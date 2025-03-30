/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ammar
 */

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.List;

/**
 * Represents a transaction in the bookstore system.
 * Handles the purchase of books, calculates costs, and manages reward points.
 */
public class Transaction {
    // Core transaction components
    private Customer customer; // Customer making the purchase
    private List<Book> books; // Books being purchased
    private double totalCost; // Total cost of the transaction

    /**
     * Creates a new transaction and processes it immediately.
     * 
     * @param customer The customer making the purchase
     * @param books    List of books being purchased
     */
    public Transaction(Customer customer, List<Book> books) {
        this.customer = customer;
        this.books = books;
        calculateTotalCost();
        updateCustomerPoints();
        showAlert("Transaction Complete", "Transaction completed successfully! Total cost: $" + totalCost);
    }

    /**
     * Calculates the total cost of all books in the transaction.
     * Sums up individual book prices to get the final cost.
     */
    private void calculateTotalCost() {
        totalCost = 0;
        for (Book book : books) {
            totalCost += book.getPrice();
        }
    }

    /**
     * Updates the customer's reward points based on purchase amount.
     * Awards 10 points per dollar spent.
     */
    private void updateCustomerPoints() {
        int earnedPoints = (int) (totalCost * 10); // 10 points per dollar
        customer.addPoints(earnedPoints);
        showAlert("Points Earned", "You earned " + earnedPoints + " points!");
    }

    /**
     * Handles the redemption of customer points for purchase discounts.
     * Converts points to monetary value (100 points = $1)
     * 
     * @return Updated total cost after point redemption
     */
    public double redeemPoints() {
        int redeemablePoints = customer.getPoints() / 100; // 100 points = $1 discount
        double discount = Math.min(redeemablePoints, totalCost);
        totalCost -= discount;
        customer.deductPoints((int) (discount * 100));
        showAlert("Points Redeemed", "You redeemed " + (int) (discount * 100) + " points! New total: $" + totalCost);
        return totalCost;
    }

    /**
     * Displays an information alert to the user.
     * 
     * @param title   The alert window title
     * @param message The message to display
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Returns the total cost of the transaction.
     * 
     * @return Current total cost
     */
    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Transaction{customer=" + customer.getUsername() + ", totalCost=" + totalCost + "}";
    }
}