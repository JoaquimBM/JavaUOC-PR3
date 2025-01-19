package uoc.ds.pr;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.nonlinear.AVLTree;
import edu.uoc.ds.adt.nonlinear.HashTable;
import edu.uoc.ds.adt.nonlinear.graphs.Edge;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.util.*;

import java.time.LocalDate;


public class LibraryPR3Impl extends  LibraryPR2Impl implements LibraryPR3 {
    private DSLinkedList<Copy> destroyedCopys;
    private OrderedVector<Book> bestRatedBooks;

    private AVLTree<Book> bookAVLTree;
    private OrderedVector<Reader> bestRatedReaders;

    private DSArray<Theme> themeVector;

    private HashTable<String,Author> authorHashTable;

    public LibraryPR3Impl() {

        bestRatedBooks = new OrderedVector<>(6,Book.CMP_RATING);
        destroyedCopys = new DSLinkedList<>(Copy.CMP_BOOKID);
        bookAVLTree = new AVLTree<>(Book.CMP);
        bestRatedReaders = new OrderedVector<>(10, Reader.CMP_SCORE);
        authorHashTable = new HashTable<>();
        themeVector = new DSArray<>(MAX_NUM_THEMES);
    }

    @Override
    public void addTheme(String themeId, String name) {
        Theme a = new Theme(themeId, name);
        themeVector.put(themeId, a);
    }

    @Override
    public void addAuthor(String uniqueCode, String name, String surname) {
        Author a = new Author(uniqueCode, name, surname);
        authorHashTable.put(uniqueCode, a);
    }

    @Override
    public Iterator<Copy> getCopies(String bookId) throws NoBookException, NoCopiesException {
        Book a = new Book(null,0,bookId,null,null);
        Book bookFound = bookAVLTree.get(a);
        Iterator<Copy> copiesIterator = null;
        if (bookFound != null) {
            copiesIterator = bookFound.getCopies();
        }else{
            throw new NoBookException();
        }
        if (copiesIterator == null) {
            throw new NoCopiesException();
        }
        return copiesIterator;
    }

    @Override
    public void createRequest(String readerId, String copyId, LocalDate date) throws ReaderNotFoundException, CopyNotFoundException,ReaderAlreadyHasRequest4Copy, ReaderAlreadyHasLoan4Copy {
        // TO-DO
    }

    @Override
    public Iterator<Book> getBooksByTheme(String themeId) throws NoBookException {
         Iterator<Book> a = themeVector.get(themeId).getBooksByTheme();

         if (a == null) {
             throw new NoBookException();
         }

        return a;
    }

    @Override
    public Iterator<Book> getBooksByAuthor(String uniqueCode) throws NoBookException {
        Iterator<Book> a = authorHashTable.get(uniqueCode).getBooks();
        if (a == null) {
            throw new NoBookException();
        }
        return a;
    }

    @Override
    public Level getReaderLevel(String readerId) throws ReaderNotFoundException {
        Reader a = getReader(readerId);
        if (a == null) {
            throw new ReaderNotFoundException();
        }
      return LevelHelper.getLevel(a.getReaderScore());

    }

    @Override
    public void addReview(String bookId, String readerId, int rate, String comment)throws RateOutOfRangeException, ReaderNotFoundException, BookNotFoundException, ReaderNotAssociatedWithBookException, UserAlreadyRatedBookException {
        // COmprobar que la nota no se vaya de 5
        // Comprobar que el libro existe
        // Comprobar que el lector existe
        // Comprobar que el array de reviews no tiene el libro que se quiere dejar una reseña
        // Comprobar que el usuario se ha leido el libro
        // Añadir la review

        if (rate < 1 || rate >5){
            throw new RateOutOfRangeException();
        }
        Book a = new Book(null,0,bookId,null,null);
        Book bookFound = bookAVLTree.get(a);
        if (bookFound != null) {
            throw new BookNotFoundException();
        }





    }


    @Override
    public Iterator<Copy> getDestroyedCopies() throws NoBookException {
        if (destroyedCopys.isEmpty()) {
            throw new NoBookException();
        }
        return destroyedCopys.values();
    }

    @Override
    public Iterator<Ratings> getReviewsByBook(String bookId) throws BookNotFoundException, NoReviewsException {
        Book a = new Book(null,0,bookId,null,null);
        Book bookFound = bookAVLTree.get(a);
        if (bookFound == null) {
            throw new BookNotFoundException();
        }
        if (bookFound.getRatings().isEmpty()) {
            throw new NoReviewsException();
        }
        return bookFound.getRatings().values();

    }

    @Override
    public Iterator<Book> best5Books() throws NoBookException {
        if (bestRatedBooks.isEmpty()) {
            throw new NoBookException();
        }
        return bestRatedBooks.values();
    }

    @Override
    public Iterator<Reader> best5Readers() throws NoReaderException {
        if (bestRatedReaders.isEmpty()) {
            throw new NoReaderException();
        }
        return bestRatedReaders.values();
    }

    @Override
    public Iterator<Copy> getRecommendationsByBook(String bookId) throws NoBookException {
        // TO-DO
    }

    @Override
    public Iterator<Copy> getRecommendationsByReader(String readerId) throws ReaderNotFoundException, NoBookException {
        // TO-DO
    }

    @Override
    public Iterator<Author> getRecommendedAuthors(String uniqueCode) throws AuthorNotFoundException, NoAuthorException {
        // TO-DO
    }



    public int numThemes() {
        return themeVector.size();
    }

    public Theme getTheme(String id) {
       return themeVector.get(id);
    }

    public int numAuthors() {
        return authorHashTable.size();
    }

    public Author getAuthor(String id) {
        return authorHashTable.get(id);
    }

    public Edge<Integer, Copy> getEdge(String bookId1, String bookId2) {

        return null;
    }
}
