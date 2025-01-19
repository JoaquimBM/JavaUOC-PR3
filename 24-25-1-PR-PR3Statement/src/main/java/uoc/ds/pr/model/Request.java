package uoc.ds.pr.model;

import java.time.LocalDate;
import java.util.Comparator;

public class Request {
    public static final Comparator<Request> CMP = (cb1, cb2)->cb1.getDate().compareTo(cb2.getDate());
    String readerId;
    String copyId;
    LocalDate date;
    public Request(String readerId, String copyId, LocalDate date) {
        this.readerId = readerId;
        this.copyId = copyId;
        this.date = date;
    }
    public String getReaderId() {
        return readerId;
    }
    public String getCopyId() {
        return copyId;
    }
    public LocalDate getDate() {
        return date;
    }

}
