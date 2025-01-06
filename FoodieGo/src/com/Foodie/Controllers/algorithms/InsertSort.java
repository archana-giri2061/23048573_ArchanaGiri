/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Foodie.Controllers.algorithms;

import com.Foodie.model.food;
import java.util.ArrayList;
import java.util.List;
/**
 * This class implements an insertion sort algorithm specifically for sorting a list of 
 * {@link food} objects by their name attribute.
 * The sorting can be done in either ascending or descending order based on the flag provided.
 * 
 * @author 23028573_ArchanaGiri
 */
public class InsertSort {
    /**
     * List to store the food items to be sorted.
     */
    List<food> foodSortList;
     /**
     * Constructor initializes the foodSortList as an empty ArrayList.
     */
    public InsertSort() {
        foodSortList = new ArrayList<>();
    }
    /**
     * Sorts the provided list of food items by their name in either ascending or descending order.
     * 
     * @param foodSortList List of {@link food} objects to be sorted.
     * @param isDesc Flag indicating whether to sort in descending order. If false, sorts in ascending order.
     * @return Sorted list of food items.
     * @throws IllegalArgumentException if the provided food list is null or empty.
     */
    public List<food> sortByName(List<food> foodSortList, boolean isDesc) {
        // Clear the internal list and add all elements from the provided list
        this.foodSortList.clear();
        this.foodSortList.addAll(foodSortList);

        // Check for null or empty list and throw an exception if necessary
        if (this.foodSortList == null || this.foodSortList.isEmpty()) {
            throw new IllegalArgumentException("Name list cannot be null or empty.");
        }
        // Insertion sort logic: Iterate through the list and move elements to their correct position
        for (int i = 1; i < this.foodSortList.size(); i++) {
            for (int j = i; j > 0 && shouldSwap(this.foodSortList.get(j).getName(), this.foodSortList.get(j - 1).getName(), isDesc); j--) {
                swap(this.foodSortList, j, j - 1);// Swap elements if they are in the wrong order
            }
        }
        // Return the sorted list
        return this.foodSortList;
    }
    /**
     * Determines whether two food names should be swapped based on the specified order (ascending/descending).
     * 
     * @param current Name of the current food item.
     * @param previous Name of the previous food item to compare with.
     * @param isDesc Flag indicating whether to sort in descending order.
     * @return true if the names should be swapped, false otherwise.
     */
    private boolean shouldSwap(String current, String previous, boolean isDesc) {
        // If isDesc is true, sort in descending order, otherwise sort in ascending order
        return isDesc ? current.compareToIgnoreCase(previous) > 0
        : current.compareToIgnoreCase(previous) < 0;
    }
    /**
     * Swaps two elements in the food list at the specified indices.
     * 
     * @param List The list of food items.
     * @param i The index of the first element to swap.
     * @param j The index of the second element to swap.
     */
    private void swap(List<food> List, int i, int j){
        // Store the food item temporarily to assist in swapping
        food temp = List.get(i);
        // Perform the swap in the list
        List.set(i, List.get(j));
        List.set(j, temp);
    }
}
