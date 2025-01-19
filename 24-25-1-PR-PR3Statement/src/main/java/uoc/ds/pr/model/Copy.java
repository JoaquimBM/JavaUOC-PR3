package uoc.ds.pr.model;

import java.util.Comparator;

public class Copy extends AbstractBook {
    public static final Comparator<Copy> CMP_BOOKID = (cb1, cb2)->cb1.getBookId().compareTo(cb2.getBookId());
    private String bookId;
    private String edition;
    private String publisher;

    public Copy(String bookId, String title, String publisher, String edition, int publicationYear, String isbn, String author, String theme) {
        super(title, publicationYear, isbn, author, theme);
        this.bookId = bookId;
        this.edition = edition;
        this.publisher = publisher;
    }

    public Copy(String bookId) {
        super(null, 0, null, null, null);
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
