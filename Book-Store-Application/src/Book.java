public class Book {

	private String title;
	private double price;

	public Book(String title, double price) {
		this.title = title;
		this.price = price;
	}

	public String getDetails() {
		String str = String.format("Title: %s\nPrice: $%.2f", title, price);
		return str;
	}
}
