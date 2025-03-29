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
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private int points;
    private String status; // Silver or Gold
    private List<Transaction> transactions;

    public Customer(String username, String password) {
        super(username, password);
        this.points = 0;
        this.status = "Silver";
        this.transactions = new ArrayList<>();
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
        updateStatus();
    }

    public void deductPoints(int points) {
        this.points = Math.max(this.points - points, 0);
        updateStatus();
    }

    private void updateStatus() {
        this.status = (points >= 1000) ? "Gold" : "Silver";
        showAlert("Status Update", "Your status is now: " + status);
    }

    public void buyBook(List<Book> books) {
        Transaction transaction = new Transaction(this, books);
        transactions.add(transaction);
    }

    public double redeemPoints(Transaction transaction) {
        return transaction.redeemPoints();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public String toString() {
        return "Customer{username=" + getUsername() + ", status=" + status + ", points=" + points + "}";
    }
}
