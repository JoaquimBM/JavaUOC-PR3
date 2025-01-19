package uoc.ds.pr.model;

import edu.uoc.ds.adt.nonlinear.AVLTree;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.util.DSLinkedList;

import java.util.Comparator;

public class Book extends AbstractBook {
    public static final Comparator<Book> CMP_RATING = (cb1, cb2) -> Integer.compare(cb1.getRating(), cb2.getRating());
    public static final Comparator<Book> CMP = (cb1, cb2)->cb1.getIsbn().compareTo(cb2.getIsbn());
    private DSLinkedList<Copy> copies;
    private AVLTree<Ratings> ratings;
    private int rating;
    public Book(String title, int publicationYear, String isbn, String author, String theme) {
        super(title, publicationYear, isbn, author, theme);
        this.copies = new DSLinkedList<>(Copy.CMP_BOOKID);
        this.ratings = new AVLTree<>(Ratings.CMP);
    }

    public Iterator<Copy> getCopies() {
        return copies.values();
    }
    public AVLTree<Ratings> getRatings() {
        return ratings;
    }
    public int getRating() {
        return rating;
    }
}
