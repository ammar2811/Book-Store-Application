/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Khalid Guled
 */
public class BookStore {
    public List<Book> bookList = new ArrayList<>();
    public List<Customer> customerList = new ArrayList<>();
    
    public List<Book> getBooks(){
        return bookList;
    }
    
    public List<Customer> getCustomers(){
        return customerList;
    }
    
    public void addBook(Book b) {
        bookList.add(b);
    }
    
    public boolean deleteBook(Book b) {
        return bookList.remove(b);
    }
    
    public void addCustomer(Customer c) {
        customerList.add(c);
    }
    
    public boolean deleteCustomer(Customer c) {
        return customerList.remove(c);
    }
    
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
                    double points = Double.parseDouble(details[2].trim());
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
