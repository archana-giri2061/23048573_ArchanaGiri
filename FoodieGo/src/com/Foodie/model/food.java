/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Foodie.model;
/**
 * Represents a food item with various attributes such as name, price, quantity, 
 * ingredients, calorie count, vegetarian status, custom additions, and portion size.
 * 
 * @author 23028573_ArchanaGiri
 */
public class food {
    // Food item attributes
    private String name;
    private double price;
    private int Quantity;
    private String Ingredients;
    private int Calorie;
    private Boolean IsVegetrain;
    private String CustomAdd;
    private String portionSize;

    /**
     * Constructs a new food object with the specified attributes.
     *
     * @param name         Name of the food item
     * @param price        Price of the food item
     * @param Quantity     Quantity available
     * @param Ingredients  Ingredients used
     * @param Calorie      Calorie count
     * @param IsVegetrain  True if vegetarian, false otherwise
     * @param CustomAdd    Custom additions or preferences
     * @param portionSize  Portion size description
     */
    public food(String name, double price, int Quantity, String Ingredients, int Calorie, Boolean IsVegetrain, String CustomAdd, String portionSize) {
        this.name = name;
        this.price = price;
        this.Quantity = Quantity;
        this.Ingredients = Ingredients;
        this.Calorie = Calorie;
        this.IsVegetrain = IsVegetrain;
        this.CustomAdd = CustomAdd;
        this.portionSize = portionSize;
    }
     /**
     * Gets the name of the food item.
     *
     * @return the name of the food item
     */
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public int getCalorie() {
        return Calorie;
    }

    public Boolean getIsVegetrain() {
        return IsVegetrain;
    }

    public String getCustomAdd() {
        return CustomAdd;
    }

    public String getPortionSize() {
        return portionSize;
    }
    /**
     * Sets the name of the food item.
     *
     * @param name the new name of the food item
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setIngredients(String Ingredients) {
        this.Ingredients = Ingredients;
    }

    public void setCalorie(int Calorie) {
        this.Calorie = Calorie;
    }

    public void setIsVegetrain(Boolean IsVegetrain) {
        this.IsVegetrain = IsVegetrain;
    }

    public void setCustomAdd(String CustomAdd) {
        this.CustomAdd = CustomAdd;
    }

    public void setPortionSize(String portionSize) {
        this.portionSize = portionSize;
    }    
}
