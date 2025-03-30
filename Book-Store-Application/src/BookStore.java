/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *
 * @author Khalid 
 */

/**
 * 
 * Represents a Book store where books and customer data is managed
 * The bookstore allows adding/removing books and customers
 * Along with storing and loading data from text files
 * 
 */
public class BookStore {
    private List<Book> bookList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    /**
     * Retrieves a list of books available in the bookstore
     * @return A list of books
     */
    public List<Book> getBooks(){
        return bookList;
    }
    /**
     * Retrieves a list of customers registered in the bookstore
     * @return A list of customer data
     */
    public List<Customer> getCustomers(){
        return customerList;
    }
    /**
     * Adds a book to the bookstore
     * @param b Book to be added
     */
    public void addBook(Book b) {
        bookList.add(b);
    }
    /**
     * Deletes a book from the bookstore
     * @param b Book to be deleted
     * @return true if book was removed from the store. returns false otherwise.
     */
    public boolean deleteBook(Book b) {
        return bookList.remove(b);
    }
    /**
     * Adds a customer to the bookstore
     * @param c Customer to be added
     */
    public void addCustomer(Customer c) {
        customerList.add(c);
    }
     /**
     * Deletes a customer from the bookstore
     * @param c Customer to be removed
     * @return true if customer was removed from the store. false otherwise.
     */
    public boolean deleteCustomer(Customer c) {
        return customerList.remove(c);
    }
    /**
     * Stores the list of books into a text file called books.txt
     * Book details are stored on separated lines.
     */
    public void StoreBooks(){
        File bookstext = new File("books.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookstext,false))){
            for (Book book : bookList){
                writer.write(book.getDetails());
                writer.newLine();
            }   
        }
        catch (IOException e){
            e.printStackTrace();
        } 
    }
    /**
     * Stores the list of registered customers into a text file called customer.txt
     * Customer details are stored on separated lines.
     */
    public void StoreCustomers(){
        File customerstext = new File("customers.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerstext,false))){
            for (Customer customer : customerList){
                writer.write(customer.getUsername() + "," + customer.getPassword() + "," + customer.getPoints());
                writer.newLine();
            }   
        }
        catch (IOException e){
            e.printStackTrace();
        } 
    }
   /**
    * Loads books from books.txt and adds them to the bookstore's book list
    * converts text data into book objects and adds them to the list
    */
    public void loadBooks(){
        File bookstext = new File("books.txt");
        
        if (!bookstext.exists()){
            System.out.println("books.txt was not found");
            return;
        }
        try(Scanner scanner = new Scanner(bookstext)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] details = line.split(",");
                
                if (details.length == 2){
                    String title = details[0].trim();
                    double price = Double.parseDouble(details[1].trim());
                    Book book = new Book(title, price);
                    bookList.add(book);
                }
                else{
                    System.out.println("Skipping incorrect book entry: " + line);
                }
            }
        }
        
        catch (FileNotFoundException e){
            System.out.println("Error: File not found");
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            System.out.println("Error in parsing book price: ensure correct format");
            e.printStackTrace();
        }
    }
    
    /**
    * Loads customers from customers.txt and adds them to the bookstore's registered customer list
    * converts text data into customer objects and adds them to the list
    */
    public void loadCustomers(){
        File customerstext = new File("customers.txt");
        
        if (!customerstext.exists()){
            System.out.println("customers.txt was not found");
            return;
        }
        try(Scanner scanner = new Scanner(customerstext)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] details = line.split(",");
                
                if (details.length == 3){
                    String username = details[0].trim();
                    String password = details[1].trim();
                    int points = Integer.parseInt(details[2].trim());
                    Customer customer = new Customer(username, password, points);
                    customerList.add(customer);
                }
                else{
                    System.out.println("Skipping incorrect customer entry: " + line);
                }
            }
        }
        
        catch (FileNotFoundException e){
            System.out.println("Error: File not found");
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            System.out.println("Error in parsing customer Points: ensure correct format");
            e.printStackTrace();
        }
    }
}
