/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Foodie.Controllers;

import com.Foodie.model.food;
import com.foodie.view.foodOrder;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 23028573_ArchanaGiri
 */
public class calculateBills {
        private foodOrder foodOrderView;
        
        public calculateBills(foodOrder foodOrderView){
            this.foodOrderView = foodOrderView;
        }
        public void calculateTotal() {
        foodOrderView.clearBillTable(); // Clear existing bill items
        double total = 0.0;

        List<foodItem> selectedItems = foodOrderView.getSelectedItems();

        // Get selected items and their quantities
        for (foodItem item : selectedItems) {
            int quantity = foodOrderView.getQuantity(item.getName());
            if (foodOrderView.qtyIsZero(quantity)) {
                foodOrderView.addItemToBill(item, quantity);
                total += item.getPrice() * quantity;
            }
        }

        // Calculate and display tax and total
        double tax = 0.10 * total; // Assuming 10% tax
        double grandTotal = total + tax;

        foodOrderView.setTaxTextfield(String.format("%.2f", tax));
        foodOrderView.setTotalTextfield(String.format("%.2f", grandTotal));
    }
        public void generateBill() {
        // Get bill data from the table
        DefaultTableModel model = (DefaultTableModel) foodOrderView.getBillTable().getModel();
        int rowCount = model.getRowCount();
        String billData = "";

        billData += "----------------------------------------\n";
        billData += "       Restaurant Bill\n";
        billData += "----------------------------------------\n";
        billData += String.format("%-20s %-10s %-10s %-10s\n", "Name", "Price", "Qty", "Total");

        for (int i = 0; i < rowCount; i++) {
            String name = (String) model.getValueAt(i, 0);
            double price = (Double) model.getValueAt(i, 1);
            int quantity = (Integer) model.getValueAt(i, 2);
            double total = (Double) model.getValueAt(i, 3);
            billData += String.format("%-20s %-10.2f %-10d %-10.2f\n", name, price, quantity, total);
        }

        billData += "----------------------------------------\n";
        billData += "Tax (10%): " + foodOrderView.getTaxTextfield() + "\n";
        billData += "Total: " + foodOrderView.getTotalTextfield() + "\n";
        billData += "----------------------------------------\n";

        // Display the bill in a dialog box
        JOptionPane.showMessageDialog(null, billData, "Restaurant Bill", JOptionPane.INFORMATION_MESSAGE);

        // You can also save the bill data to a file here
        // ... (File handling logic) 
    }

}
