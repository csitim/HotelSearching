package com.hotels.platform.availability;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Represents a bookable hotel's details.
 */
public final class HotelDetails {
    private final String hotelId;
    private final int nightlyPrice;
    private final int popularityIndex;
    private final boolean breakfastIncluded;

    /**
     * Constructs a new instance.
     *
     * @param hotelId           Unique identifier for this hotel
     * @param nightlyPrice      The cost per night to book this hotel
     * @param popularityIndex   The relative popularity of this hotel. The lower the index the more popular the hotel, with '1' being the most popular hotel in the system
     * @param breakfastIncluded Indicates if this hotel includes breakfast in its price
     */
    public HotelDetails(String hotelId,
                        int nightlyPrice,
                        int popularityIndex,
                        boolean breakfastIncluded) {
        this.hotelId = requireNonNull(hotelId);
        this.nightlyPrice = nightlyPrice;
        this.popularityIndex = popularityIndex;
        this.breakfastIncluded = breakfastIncluded;
    }

    /**
     * Returns a unique identifier for this hotel.
     *
     * @return a unique identifier for this hotel
     */
    public String getHotelId() {
        return hotelId;
    }

    /**
     * Returns the cost per night to book this hotel.
     *
     * @return the cost per night to book this hotel
     */
    public int getNightlyPrice() {
        return nightlyPrice;
    }

    /**
     * Returns the relative popularity of this hotel. The lower the index the more popular the hotel, with '1' being the most popular hotel in the system.
     *
     * @return the relative popularity of this hotel. The lower the index the more popular the hotel, with '1' being the most popular hotel in the system
     */
    public int getPopularityIndex() {
        return popularityIndex;
    }

    /**
     * Returns true if this hotel includes breakfast in its price.
     *
     * @return if this hotel includes breakfast in its price
     */
    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, nightlyPrice, popularityIndex, breakfastIncluded);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof HotelDetails) {
            HotelDetails other = (HotelDetails) obj;
            return Objects.equals(hotelId, other.hotelId)
                    && Objects.equals(nightlyPrice, other.nightlyPrice)
                    && Objects.equals(popularityIndex, other.popularityIndex)
                    && Objects.equals(breakfastIncluded, other.breakfastIncluded);
        }
        return false;
    }

    @Override
    public String toString() {
        return "HotelDetails{" +
                "hotelId='" + hotelId + '\'' +
                ", nightlyPrice=" + nightlyPrice +
                ", popularityIndex=" + popularityIndex +
                ", breakfastIncluded=" + breakfastIncluded +
                '}';
    }
}
