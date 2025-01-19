package uoc.ds.pr;

import edu.uoc.ds.adt.nonlinear.graphs.Edge;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;

import java.time.LocalDate;

public interface LibraryPR3 extends Library {

    int MAX_NUM_THEMES = 30;

    int MIN_RATE = 1;
    int MAX_RATE = 5;

    public enum Level {
        ASLAN (0),
        WINDRUNNER (1),
        FREMEN (2),
        HOBBIT (3),
        OOMPA_LOOMPA (4),
        MUGGLE(5),
        TROLL(6);
        private final int value;

        Level(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public void addTheme(String themeId, String name);
    public void addAuthor(String uniqueCode, String name, String surname);
    public Iterator<Copy> getCopies(String bookId) throws NoBookException, NoCopiesException;
    public void createRequest(String readerId, String copyId, LocalDate date)
            throws ReaderNotFoundException, CopyNotFoundException,
            ReaderAlreadyHasRequest4Copy, ReaderAlreadyHasLoan4Copy;

    public Iterator<Book> getBooksByTheme(String themeId) throws NoBookException; // This and the function bellow changed from Copy->Book because it makes more sense even the function is Books// probably a typo in the big ADT picture this make more sense
    public Iterator<Book> getBooksByAuthor(String uniqueCode) throws NoBookException;// Also in a application perspective this makes more sense
    public Level getReaderLevel(String readerId) throws ReaderNotFoundException;
    public void addReview(String bookId, String readerId, int rate, String comment)
            throws RateOutOfRangeException, ReaderNotFoundException, BookNotFoundException, ReaderNotAssociatedWithBookException, UserAlreadyRatedBookException;
    public Iterator<Copy> getDestroyedCopies() throws NoBookException;
    public Iterator<Ratings> getReviewsByBook(String bookId) throws BookNotFoundException, NoReviewsException;
    public Iterator<Book> best5Books() throws NoBookException;
    public Iterator<Reader> best5Readers() throws NoReaderException;

    public Iterator<Copy> getRecommendationsByBook(String bookId) throws NoBookException;
    public Iterator<Copy> getRecommendationsByReader(String readerId) throws ReaderNotFoundException, NoBookException;
    public Iterator<Author> getRecommendedAuthors(String uniqueCode) throws AuthorNotFoundException, NoAuthorException;

    /***********************************************************************************/
    /******************** AUX OPERATIONS  **********************************************/
    /***********************************************************************************/

    public int numThemes();
    public Theme getTheme(String id);

    public int numAuthors();
    public  Author getAuthor(String id);

    public Edge<Integer, Copy> getEdge(String bookId1, String bookId2);

}
