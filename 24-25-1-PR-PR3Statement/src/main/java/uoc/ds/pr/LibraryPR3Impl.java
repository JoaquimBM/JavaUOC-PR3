package uoc.ds.pr;

import edu.uoc.ds.adt.nonlinear.PriorityQueue;
import edu.uoc.ds.adt.nonlinear.graphs.Edge;
import edu.uoc.ds.adt.nonlinear.graphs.Vertex;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.adt.sequential.Set;
import edu.uoc.ds.adt.sequential.SetLinkedListImpl;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;

import java.time.LocalDate;

public class LibraryPR3Impl extends  LibraryPR2Impl implements LibraryPR3 {


    public LibraryPR3Impl() {
        // TO-DO
    }

    @Override
    public void addTheme(String themeId, String name) {
        // TO-DO
    }

    @Override
    public void addAuthor(String uniqueCode, String name, String surname) {
        // TO-DO
    }

    @Override
    public Iterator<Copy> getCopies(String bookId) throws NoBookException, NoCopiesException {
        // TO-DO
    }

    @Override
    public void createRequest(String readerId, String copyId, LocalDate date) throws ReaderNotFoundException, CopyNotFoundException,
            ReaderAlreadyHasRequest4Copy, ReaderAlreadyHasLoan4Copy {
        // TO-DO
    }

    @Override
    public Iterator<Book> getBooksByTheme(String themeId) throws NoBookException {
        // TO-DO
    }

    @Override
    public Iterator<Book> getBooksByAuthor(String uniqueCode) throws NoBookException {
        // TO-DO
    }

    @Override
    public Level getReaderLevel(String readerId) throws ReaderNotFoundException {
        // TO-DO
    }

    @Override
    public void addReview(String bookId, String readerId, int rate, String comment)
            throws RateOutOfRangeException, ReaderNotFoundException, BookNotFoundException,
            ReaderNotAssociatedWithBookException, UserAlreadyRatedBookException {
        // TO-DO
    }


    @Override
    public Iterator<Copy> getDestroyedCopies() throws NoBookException {
        // TO-DO
    }

    @Override
    public Iterator<Rating> getReviewsByBook(String bookId)
            throws BookNotFoundException, NoReviewsException {
        // TO-DO
    }

    @Override
    public Iterator<CatalogedBook> best5Books() throws NoBookException {
        // TO-DO
    }

    @Override
    public Iterator<Reader> best5Readers() throws NoReaderException {
        // TO-DO
    }

    @Override
    public Iterator<Book> getRecommendationsByBook(String bookId) throws NoBookException {
        // TO-DO
    }

    @Override
    public Iterator<Book> getRecommendationsByReader(String readerId) throws ReaderNotFoundException, NoBookException {
        // TO-DO
    }

    @Override
    public Iterator<Author> getRecommendedAuthors(String uniqueCode) throws AuthorNotFoundException, NoAuthorException {
        // TO-DO
    }



    public int numThemes() {
        // TO-DO
    }

    public Theme getTheme(String id) {
        // TO-DO
    }

    public int numAuthors() {
        // TO-DO
    }

    public Author getAuthor(String id) {
        // TO-DO
    }

    public Edge<Integer, Book> getEdge(String bookId1, String bookId2) {
        // TO-DO
    }
}
