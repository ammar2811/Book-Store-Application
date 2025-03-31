/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ammar
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer in the bookstore system.
 * Extends User class with additional functionality for loyalty program.
 */
public class Customer extends User {
    private int points; // Customer's reward points
    private String status; // Customer status (Silver/Gold)
    private List<Transaction> transactions; // Customer's purchase history

    /**
     * Creates a new customer with default values.
     * Initializes with 0 points and Silver status.
     * 
     * @param username Customer's username
     * @param password Customer's password
     */
    public Customer(String username, String password, int points) {
        super(username, password);
        this.points = points;
        this.status = "Silver";
        this.transactions = new ArrayList<>();
    }

    /**
     * @return Current reward points balance
     */
    public int getPoints() {
        return points;
    }

    /**
     * Adds reward points to customer's account and updates status.
     * 
     * @param points Points to be added
     */
    public void addPoints(int points) {
        this.points += points;
        updateStatus();
    }

    /**
     * Deducts points from customer's account.
     * Prevents negative point balance.
     * 
     * @param points Points to be deducted
     */
    public void deductPoints(int points) {
        this.points = Math.max(this.points - points, 0);
        updateStatus();
    }
    
    public String getStatus(){
        return status;
    }
    /**
     * Updates customer status based on points.
     * Gold status requires 1000+ points.
     */
    private void updateStatus() {
        this.status = (points >= 1000) ? "Gold" : "Silver";
        //showAlert("Status Update", "Your status is now: " + status);
    }

    /**
     * Processes a book purchase transaction.
     * 
     * @param books List of books to purchase
     */
    public void buyBook(List<Book> books) {
        Transaction transaction = new Transaction(this, books, true);
        transactions.add(transaction);
    }

    /**
     * Redeems points for a specific transaction.
     * 
     * @param transaction Transaction to apply points to
     * @return Updated total cost after redemption
     */
    public double redeemPoints(Transaction transaction) {
        return transaction.redeemPoints();
    }

    /**
     * Displays an information alert to the user.
     * 
     * @param title   The alert window title
     * @param message The message to display
     */
    /*private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }*/
 
    @Override
    public boolean login() {
        // TODO: Change this to actually work for customers not admins.
        return getUsername().equals("admin") && getPassword().equals("admin");
    }

    @Override
    public boolean logout() {
        System.out.println("Customer " + getUsername() + "has logged out");
        return true;
    }

    @Override
    public String toString() {
        return "Customer: " + getUsername() + ", Status: " + status + ", Points = " + points;
    }
}
