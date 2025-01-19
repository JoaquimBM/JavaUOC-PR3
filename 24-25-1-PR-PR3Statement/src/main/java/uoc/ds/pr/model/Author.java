package uoc.ds.pr.model;

import edu.uoc.ds.adt.nonlinear.AVLTree;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.util.DSLinkedList;

import java.util.Comparator;

public class Author {
    public static final Comparator<Author> CMP_AUTHORID = (cb1, cb2)->cb1.getAuthorId().compareTo(cb2.getAuthorId());

    String AuthorId;
    String Name;
    String Surname;
    AVLTree<Book> BooksTree;

    public Author(String AuthorId, String Name, String Surname) {
        this.AuthorId = AuthorId;
        this.Name = Name;
        this.Surname = Surname;
        this.BooksTree = new AVLTree<>(Book.CMP);
    }

    public String getAuthorId() {
        return AuthorId;
    }
    public String getName() {
        return Name;
    }
    public String getSurname() {
        return Surname;
    }
    public Iterator<Book> getBooks() {
        return BooksTree.values();
    }
    public void addBook(Book book) {
        BooksTree.add(book);
    }
}
