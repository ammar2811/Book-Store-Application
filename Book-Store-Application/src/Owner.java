/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Khalid Guled
 */
public class Owner extends User {
    public Owner() {
        super("admin", "admin");
    }

    public void addBook(BookStore store, Book b) {
        store.addBook(b);
    }

    public void deleteBook(BookStore store, Book b) {
        store.deleteBook(b);
    }

    public void addCustomer(BookStore store, Customer c) {
        store.addCustomer(c);
    }

    public void deleteCustomer(BookStore store, Customer c) {
        store.deleteCustomer(c);
    }

    @Override
    public boolean login() {
        return getUsername().equals("admin") && getPassword().equals("admin");
    }

    @Override
    public boolean logout() {
        System.out.println("Owner has logged out.");
        return true;
    }
}
