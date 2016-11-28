package com.hotels.platform.availability;

import java.util.Collection;

/**
 * Provides the ability to search for the pricing & details of hotels that are currently available and match the user's search criteria.
 */
public interface PricingAndAvailabilityService {
    /**
     * Retrieves the details of all available hotels matching the search criteria specified in the request.
     *
     * @param pricingAndAvailabilityRequest request
     * @return a collection of hotels matching the request in no particular order
     */
    Collection<HotelDetails> getAvailableHotelDetails(PricingAndAvailabilityRequest pricingAndAvailabilityRequest);
}
