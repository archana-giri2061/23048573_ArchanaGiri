/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Foodie.model;

/**
 *
 * @author 23028573_ArchanaGiri
 */
public class food {
    private String name;
    private double price;
    private int Quantity;
    private String Ingredients;

    public food(String name, double price, int Quantity, String Ingredients) {
        this.name = name;
        this.price = price;
        this.Quantity = Quantity;
        this.Ingredients = Ingredients;
    }

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
    
    
}
