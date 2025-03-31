import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
/*
  @Author: Hassan
*/
public class Main extends Application {

    private BookStore bookStore;

    @Override
    public void start(Stage primaryStage) {
        bookStore = new BookStore();
        bookStore.loadBooks();
        bookStore.loadCustomers();

        showLoginScreen(primaryStage);
    }

    private void showLoginScreen(Stage primaryStage) {
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Login");
        Label errorLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = userField.getText().trim();
            String password = passField.getText().trim();

            // Check for admin
            if (username.equals("admin") && password.equals("admin")) {
                Owner owner = new Owner(); // use Hardcoded admin/admind login
                showOwnerScreen(primaryStage, owner);
            } else {
                // Look up customer
                for (Customer customer : bookStore.getCustomers()) {
                    if (customer.getUsername().equals(username)
                            && customer.getPassword().equals(password)) {
                        showCustomerScreen(primaryStage, customer);
                        return;
                    }
                }
                errorLabel.setText("Invalid login.");
            }
        });
        VBox layout = new VBox(10, userLabel, userField, passLabel, passField, loginButton, errorLabel);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bookstore Login");
        primaryStage.show();
    }

    private void showOwnerScreen(Stage stage, Owner owner) {
        Label title = new Label("Welcome, Owner");

        Button booksButton = new Button("Books");
        Button customersButton = new Button("Customers");
        Button logoutButton = new Button("Logout");

        booksButton.setOnAction(e -> showOwnerBooksScreen(stage, owner));
        customersButton.setOnAction(e -> showOwnerCustomersScreen(stage, owner));
        logoutButton.setOnAction(e -> showLoginScreen(stage));

        VBox layout = new VBox(15, title, booksButton, customersButton, logoutButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }

    private void showOwnerBooksScreen(Stage stage, Owner owner) {
        // Book TableView
        TableView<Book> table = new TableView<>();
        ObservableList<Book> bookData = FXCollections.observableArrayList(bookStore.getBooks());

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(titleCol, priceCol);
        table.setItems(bookData);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Input fields
        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");

        // Layout
        HBox inputBox = new HBox(10, titleField, priceField, addButton);
        inputBox.setAlignment(Pos.CENTER);

        HBox actionBox = new HBox(10, deleteButton, backButton);
        actionBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15, table, inputBox, actionBox);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        // Add Book Actions
        addButton.setOnAction(e -> {
            String title = titleField.getText().trim();
            String priceStr = priceField.getText().trim();

            if (!title.isEmpty() && !priceStr.isEmpty()) {
                try {
                    double price = Double.parseDouble(priceStr);
                    Book newBook = new Book(title, price);
                    owner.addBook(bookStore, newBook);
                    bookStore.StoreBooks();
                    bookData.setAll(bookStore.getBooks());
                    titleField.clear();
                    priceField.clear();
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid Price.");
                }
            }
        });

        // Delete book Actionm
        deleteButton.setOnAction(e -> {
            Book selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                owner.deleteBook(bookStore, selected);
                bookStore.StoreBooks();
                bookData.setAll(bookStore.getBooks());
            }
        });

        // Bakc button returns to Owner start screen
        backButton.setOnAction(e -> showOwnerScreen(stage, owner));

        stage.setScene(new Scene(layout, 500, 400));
    }

    private void showOwnerCustomersScreen(Stage stage, Owner owner) {
        // Table of customers
        TableView<Customer> table = new TableView<>();
        ObservableList<Customer> customerData = FXCollections.observableArrayList(bookStore.getCustomers());

        TableColumn<Customer, String> userCol = new TableColumn<>("Username");
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Customer, String> passCol = new TableColumn<>("Password");
        passCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Customer, Integer> pointsCol = new TableColumn<>("Points");
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));

        TableColumn<Customer, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(userCol, passCol, pointsCol, statusCol);
        table.setItems(customerData);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Add/Delete Fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");

        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");

        HBox inputBox = new HBox(10, usernameField, passwordField, addButton);
        inputBox.setAlignment(Pos.CENTER);

        HBox controls = new HBox(10, deleteButton, backButton);
        controls.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15, table, inputBox, controls);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        // Add customer action
        addButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            if (!username.isEmpty() && !password.isEmpty()) {
                Customer newCustomer = new Customer(username, password, 0);
                owner.addCustomer(bookStore, newCustomer);
                customerData.setAll(bookStore.getCustomers());
                usernameField.clear();
                passwordField.clear();
            }
        });

        // Delete customer action
        deleteButton.setOnAction(e -> {
            Customer selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                owner.deleteCustomer(bookStore, selected);
                bookStore.StoreCustomers();
                customerData.setAll(bookStore.getCustomers());
            }
        });

        // Back button
        backButton.setOnAction(e -> showOwnerScreen(stage, owner));

        stage.setScene(new Scene(layout, 600, 400));
    }

    private void showCustomerScreen(Stage stage, Customer customer) {

        Label welcomeLabel = new Label("Welcome " + customer.getUsername()
                + ". You have " + customer.getPoints() + " points. Your status is " + customer.getStatus());

        // Table setup
        TableView<SelectableBook> bookTable = new TableView<>();
        ObservableList<SelectableBook> bookData = FXCollections.observableArrayList();

// Populate wrapped book objects
        for (Book b : bookStore.getBooks()) {
            bookData.add(new SelectableBook(b));
        }

        TableColumn<SelectableBook, String> titleCol = new TableColumn<>("Book Name");
        titleCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBook().getTitle()));

        TableColumn<SelectableBook, Double> priceCol = new TableColumn<>("Book Price");
        priceCol.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getBook().getPrice()).asObject());

        TableColumn<SelectableBook, Boolean> selectCol = new TableColumn<>("Select");
        selectCol.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));

        bookTable.getColumns().addAll(titleCol, priceCol, selectCol);
        bookTable.setItems(bookData);
        bookTable.setEditable(true);

        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Buttons
        Button buyButton = new Button("Buy");
        Button redeemButton = new Button("Redeem Points and Buy");
        Button logoutButton = new Button("Logout");

        HBox buttonBox = new HBox(10, buyButton, redeemButton, logoutButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15, welcomeLabel, bookTable, buttonBox);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        // Scene
        Scene scene = new Scene(layout, 650, 450);
        stage.setScene(scene);

        // TODO (next step): Hook up Buy, Redeem, Logout functionality
        buyButton.setOnAction(e -> {
            ArrayList<Book> selectedBooksList = new ArrayList<>();
            for (SelectableBook sb : bookData) {
                if (sb.isSelected()) {
                    selectedBooksList.add(sb.getBook());  // ? correct one
                }
            }
            if (!selectedBooksList.isEmpty()) {
                showCustomerCostScreen(stage, customer, selectedBooksList, false);
            }
        });

        logoutButton.setOnAction(e -> {
            showLoginScreen(stage);
        });

        redeemButton.setOnAction(e -> {
            ArrayList<Book> selectedBooksList = new ArrayList<>();
            for (SelectableBook sb : bookData) {
                if (sb.isSelected()) {
                    selectedBooksList.add(sb.getBook());
                }
            }
            if (!selectedBooksList.isEmpty()) {
                showCustomerCostScreen(stage, customer, selectedBooksList, true);
            }
        });
    }

    private void showCustomerCostScreen(Stage stage, Customer customer, ArrayList<Book> purchasedBooks, boolean usePoints) {
        double originalTotal = purchasedBooks.stream().mapToDouble(Book::getPrice).sum();
        double finalTotal = originalTotal;

        Transaction transaction = new Transaction(customer, purchasedBooks, !usePoints);

        if (usePoints) {
            finalTotal = customer.redeemPoints(transaction); // Points get consumed
        }

        // Don't add points here manually
        //customer.buyBook(purchasedBooks); // Let this internally handle the transaction & points
        bookStore.StoreCustomers(); // Save state

        // Summary screen
        String summary = "Transaction completed successfully!\n\n"
                + "Total Cost: $" + String.format("%.2f", finalTotal) + "\n"
                + "New Points: " + customer.getPoints() + "\n"
                + "Status: " + customer.getStatus();

        Label summaryLabel = new Label(summary);
        summaryLabel.setWrapText(true);

        Button backButton = new Button("Back to Bookstore");
        Button logoutButton = new Button("Logout");

        backButton.setOnAction(e -> showCustomerScreen(stage, customer));
        logoutButton.setOnAction(e -> showLoginScreen(stage));

        HBox buttons = new HBox(15, backButton, logoutButton);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, summaryLabel, buttons);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(layout, 400, 250));
    }


    @Override
    public void stop() {
        // Save data if necessary
        bookStore.StoreBooks();
        bookStore.StoreCustomers();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
