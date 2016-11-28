package com.hotels.platform.search;

import java.util.Collection;

import org.mockito.Mockito;

import com.hotels.platform.availability.HotelDetails;
import com.hotels.platform.availability.PricingAndAvailabilityRequest;
import com.hotels.platform.availability.PricingAndAvailabilityService;

public class PricingAndAvailabilityServiceMockBuilder {

	private Collection<HotelDetails> hotelDetails;

	private PricingAndAvailabilityServiceMockBuilder() {
	}

	public static PricingAndAvailabilityServiceMockBuilder getBuilder() {
		return new PricingAndAvailabilityServiceMockBuilder();
	}

	public PricingAndAvailabilityServiceMockBuilder withHotelDetails(Collection<HotelDetails> hotelDetails) {
		this.hotelDetails = hotelDetails;
		return this;
	}

	public PricingAndAvailabilityService build() {
		PricingAndAvailabilityService mock = Mockito.mock(PricingAndAvailabilityService.class);

		Mockito.when(mock.getAvailableHotelDetails(Mockito.any(PricingAndAvailabilityRequest.class)))
				.thenReturn(hotelDetails);

		return mock;
	}
}
