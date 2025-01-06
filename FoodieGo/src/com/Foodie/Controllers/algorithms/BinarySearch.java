package com.Foodie.Controllers.algorithms;

import java.util.List;
import com.Foodie.model.food;

/**
 * Binary Search Implementation for Food Objects
 *
 * @author 23028573_ArchanaGiri
 */
public class BinarySearch {

    /**
     * Performs a binary search to find a food object by its name.
     *
     * @param searchValue the name to search for
     * @param foodSortList the sorted list of food objects
     * @param left the leftmost index
     * @param right the rightmost index
     * @return the food object if found, or null if not found
     */
    public food searchByName(String searchValue, List<food> foodSortList, int left, int right) {
        if (foodSortList == null || foodSortList.isEmpty()) {
            throw new IllegalArgumentException("The food list cannot be null or empty.");
        }

        // Base case: the search range is invalid
        if (right < left) {
            return null;
        }

        // Calculate the middle index
        int mid = left + (right - left) / 2;

        // Compare the search value with the name of the food object at the middle index
        int comparison = searchValue.compareToIgnoreCase(foodSortList.get(mid).getName());

        if (comparison == 0) {
            // Found the target food object
            return foodSortList.get(mid);
        } else if (comparison < 0) {
            // Search in the left half
            return searchByName(searchValue, foodSortList, left, mid - 1);
        } else {
            // Search in the right half
            return searchByName(searchValue, foodSortList, mid + 1, right);
        }
    }
}
