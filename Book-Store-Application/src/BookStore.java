
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
 * Represents a Book store where all book and customer data is stored. could add
 * or remove book/customer
 * 
 */
public class BookStore {
    private List<Book> bookList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();

    /**
     * 
     * @return arrayList containing book data
     */
    public List<Book> getBooks() {
        return bookList;
    }

    /**
     * 
     * @return arrayList containing customer data
     */
    public List<Customer> getCustomers() {
        return customerList;
    }

    /**
     * Adds a book to the BookList array
     * 
     * @param b Book
     */
    public void addBook(Book b) {
        bookList.add(b);
    }

    /**
     * Deletes a book from the BookList array
     * 
     * @param b Book
     * @return true if book was deleted from arrayList. returns false if book was
     *         not deleted.
     */
    public boolean deleteBook(Book b) {
        return bookList.remove(b);
    }

    /**
     * Adds a customer to the CustomerList array
     * 
     * @param c Customer
     */
    public void addCustomer(Customer c) {
        customerList.add(c);
    }

    /**
     * Deletes a customer from the CustomerList array
     * 
     * @param c Customer
     * @return true if customer was deleted from arrayList. returns false if
     *         customer was not deleted.
     */
    public boolean deleteCustomer(Customer c) {
        return customerList.remove(c);
    }

    /**
     * Stores the list of books into a text file called books.txt
     * Book details are stored on separated lines.
     */
    public void StoreBooks() {
        File bookstext = new File("books.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookstext, false))) {
            for (Book book : bookList) {
                writer.write(book.getDetails());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StoreCustomers() {
        File customerstext = new File("customers.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerstext, false))) {
            for (Customer customer : customerList) {
                writer.write(customer.getUsername() + "," + customer.getPassword() + "," + customer.getPoints());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copies the book data stored in books.txt and places it inside of the bookList
     * Array.
     * needed to convert string data into book data type
     */
    public void loadBooks() {
        File bookstext = new File("books.txt");

        if (!bookstext.exists()) {
            System.out.println("books.txt was not found");
            return;
        }
        try (Scanner scanner = new Scanner(bookstext)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");

                if (details.length == 2) {
                    String title = details[0].trim();
                    double price = Double.parseDouble(details[1].trim());
                    Book book = new Book(title, price);
                    bookList.add(book);
                } else {
                    System.out.println("Skipping incorrect book entry: " + line);
                }
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error in parsing book price: ensure correct format");
            e.printStackTrace();
        }
    }

    /**
     * Copies the customer's data stored in customer.txt and places it inside of the
     * customerList Array.
     * needed to convert string data into customer data type
     */
    public void loadCustomers() {
        File customerstext = new File("customers.txt");

        if (!customerstext.exists()) {
            System.out.println("customers.txt was not found");
            return;
        }
        try (Scanner scanner = new Scanner(customerstext)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");

                if (details.length == 3) {
                    String username = details[0].trim();
                    String password = details[1].trim();
                    int points = Integer.parseInt(details[2].trim());
                    Customer customer = new Customer(username, password, points);
                    customerList.add(customer);
                } else {
                    System.out.println("Skipping incorrect customer entry: " + line);
                }
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error in parsing customer Points: ensure correct format");
            e.printStackTrace();
        }
    }
}
