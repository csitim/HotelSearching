package com.hotels.platform.search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hotels.platform.availability.HotelDetails;
import com.hotels.platform.availability.PricingAndAvailabilityService;

public class SearchServiceTest {

	private static final int MAX_NUMBER_OF_HOTELS = 10;

	private static final SearchRequest GENERAL_SEARCH_REQUEST = new SearchRequest("Paris", LocalDate.of(2016, 11, 28),
			LocalDate.of(2016, 11, 30), 2, null, MAX_NUMBER_OF_HOTELS);

	private static final SearchRequest SEARCH_REQUEST_WITH_BREAKFAST = new SearchRequest("Paris",
			LocalDate.of(2016, 11, 28), LocalDate.of(2016, 11, 30), 2, Boolean.TRUE, MAX_NUMBER_OF_HOTELS);

	private static final SearchRequest SEARCH_REQUEST_WITHOUT_BREAKFAST = new SearchRequest("Paris",
			LocalDate.of(2016, 11, 28), LocalDate.of(2016, 11, 30), 2, Boolean.FALSE, MAX_NUMBER_OF_HOTELS);

	private static final SearchRequest SEARCH_REQUEST_WITH_NO_LIMIT = new SearchRequest("Paris",
			LocalDate.of(2016, 11, 28), LocalDate.of(2016, 11, 30), 2, null, null);

	private static final SearchRequest SEARCH_REQUEST_WITH_LIMIT_2 = new SearchRequest("Paris",
			LocalDate.of(2016, 11, 28), LocalDate.of(2016, 11, 30), 2, null, 2);

	private static final SearchRequest SEARCH_REQUEST_WITH_LIMIT_11 = new SearchRequest("Paris",
			LocalDate.of(2016, 11, 28), LocalDate.of(2016, 11, 30), 2, null, MAX_NUMBER_OF_HOTELS + 1);
	
	private static final SearchRequest SEARCH_REQUEST_WITH_BREAKFAST_WITH_LIMIT_2 = new SearchRequest("Paris",
			LocalDate.of(2016, 11, 28), LocalDate.of(2016, 11, 30), 2, Boolean.TRUE, 2);

	@Test
	public void testWhenPricingAndAvailabilityServiceReturnedByNullThenEmptyListShouldBeReturned() {
		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(null).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(GENERAL_SEARCH_REQUEST);

		Assert.assertTrue(hotelIds.isEmpty());

	}

	@Test
	public void testWhenNoHotelIsMatchingTheCriteriaThenEmptyListShouldBeReturned() {

		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(new ArrayList<>()).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(GENERAL_SEARCH_REQUEST);

		Assert.assertTrue(hotelIds.isEmpty());
	}

	@Test
	public void testWhenBreakfastIncludedSearchCriteriaIsTrueThenOnlyThisTypeOfHotelsShouldBeReturned() {
		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(getAllHotelDetails()).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(SEARCH_REQUEST_WITH_BREAKFAST);

		Assert.assertEquals(hotelIds.size(), 6);
	}

	@Test
	public void testWhenBreakfastIncludedSearchCriteriaIsFalseThenOnlyThisTypeOfHotelsShouldBeReturned() {
		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(getAllHotelDetails()).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(SEARCH_REQUEST_WITHOUT_BREAKFAST);

		Assert.assertEquals(hotelIds.size(), 4);
	}

	@Test
	public void testWhenBreakfastIncludedSearchCriteriaIsNotSetThenItDoesNotEffectTheHotelResultList() {
		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(getAllHotelDetails()).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(GENERAL_SEARCH_REQUEST);

		Assert.assertEquals(hotelIds.size(), MAX_NUMBER_OF_HOTELS);
	}

	@Test
	public void testWhenLimitIsSetAndItIsLowerThanTheSizeOfTheResultList() {
		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(getAllHotelDetails()).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(SEARCH_REQUEST_WITH_LIMIT_2);

		Assert.assertEquals(hotelIds.size(), 2);
	}

	@Test
	public void testWhenLimitIsSetAndItIsBiggerThanTheSizeOfTheResultList() {
		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(getAllHotelDetails()).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(SEARCH_REQUEST_WITH_LIMIT_11);

		Assert.assertEquals(hotelIds.size(), MAX_NUMBER_OF_HOTELS);
	}

	@Test
	public void testWhenLimitIsNotSetThenItShouldBeSetForDefaultValue() {
		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(getAllHotelDetails()).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(SEARCH_REQUEST_WITH_NO_LIMIT);

		Assert.assertEquals(hotelIds.size(), 3);
	}
	
	@Test
	public void testWhenBothOfLimitAndBreakfastAreSet() {
		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(getAllHotelDetails()).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(SEARCH_REQUEST_WITH_BREAKFAST_WITH_LIMIT_2);

		Assert.assertEquals(hotelIds.size(), 2);
		Assert.assertEquals(hotelIds.get(0), "1");
		Assert.assertEquals(hotelIds.get(1), "6");
	}

	@Test
	public void testThatTheResultListIsSorted() {
		PricingAndAvailabilityService pricingAndAvailabilityServiceMock = PricingAndAvailabilityServiceMockBuilder
				.getBuilder().withHotelDetails(getAllHotelDetails()).build();

		SearchService searchService = new SearchServiceImpl(pricingAndAvailabilityServiceMock);
		List<String> hotelIds = searchService.searchHotels(SEARCH_REQUEST_WITH_BREAKFAST);

		Assert.assertEquals(hotelIds.get(0), "1");
		Assert.assertEquals(hotelIds.get(1), "6");
		Assert.assertEquals(hotelIds.get(2), "7");
		Assert.assertEquals(hotelIds.get(3), "8");
		Assert.assertEquals(hotelIds.get(4), "3");
		Assert.assertEquals(hotelIds.get(5), "2");
	}

	private Collection<HotelDetails> getAllHotelDetails() {
		Collection<HotelDetails> hotelDetails = new ArrayList<>(MAX_NUMBER_OF_HOTELS);

		hotelDetails.add(new HotelDetails("1", 10, 3, true));
		hotelDetails.add(new HotelDetails("2", 20, 10, true));
		hotelDetails.add(new HotelDetails("3", 30, 7, true));
		hotelDetails.add(new HotelDetails("4", 40, 2, false));
		hotelDetails.add(new HotelDetails("5", 10, 1, false));
		hotelDetails.add(new HotelDetails("6", 20, 4, true));
		hotelDetails.add(new HotelDetails("7", 30, 5, true));
		hotelDetails.add(new HotelDetails("8", 40, 6, true));
		hotelDetails.add(new HotelDetails("9", 10, 3, false));
		hotelDetails.add(new HotelDetails("10", 20, 1, false));

		return hotelDetails;
	}

}
