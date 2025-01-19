package uoc.ds.pr.model;


import edu.uoc.ds.adt.nonlinear.AVLTree;
import edu.uoc.ds.traversal.Iterator;

public class Theme {
     private String id;
     private String name;
     private AVLTree<Book> booksByTheme;

     public Theme(String id, String name) {
        id = this.id;
        name = this.name;
        this.booksByTheme = new AVLTree<>();
     }
     public String getId() {
         return id;
     }
     public String getName() {
         return name;
     }

    public Iterator<Book> getBooksByTheme() {
        return booksByTheme.values();
    }
}
