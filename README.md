# Hotels.com Programming Test

This exercise revolves around creating an implementation of a hotel search service api. This api should allow users to search for and filter a sorted list of hotelIds representing hotels matching their search criteria.
The service is backed by a Pricing & Availability service, which returns all of the data that the hotel search service currently requires.
 
## Important points...

 - the exercise is intended to test core java programming skills, so only the dependencies available in the pom should be used
 - this service will be used in a concurrent environment
 - readability, maintainability and performance should be considered in addition to the service functioning as per the spec
 - you may add classes if required, but you should not modify any of the existing classes apart from the ones instructed to below 
 
## Exercise 1

Create a hotel search service. Implement a method in your service, which will take the query information as an argument and return an ordered Collection of Hotel IDs as a result. 
The service is responsible for calling the pricing & availability service and sorting and filtering the results.
The contents of the "com.hotels.platform.availability" package (i.e. PricingAndAvailabilityService & related beans) are provided by another team, so you are **not** responsible for the implementation and testing of this component.

The service should use the PricingAndAvailabilityService to get a collection of hotels and then filter, limit and sort the results as explained below:

- filter according to whether the user wants breakfast included, breakfast excluded or is happy with either.
- allow the number of results to be limited. If no limit is specified, return 3 results.
- sort the results by popularity, with the most popular first.
 
The classes provided in the "com.hotels.platform.availability" package have javadoc comments to explain how they are to be used.

## Exercise 2

There is a problem with the current implementation of PricingAndAvailabilityRequest. Add a TODO comment to the class with potential solutions.
