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

public class Transaction {
    private Customer customer;
    private List<Book> books;
    private double totalCost;

    public Transaction(Customer customer, List<Book> books) {
        this.customer = customer;
        this.books = books;
        calculateTotalCost();
        updateCustomerPoints();
        showAlert("Transaction Complete", "Transaction completed successfully! Total cost: $" + totalCost);
    }

    private void calculateTotalCost() {
        totalCost = 0;
        for (Book book : books) {
            totalCost += book.getPrice();
        }
    }

    private void updateCustomerPoints() {
        int earnedPoints = (int) (totalCost * 10); // 10 points per dollar
        customer.addPoints(earnedPoints);
        showAlert("Points Earned", "You earned " + earnedPoints + " points!");
    }

    public double redeemPoints() {
        int redeemablePoints = customer.getPoints() / 100; // 100 points = $1 discount
        double discount = Math.min(redeemablePoints, totalCost);
        totalCost -= discount;
        customer.deductPoints((int) (discount * 100));
        showAlert("Points Redeemed", "You redeemed " + (int) (discount * 100) + " points! New total: $" + totalCost);
        return totalCost;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Transaction{customer=" + customer.getUsername() + ", totalCost=" + totalCost + "}";
    }
}