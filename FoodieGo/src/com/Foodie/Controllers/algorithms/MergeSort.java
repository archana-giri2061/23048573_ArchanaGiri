/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Foodie.Controllers.algorithms;

import com.Foodie.model.food;
import java.util.ArrayList;
import java.util.List;
/**
 * This class implements the Merge Sort algorithm to sort a list of {@link food} objects 
 * based on their calorie attribute. The sorting can be done in either ascending or 
 * descending order depending on the flag provided.
 * 
 * Merge Sort is a divide-and-conquer algorithm that recursively splits the list into 
 * smaller sublists, sorts them, and then merges them back together in sorted order.
 * 
 * @author 23028573_ArchanaGiri
 */
public class MergeSort {
    /**
     * Sorts a list of food items by their calorie content in either ascending or
     * descending order using the merge sort algorithm
     * @param foodSortList List of {@link food} objects to be sorted by calorie.
     * @param isDesc Flag indicating whether to sort in descending order. If false, sorts in ascending order.
     * @return A sorted list of food items based on calorie.
     */
    public List<food> sortByCalorie(List<food> foodSortList, boolean isDesc) {
        //Check for  null or empty list and throw an exception if necessary 
        if (foodSortList == null || foodSortList.isEmpty()) {
            throw new IllegalArgumentException("food list cannot be null or empty");
        }
        //perform the merge sort on the food list
        return mergeSort(foodSortList, isDesc);
    }
    
    private List<food> mergeSort(List<food> list, boolean isDesc) {
        if (list.size() <= 1) {
            return list;
        }

        // Split the list into two halves
        int mid = list.size() / 2;
        List<food> left = new ArrayList<>(list.subList(0, mid));
        List<food> right = new ArrayList<>(list.subList(mid, list.size()));

        // Recursively sort each half
        left = mergeSort(left, isDesc);
        right = mergeSort(right, isDesc);

        // Merge the sorted halves
        return merge(left, right, isDesc);
    }

    private List<food> merge(List<food> left, List<food> right, boolean isDesc) {
        List<food> merged = new ArrayList<>();
        int i = 0, j = 0;

        // Merge elements from both lists based on the `shouldMerge` condition
        while (i < left.size() && j < right.size()) {
            if (shouldMerge(left.get(i).getCalorie(), right.get(j).getCalorie(), isDesc)) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        // Add remaining elements from the left list
        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        // Add remaining elements from the right list
        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }

    private boolean shouldMerge(int leftCalorie, int rightCalorie, boolean isDesc) {
        return isDesc ? leftCalorie > rightCalorie : leftCalorie < rightCalorie;
    }
}
