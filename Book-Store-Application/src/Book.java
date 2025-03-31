/*
 * @Author: Hassan
 */
public class Book {

	private String title;
	private double price;

	public Book(String title, double price) {
		this.title = title;
		this.price = price;
	}

	public String getDetails() {
		String str = String.format("%s,%.2f", title, price);
		return str;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}
}
