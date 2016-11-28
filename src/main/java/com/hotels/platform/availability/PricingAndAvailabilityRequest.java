package com.hotels.platform.availability;


import java.util.Date;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Defines the search criteria for a hotel pricing & availability check.
 */
public final class PricingAndAvailabilityRequest {
    private final String destination;
    private final Date checkIn; //TODO use java.time.LocalDate;
    private final Date checkOut; //TODO use java.time.LocalDate;
    private final int numberOfGuests;

    /**
     * Constructs an instance.
     *
     * @param destination    The location the user would like to stay e.g. 'Paris'
     * @param checkIn        The check in date for the hotel
     * @param checkOut       The check out date for the hotel
     * @param numberOfGuests The number of guests a room is required for
     */
    public PricingAndAvailabilityRequest(String destination,
                                         Date checkIn,
                                         Date checkOut,
                                         int numberOfGuests) {
        this.destination = requireNonNull(destination);
        this.checkIn = requireNonNull(checkIn);
        this.checkOut = requireNonNull(checkOut);
        this.numberOfGuests = numberOfGuests;
    }

    /**
     * Returns the location the user would like to stay e.g. 'Paris'.
     *
     * @return the location the user would like to stay e.g. 'Paris'
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns the check in date for the hotel.
     *
     * @return check in date for the hotel
     */
    public Date getCheckIn() {
        return checkIn;
    }

    /**
     * Returns the check out date for the hotel.
     *
     * @return the check out date for the hotel
     */
    public Date getCheckOut() {
        return checkOut;
    }

    /**
     * Returns the number of guests a room is required for.
     *
     * @return the number of guests a room is required for
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, checkIn, checkOut, numberOfGuests);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PricingAndAvailabilityRequest) {
            PricingAndAvailabilityRequest other = (PricingAndAvailabilityRequest) obj;
            return Objects.equals(destination, other.destination)
                    && Objects.equals(checkIn, other.checkIn)
                    && Objects.equals(checkOut, other.checkOut)
                    && Objects.equals(numberOfGuests, other.numberOfGuests);
        }
        return false;
    }

    @Override
    public String toString() {
        return "PricingAndAvailabilityRequest{" +
                "destination='" + destination + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
