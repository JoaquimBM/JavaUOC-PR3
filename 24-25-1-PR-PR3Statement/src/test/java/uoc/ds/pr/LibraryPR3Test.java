package uoc.ds.pr;

import edu.uoc.ds.traversal.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;

public class LibraryPR3Test extends LibraryPR2Test {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        super.theLibrary = FactoryLibrary.getLibrary();
    }


    @Test
    public void getCopiesTest() throws DSException  {
        Assert.assertThrows(NoBookException.class, () ->
                theLibrary.getCopies("XXXXXX"));
        ;
        super.catalogCopyTest();

        Iterator<Copy> it = theLibrary.getCopies("978-0451530960");

        Assert.assertTrue(it.hasNext());
        Copy copy = it.next();
        Assert.assertEquals("JV2c", copy.getCopyId());

        Assert.assertTrue(it.hasNext());
        copy = it.next();
        Assert.assertEquals("JV2b", copy.getCopyId());
    }

    @Test
    public void getBooksByThemeTest() throws DSException {


        super.storeCopyTest();

        Assert.assertThrows(NoBookException.class, () ->
                theLibrary.getBooksByTheme("THEME10"));

        Iterator<Copy> it = theLibrary.getBooksByTheme("THEME21");

        Assert.assertTrue(it.hasNext());
        Copy copy1 = it.next();
        Assert.assertEquals("978-0140433340", copy1.getIsbn());
        Assert.assertEquals("Love and Friendship", copy1.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy2 = it.next();
        Assert.assertEquals("978-0141439518", copy2.getIsbn());
        Assert.assertEquals("Pride and Prejudice", copy2.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy3 = it.next();
        Assert.assertEquals("978-0141439587", copy3.getIsbn());
        Assert.assertEquals("Emma", copy3.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy4 = it.next();
        Assert.assertEquals("978-0141439686", copy4.getIsbn());
        Assert.assertEquals("Persuasion", copy4.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy5 = it.next();
        Assert.assertEquals("978-0141439808", copy5.getIsbn());
        Assert.assertEquals("Mansfield Park", copy5.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy6 = it.next();
        Assert.assertEquals("978-0743477116", copy6.getIsbn());
        Assert.assertEquals("Romeo and Juliet", copy6.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy7 = it.next();
        Assert.assertEquals("978-0743477543", copy7.getIsbn());
        Assert.assertEquals("A Midsummer Night's Dream", copy7.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy8 = it.next();
        Assert.assertEquals("978-0743482752", copy8.getIsbn());
        Assert.assertEquals("Much Ado About Nothing", copy8.getTitle());

        Assert.assertFalse(it.hasNext());

    }

    @Test
    public void getBooksByAuthorTest() throws DSException {

        super.storeCopyTest();

        Assert.assertThrows(NoBookException.class, () ->
                theLibrary.getBooksByAuthor("GAVO"));



        Iterator<Copy> it = theLibrary.getBooksByAuthor("JA");

        Assert.assertTrue(it.hasNext());
        Copy copy1 = it.next();
        Assert.assertEquals("978-0141439518", copy1.getIsbn());
        Assert.assertEquals("Pride and Prejudice", copy1.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy2 = it.next();
        Assert.assertEquals("978-0141439662", copy2.getIsbn());
        Assert.assertEquals("Sense and Sensibility", copy2.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy3 = it.next();
        Assert.assertEquals("978-0141439587", copy3.getIsbn());
        Assert.assertEquals("Emma", copy3.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy4 = it.next();
        Assert.assertEquals("978-0141439808", copy4.getIsbn());
        Assert.assertEquals("Mansfield Park", copy4.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy5 = it.next();
        Assert.assertEquals("978-0141439686", copy5.getIsbn());
        Assert.assertEquals("Persuasion", copy5.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy6 = it.next();
        Assert.assertEquals("978-0141439792", copy6.getIsbn());
        Assert.assertEquals("Northanger Abbey", copy6.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy7 = it.next();
        Assert.assertEquals("978-0140433203", copy7.getIsbn());
        Assert.assertEquals("Lady Susan", copy7.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy8 = it.next();
        Assert.assertEquals("N/A", copy8.getIsbn());
        Assert.assertEquals("The Watsons", copy8.getTitle());

        Assert.assertTrue(it.hasNext());
        Copy copy9 = it.next();
        Assert.assertEquals("978-0140433340", copy9.getIsbn());
        Assert.assertEquals("Love and Friendship", copy9.getTitle());

        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void getLevelTest() throws DSException {
        Assert.assertThrows(ReaderNotFoundException.class, () ->
            theLibrary.getReaderLevel("XXXX"));

        Assert.assertEquals(LibraryPR3.Level.MUGGLE, theLibrary.getReaderLevel("readerId1"));
        Assert.assertEquals(LibraryPR3.Level.ASLAN, theLibrary.getReaderLevel("readerId2"));
        Assert.assertEquals(LibraryPR3.Level.HOBBIT, theLibrary.getReaderLevel("readerId3"));
        Assert.assertEquals(LibraryPR3.Level.MUGGLE, theLibrary.getReaderLevel("readerId4"));

    }

    @Test
    public void addReviewTest() throws DSException {
        Assert.assertThrows(RateOutOfRangeException.class, () ->
                theLibrary.addReview("978-1605062234", "readerId2", -5, "blah blah blah"));
        Assert.assertThrows(RateOutOfRangeException.class, () ->
                theLibrary.addReview("978-1605062234", "readerId2", 12, "blah blah blah"));

        super.giveBackBookTest();

        Assert.assertThrows(ReaderNotFoundException.class, () ->
                theLibrary.addReview("978-1605062234", "XXXXX", 5, "blah blah blah"));
        Assert.assertThrows(BookNotFoundException.class, () ->
                theLibrary.addReview("XXXXXX", "readerId1", 5, "blah blah blah"));
        Assert.assertThrows(ReaderNotAssociatedWithBookException.class, () ->
                theLibrary.addReview("978-1605062234", "readerId1", 5, "blah blah blah"));

        theLibrary.addReview("978-1605062234", "readerId2", 5, "blah blah blah");

        Assert.assertThrows(UserAlreadyRatedBookException.class, () ->
                theLibrary.addReview("978-1605062234", "readerId2", 5, "blah blah blah"));

    }


    @Test
    public void getDestroyedCopiesTest() throws DSException {
        Assert.assertThrows(NoBookException.class, () ->
                theLibrary.getDestroyedCopies());

        super.giveBackBookTest();

        Iterator<Copy> it = theLibrary.getDestroyedCopies();
        Assert.assertTrue(it.hasNext());
        Copy copy = it.next();
        Assert.assertEquals("JV2b", copy.getCopyId());
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void getReviewsByBookTest() throws DSException {
        Assert.assertThrows(BookNotFoundException.class, () ->
                theLibrary.getReviewsByBook("XXXX"));

        this.addReviewTest();

        Assert.assertThrows(NoReviewsException.class, () ->
                theLibrary.getReviewsByBook("978-1435149408"));

        Iterator<Ratings> it = theLibrary.getReviewsByBook("978-1605062234");
        Assert.assertTrue(it.hasNext());
        Ratings rating = it.next();
        Assert.assertEquals(5, rating.getRate());
        Assert.assertEquals("blah blah blah", rating.getComment());
        Assert.assertEquals("readerId2", rating.getReader().getId());

        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void best5BooksTest() throws DSException {
        Assert.assertThrows(NoBookException.class, () ->
                theLibrary.best5Books());


        addReviewTest();

        Iterator<CatalogedCopy> it = theLibrary.best5Books();
        Assert.assertTrue(it.hasNext());
        CatalogedCopy catalogedCopies = it.next();
        Assert.assertEquals("978-1605062234", catalogedCopies.getIsbn());
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void best5ReadersTest() throws DSException {
        Assert.assertThrows(NoReaderException.class, () ->
                theLibrary.best5Readers());


        addReviewTest();

        Iterator<Reader> it = theLibrary.best5Readers();
        Assert.assertTrue(it.hasNext());
        Reader reader = it.next();
        Assert.assertEquals("readerId1", reader.getId());
        Assert.assertEquals(-1, reader.getPoints());

        Assert.assertTrue(it.hasNext());
        reader = it.next();
        Assert.assertEquals("readerId2", reader.getId());
        Assert.assertEquals(-31, reader.getPoints());

        Assert.assertFalse(it.hasNext());
    }
}
