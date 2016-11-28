package com.hotels.platform.search;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hotels.platform.availability.HotelDetails;
import com.hotels.platform.availability.PricingAndAvailabilityRequest;
import com.hotels.platform.availability.PricingAndAvailabilityService;

public class SearchServiceImpl implements SearchService {

	private static final int DEFAULT_LIMIT = 3;

	private final PricingAndAvailabilityService pricingAndAvailabilityService;

	public SearchServiceImpl(PricingAndAvailabilityService pricingAndAvailabilityService) {
		this.pricingAndAvailabilityService = pricingAndAvailabilityService;
	}

	@Override
	public List<String> searchHotels(SearchRequest searchRequest) {

		PricingAndAvailabilityRequest pricingAndAvailabilityRequest = createPricingAndAvailabilityRequest(
				searchRequest);

		Collection<HotelDetails> availableHotelDetails = pricingAndAvailabilityService
				.getAvailableHotelDetails(pricingAndAvailabilityRequest);

		if (availableHotelDetails == null || availableHotelDetails.isEmpty()) {
			return new ArrayList<>();
		}

		Integer limit = searchRequest.getNumberOfResultsLimit();
		if (limit == null) {
			limit = DEFAULT_LIMIT;
		}

		Boolean breakfastIncludedCriteria = searchRequest.getBreakfastIncluded();

		return availableHotelDetails.stream()
				.filter(new FilterBreakfastPredicate(breakfastIncludedCriteria))
				.sorted(Comparator.comparingInt(HotelDetails::getPopularityIndex))
				.limit(limit)
				.map(HotelDetails::getHotelId)
				.collect(Collectors.toList());

	}

	private PricingAndAvailabilityRequest createPricingAndAvailabilityRequest(SearchRequest searchRequest) {

		String destination = searchRequest.getDestination();
		Date checkIn = Date.valueOf(searchRequest.getCheckIn());
		Date checkOut = Date.valueOf(searchRequest.getCheckOut());
		int numberOfGuests = searchRequest.getNumberOfGuests();

		return new PricingAndAvailabilityRequest(destination, checkIn, checkOut, numberOfGuests);
	}

	private class FilterBreakfastPredicate implements Predicate<HotelDetails> {

		private final Boolean criteria;

		public FilterBreakfastPredicate(Boolean criteria) {
			this.criteria = criteria;
		}

		@Override
		public boolean test(HotelDetails hotelDetails) {
			if (criteria == null) {
				return true;
			}
			return criteria.booleanValue() == hotelDetails.isBreakfastIncluded();
		}

	}

}
