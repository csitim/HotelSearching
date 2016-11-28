package com.hotels.platform.search;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Defines the search criteria for a hotel searching.
 */
public class SearchRequest {
	private final String destination;
	private final LocalDate checkIn;
	private final LocalDate checkOut;
	private final int numberOfGuests;
	private final Boolean breakfastIncluded;
	private final Integer numberOfResultsLimit;

	/**
	 * Constructs an instance.
	 *
	 * @param destination
	 *            The location the user would like to stay e.g. 'Paris'
	 * @param checkIn
	 *            The check in date for the hotel
	 * @param checkOut
	 *            The check out date for the hotel
	 * @param numberOfGuests
	 *            The number of guests a room is required for
	 * @param breakfastIncluded
	 *            The breakfast included the price, excluded or null if the user is
	 *            happy with either
	 * @param numberOfResultsLimit
	 *            The limit of the number of results
	 */
	public SearchRequest(String destination, LocalDate checkIn, LocalDate checkOut, int numberOfGuests, Boolean breakfastIncluded, Integer numberOfResultsLimit) {
		this.destination = requireNonNull(destination);
		this.checkIn = requireNonNull(checkIn);
		this.checkOut = requireNonNull(checkOut);
		this.numberOfGuests = numberOfGuests;
		this.breakfastIncluded = breakfastIncluded;
		this.numberOfResultsLimit = numberOfResultsLimit;
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
	public LocalDate getCheckIn() {
		return checkIn;
	}

	/**
	 * Returns the check out date for the hotel.
	 *
	 * @return the check out date for the hotel
	 */
	public LocalDate getCheckOut() {
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
	
	/**
	 * Returns that the breakfast included the price, excluded or null if the user is
	 *            happy with either
	 *
	 * @return the breakfast included the price, excluded or null if the user is
	 *            happy with either
	 */
	public Boolean getBreakfastIncluded() {
		return breakfastIncluded;
	}
	
	/**
	 * Returns the limit of the number of results.
	 *
	 * @return the limit of the number of results
	 */
	public Integer getNumberOfResultsLimit() {
		return numberOfResultsLimit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destination, checkIn, checkOut, numberOfGuests, breakfastIncluded, numberOfResultsLimit);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof SearchRequest) {
			SearchRequest other = (SearchRequest) obj;
			return Objects.equals(destination, other.destination) && Objects.equals(checkIn, other.checkIn)
					&& Objects.equals(checkOut, other.checkOut) && Objects.equals(numberOfGuests, other.numberOfGuests)
					&& Objects.equals(breakfastIncluded, other.breakfastIncluded) && Objects.equals(numberOfResultsLimit, other.numberOfResultsLimit);
		}
		return false;
	}

	@Override
	public String toString() {
		return "PricingAndAvailabilityRequest{" + "destination='" + destination + '\'' + ", checkIn=" + checkIn
				+ ", checkOut=" + checkOut + ", numberOfGuests=" + numberOfGuests + ", breakfastIncluded=" + breakfastIncluded + ", numberOfResultsLimit=" + numberOfResultsLimit + '}';
	}
}
