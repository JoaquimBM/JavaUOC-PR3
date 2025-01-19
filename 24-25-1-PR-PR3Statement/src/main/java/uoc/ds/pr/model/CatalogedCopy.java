package uoc.ds.pr.model;

import edu.uoc.ds.adt.nonlinear.PriorityQueue;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.util.DSLinkedList;

import java.util.Comparator;

public class CatalogedCopy {
    public static final Comparator<CatalogedCopy> CMP = (cb1, cb2)->cb1.getIsbn().compareTo(cb2.getIsbn());
    public static final Comparator<CatalogedCopy> CMP_BOOKID = (cb1, cb2)->cb1.getBookId().compareTo(cb2.getBookId());
    public static final Comparator<CatalogedCopy> CMP_V = (cb1, cb2) -> Integer.compare(cb1.loanList.size(), cb2.loanList.size());

    public DSLinkedList<Loan> loanList;
    public PriorityQueue<Request> requestQueue;
    private Copy copy;

    private int totalCopies;
    private int copies;

    public CatalogedCopy(Copy copy) {
        this.copy = copy;
        this.copies = 1;
        this.loanList = new DSLinkedList<>(Loan.CMP);
        this.requestQueue = new PriorityQueue<>(Request.CMP);
    }

    public CatalogedCopy(String bookId) {
        this.copy = new Copy(bookId);
    }

    public void incCopies() {
        copies++;
        totalCopies++;
    }

    public String getBookId() {
        return (copy !=null? copy.getBookId():null);
    }

    public String getIsbn() {
        return (copy !=null? copy.getIsbn():null);
    }
    public String getTitle() {
        return (copy !=null? copy.getTitle():null);
    }

    public int numCopies() {
        return copies;
    }

    public void decreaseCopies() {
        copies--;
    }

    public int numLoans() {
        return this.loanList.size();
    }

    public void addLoan(Loan loan) {
        loanList.insertEnd(loan);
    }

    public Iterator<Loan> getAllLoans() {
        return loanList.values();
    }
    public int getNumOfLoans(){
        return loanList.size();
    }

    public Copy getBook() {
        return copy;
    }
}
