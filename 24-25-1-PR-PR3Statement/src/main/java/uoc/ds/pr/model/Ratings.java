package uoc.ds.pr.model;

import java.util.Comparator;

public class Ratings {
    public static final Comparator<Ratings> CMP = (cb1, cb2)->cb1.getBookId().compareTo(cb2.getBookId());
    String reviewId;
    String bookId;
    String readerId;
    int rate;
    String comment;
    public Ratings(String bookId, String readerId, int rate, String comment) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.rate = rate;
        this.comment = comment;
    }
    public String getBookId() {
        return bookId;
    }
    public String getReaderId() {
        return readerId;
    }
    public int getRate() {
        return rate;
    }
    public String getComment() {
        return comment;
    }
    public Reader getReader(){


    }


}
