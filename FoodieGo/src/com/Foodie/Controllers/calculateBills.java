package com.Foodie.Controllers;

import com.Foodie.model.food;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 * This class provides utility methods for calculating bills, taxes, and managing food items in a table.
 * 
 * <p>It includes methods for calculating totals (quantity, subtotal, tax, final total) 
 * and for adding food items to a table model.</p>
 * 
 * @author 23028573_ArchanaGiri
 */
public class calculateBills {
    /**
     * Calculates the total quantity of food items in the given list.
     * 
     * @param foodList A list of {@code food} objects.
     * @return The total quantity of food items.
     */
    public int calculateTotalQuantity(List<food> foodList) {
        int totalQuantity = 0;
        for ( food item : foodList) { // Iterate through each food item
            totalQuantity += item.getQuantity(); // Add the quantity of each item
        }
        return totalQuantity; // Return the total quantity
    }
     /**
     * Calculates the subtotal (price * quantity) for all food items in the list.
     * 
     * @param foodList A list of {@code food} objects.
     * @return The subtotal amount.
     */
    public double calculateSubtotal(List<food> foodList) {
        double subtotal = 0.0;
        for (food item : foodList) {
            subtotal += item.getQuantity() * item.getPrice();
        }
        return subtotal;
    }
     /**
     * Calculates tax based on a fixed rate of 3% of the subtotal.
     * 
     * @param subtotal The subtotal amount.
     * @return The calculated tax amount.
     */
    public double calculateTax(double subtotal) {
        return subtotal * 0.03; // 3% tax
    }
     /**
     * Calculates the final total by adding the subtotal and tax.
     * 
     * @param subtotal The subtotal amount.
     * @param tax The tax amount.
     * @return The final total amount.
     */
    public double calculateFinalTotal(double subtotal, double tax) {
        return subtotal + tax; // Add tax to subtotal
    }
     /**
     * Adds a {@code food} item to a {@code DefaultTableModel}.
     * 
     * <p>The table model is updated with the food item's details including its
     * name, price, quantity, ingredients, calorie count, vegetarian status, 
     * custom additions, and portion size.</p>
     * 
     * @param model The table model to which the food item should be added.
     * @param item The {@code food} object representing the item to add.
     */
    public void addFoodToTable(DefaultTableModel model, food item) {
        model.addRow(new Object[]{  // Add a new row to the table model
            item.getName(),
            item.getPrice(),
            item.getQuantity(),
            item.getIngredients(),
            item.getCalorie(),
            item.getIsVegetrain(),
            item.getCustomAdd(),
            item.getPortionSize()
        });
    }

}
