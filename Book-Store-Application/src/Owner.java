/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Khalid 
 */

/**
 * 
 * Represents the Owner in the bookstore System.
 * Owner has administrative privileges to manage books and customers
 */
public class Owner extends User {
    /**
     * Creates an Owner with their username and password.
     * Username and Password are both "admin".
     */
    public Owner() {
        super("admin", "admin");
    }
    /**
     * Adds a book to the bookstore.
     * @param store The bookstore where the book will be added to.
     * @param b The book to be added.
     */
    public void addBook(BookStore store, Book b) {
        store.addBook(b);
    }
    /**
     * Removes a book from the bookstore.
     * @param store The bookstore where the book will be removed from.
     * @param b The book to be removed.
     */
    public void deleteBook(BookStore store, Book b) {
        store.deleteBook(b);
    }
    /**
     * Adds a customer to the bookstore.
     * @param store The bookstore where the customer will be added to.
     * @param c The customer to be added.
     */
    public void addCustomer(BookStore store, Customer c) {
        store.addCustomer(c);
    }
    /**
     * Removes a customer from the bookstore.
     * @param store The bookstore where the customer will be removed from.
     * @param c The customer to be removed.
     */
    public void deleteCustomer(BookStore store, Customer c) {
        store.deleteCustomer(c);
    }
    /**
     * Checks if the owner successfully logged in with the correct username and password.
     * @return true if username and password are both "admin". false otherwise.
     */
    @Override
    public boolean login() {
        return getUsername().equals("admin") && getPassword().equals("admin");
    }
}
