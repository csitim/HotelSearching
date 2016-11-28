package com.hotels.platform.search;

import java.util.List;

/**
 * Provides the ability to search for hotels that are currently available and
 * match the user's search criteria.
 */
public interface SearchService {

	/**
	 * Retrieves the IDs of all available hotels matching the search criteria
	 * specified in the request.
	 *
	 * @param searchRequest
	 *            request
	 * @return an ordered collection of hotel IDs matching the request, sorted
	 *         by popularity (most popular first). Limit can be specified
	 *         (default limit is 3).
	 */
	List<String> searchHotels(SearchRequest searchRequest);

}
