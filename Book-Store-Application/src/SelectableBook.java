/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/**
 *
 * @author hselshik
 */


public class SelectableBook {
    private final Book book;
    private final BooleanProperty selected;

    public SelectableBook(Book book) {
        this.book = book;
        this.selected = new SimpleBooleanProperty(false);
    }

    public Book getBook() {
        return book;
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
