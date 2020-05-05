package observer;

import models.Book;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<LibrarianObserver> observers = new ArrayList<>();

    // MODIFIES: observers
    // EFFECTS: adds an observer to the list
    public void addObserver(LibrarianObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    // EFFECTS: notifies all observers
    public void notifyObservers(Book book) {
        for (LibrarianObserver observer : observers) {
            observer.update(book);
        }
    }
}
