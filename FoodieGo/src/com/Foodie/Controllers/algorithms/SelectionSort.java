/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Foodie.Controllers.algorithms;
import com.Foodie.model.food;
import java.util.ArrayList;
import java.util.List;
/**
 * Implements the Selection Sort algorithm for sorting a list of {@code food} objects by price.
 * 
 * <p>This class provides functionality to sort food items in ascending or descending order 
 * based on their prices. It uses a selection sort algorithm that repeatedly selects the 
 * minimum or maximum element and places it in the correct position.</p>
 * 
 * @author 23028573_ArchanaGiri
 */
public class SelectionSort {
    // List to hold the sorted food items
    List<food> foodSortList;
     /**
     * Default constructor initializing the food sort list.
     */
    public SelectionSort(){
        foodSortList = new ArrayList<>();
        
    }
    /**
     * Sorts the list of food items by price using the Selection Sort algorithm.
     *
     * @param price A list of {@code food} objects to be sorted.
     * @param isDesc A boolean indicating the order of sorting:
     *               {@code true} for descending, {@code false} for ascending.
     * @return A sorted list of {@code food} objects by price.
     * @throws IllegalArgumentException If the input list is null or empty.
     */
    public List<food> sortByPrice(List<food> price, boolean isDesc){
        // Clear and initialize the sorting list
        this.foodSortList.clear();
        this.foodSortList.addAll(price);
 
        // Validate the input list
        if(foodSortList == null || foodSortList.isEmpty()){
            throw new IllegalArgumentException("Student list cannot be null or empty.");
        }
         // Perform selection sort
        for(int i=0; i<foodSortList.size() -1; i++){
            // Find the index of the extremum (min or max) element
            int extremumIndex = findExtremumIndex(foodSortList, i, isDesc);
            // Swap elements if the current index is not the extremum index
            if(i != extremumIndex){
                swap(foodSortList, i, extremumIndex);
            }
        }
        // Return the sorted list
        return foodSortList;
    }
    /**
     * Finds the index of the extremum (minimum or maximum) element in the list.
     *
     * @param foodSortList The list of food items.
     * @param startIndex The starting index for searching.
     * @param isDesc A boolean indicating whether to find the maximum (true) or minimum (false).
     * @return The index of the extremum element.
     */
    private int findExtremumIndex(List<food> foodSortList, int startIndex, boolean isDesc){
        int extremumIndex = startIndex;
        // Iterate through the list to find the extremum value
        for(int j = startIndex + 1; j<foodSortList.size(); j++){
            if(shouldSwap(foodSortList.get(j).getPrice(), foodSortList.get(extremumIndex).getPrice(), isDesc)){
                extremumIndex =j;// Update the extremum index
            }
        }
        return extremumIndex;// Return the index of the extremum element
    }
     /**
     * Determines whether two elements should be swapped based on the sorting order.
     *
     * @param current The current element's value.
     * @param extremum The current extremum value.
     * @param isDesc A boolean indicating the sorting order:
     *               {@code true} for descending, {@code false} for ascending.
     * @return {@code true} if the elements should be swapped, {@code false} otherwise.
     */
    private boolean shouldSwap (double current, double extremum, boolean isDesc){
        return isDesc? current > extremum : current < extremum;
    }
    /**
     * Swaps two elements in the list.
     *
     * @param foodSortList The list of food items.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private void swap(List<food> foodSortList, int i, int j){
        // Temporary variable to hold one of the elements during the swap
        food temp = foodSortList.get(i);
         // Perform the swap
        foodSortList.set(i, foodSortList.get(j));
        foodSortList.set(j, temp);
    }
}
