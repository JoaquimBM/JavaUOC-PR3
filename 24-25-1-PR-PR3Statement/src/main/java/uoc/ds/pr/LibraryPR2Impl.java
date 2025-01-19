package uoc.ds.pr;

import java.time.LocalDate;

import edu.uoc.ds.adt.nonlinear.AVLTree;
import edu.uoc.ds.adt.nonlinear.HashTable;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.util.BookWareHouse;
import uoc.ds.pr.util.DSArray;
import uoc.ds.pr.util.DSLinkedList;
import uoc.ds.pr.util.OrderedVector;


public class LibraryPR2Impl implements Library {
    private HashTable<String,Reader> readers;
    private DSArray<Worker> workers;
    private BookWareHouse bookWareHouse;
    private DSLinkedList<CatalogedCopy> catalogedCopies;
    private AVLTree<Loan> loanList;

    private OrderedVector<Reader> mostFrequentReader;
    private OrderedVector<CatalogedCopy> mostReadBook;

    private int numCatalogedBooks;

    public LibraryPR2Impl() {
        readers = new HashTable<>();
        workers = new DSArray<>(MAX_NUM_WORKERS);
        bookWareHouse = new BookWareHouse();
        catalogedCopies = new DSLinkedList<>(CatalogedCopy.CMP);
        loanList = new AVLTree<>(Loan.CMP);
        mostFrequentReader = new OrderedVector<>(MAX_NUM_READERS,Reader.CMP_V);
        // The number of stored books is unknown and relatively small, in the range of a few hundred,
        mostReadBook = new OrderedVector<>(300, CatalogedCopy.CMP_V);
    }

    @Override
    public void addReader(String id, String name, String surname, String docId, LocalDate birthDate, String birthPlace, String address, int points) {
        Reader reader = getReader(id);
        if (reader == null) {
            reader = new Reader(id, name, surname, docId, birthDate, birthPlace, address);
            this.readers.put(id, reader);
        }
        else {
            reader.update(name, surname, docId, birthDate, birthPlace, address);
        }
    }

    public void addWorker(String id, String name, String surname) {
        Worker worker = getWorker(id);
        if (worker == null) {
            worker = new Worker(id, name, surname);
            this.workers.put(id, worker);
        }
        else {
            worker.update(name, surname);
        }
    }

    public void storeCopy(String copyId, String title, String publisher, String edition, int publicationYear, String isbn, String author, String theme) throws AuthorNotFoundException, ThemeNotFoundException {

        bookWareHouse.storeBook(copyId, title, publisher, edition, publicationYear, isbn, author, theme);
    }


    public Copy catalogCopy(String workerId) throws NoBookException, WorkerNotFoundException {
        Worker worker = getWorker2(workerId);

        if (bookWareHouse.isEmpty()) {
            throw new NoBookException();
        }

        Copy b = bookWareHouse.getBookPendingCataloging();
        CatalogedCopy catalogedCopies = this.catalogedCopies.get(new CatalogedCopy(b));
        if (catalogedCopies == null) {
            catalogedCopies = new CatalogedCopy(b);
            this.catalogedCopies.insertEnd(catalogedCopies);
            worker.catalogBook(catalogedCopies);
        }
        else {
            catalogedCopies.incCopies();
            worker.incTotalCatalogBooks();
        }
        numCatalogedBooks++;

        return b;
    }

    @Override
    public Request lendCopy(String loanId, String copyId, String workerId, LocalDate date, LocalDate expirationDate)throws CopyNotFoundException, WorkerNotFoundException, MaximumNumberOfBooksException, NoRequestException, CopyNotAvailableException {

    }

    public Loan giveBackBook(String loanId, LocalDate date, CopyReturnStatus status) throws LoanNotFoundException {
        Loan loan = getLoan2(loanId);
        Worker worker = loan.getWorker();

        if (loan.isDelayed(date)) {
            loan.setState(LoanState.DELAYED);
        }
        else {
            loan.setState(LoanState.COMPLETED);
        }

        worker.addClosedLoan(loan);
        Reader reader = loan.getReader();
        reader.addClosedLoan(loan);

        return loan;
    }

    public int timeToBeCataloged(String bookId, int lotPreparationTime, int bookCatalogTime) throws BookNotFoundException, InvalidLotPreparationTimeException, InvalidCatalogTimeException {
        if (lotPreparationTime<0) {
            throw new InvalidLotPreparationTimeException();
        }
        if (bookCatalogTime<0) {
            throw new InvalidCatalogTimeException();
        }

        BookWareHouse.Position position = bookWareHouse.getPosition(bookId);
        if (position == null) {
            throw new BookNotFoundException();
        }

        int previousStacks = position.getNumStack(); // + 1 ;
        int numberInStack = position.getNum() + 1;

        int t1 = previousStacks * (lotPreparationTime + (MAX_BOOK_STACK * bookCatalogTime));
        int t2 = lotPreparationTime + numberInStack * bookCatalogTime;
        int t = t1 + t2;
        return t;
    }

    public Iterator<Loan> getAllLoansByReader(String readerId) throws NoLoansException {
        Reader reader = getReader(readerId);
        Iterator<Loan> it = reader.getAllLoans();
        if (!it.hasNext()) {
            throw new NoLoansException();
        }
        return it;
    }

    public Iterator<Loan> getAllLoansByState(String readerId, LoanState state) throws NoLoansException {
        Reader reader = getReader(readerId);
        Iterator<Loan> it = reader.getAllLoansByState(state);
        if (!it.hasNext()) {
            throw new NoLoansException();
        }
        return it;
    }

    public Iterator<Loan> getAllLoansByCopy(String copyId) throws NoLoansException {
        CatalogedCopy catalogedCopies = getCatalogedBook(copyId);
        Iterator<Loan> it = null;
        if (catalogedCopies !=null) {
            it = catalogedCopies.getAllLoans();
            if (!it.hasNext()) {
                throw new NoLoansException();
            }
        }
        else  throw new NoLoansException();
        return it;
    }

    public Reader getReaderTheMost() throws NoReaderException {
        if (mostReadBook.isEmpty()) {
            throw new NoReaderException();
        }
        return mostFrequentReader.elementAt(0);
    }

    public Copy getMostReadBook() throws NoBookException {
        if (mostReadBook.isEmpty()) {
            throw new NoBookException();
        }
        CatalogedCopy catalogedCopies = mostReadBook.elementAt(0);
        return catalogedCopies.getBook();
    }


    /***********************************************************************************/
    /******************** AUX OPERATIONS  **********************************************/
    /***********************************************************************************/
    @Override
    public Reader getReader(String id) {
        return readers.get(id);
    }

    private Reader getReader2(String id) throws ReaderNotFoundException {
        Reader reader = getReader(id);
        if (reader == null) {
            throw new ReaderNotFoundException();
        }
        return reader;
    }

    @Override
    public int numReaders() {
        return readers.size();
    }

    @Override
    public Worker getWorker(String id) {
        return workers.get(id);
    }

    private Worker getWorker2(String id) throws WorkerNotFoundException {
        Worker worker = getWorker(id);
        if (worker == null) {
            throw new WorkerNotFoundException();
        }
        return worker;
    }

    @Override
    public int numWorkers() {
        return workers.size();
    }

    public int numBooks() {
        return bookWareHouse.numBooks();
    }

    public int numStacks() {
        return bookWareHouse.numStacks();
    }

    protected CatalogedCopy getCatalogedBook(String bookId) {
        CatalogedCopy a = this.catalogedCopies.get(new CatalogedCopy(bookId), CatalogedCopy.CMP_BOOKID);
        return a;
    }

    public CatalogedCopy getCatalogedBook2(String bookId) throws BookNotFoundException {
        CatalogedCopy catalogedCopies = getCatalogedBook(bookId);
        if (catalogedCopies == null) {
            throw new BookNotFoundException();
        }
        return catalogedCopies;
    }

    public int numCatalogBooks() {
        return numCatalogedBooks;
    }

    public int numCopiesToCatalog() {
        return bookWareHouse.numBooks();
    }

    public int numCatalogCopies() {
        return catalogedCopies.size();
    }


    public int numCatalogCopiesByWorker(String workerId) {

    }

    public int numCatalogBooksByWorker(String workerId) {
        Worker worker = this.workers.get(workerId);
        return (worker!=null? worker.numCatalogBooks():0);
    }


    public int numCopiesToCatalog(String bookId) {

    }

    public int numAvailableCopies(String bookId) {

    }

    public Loan getLoan(String loanId) {
        Loan loan = loanList.get(new Loan(loanId));
        return loan;
    }

    public Loan getLoan2(String loanId) throws LoanNotFoundException {
        Loan loan = getLoan(loanId);
        if (loan == null) {
            throw new LoanNotFoundException();
        }
        return loan;
    }

    public int numLoans() {
        return loanList.size();
    }

    public int numLoansByWorker(String workerId) {
        Worker worker = getWorker(workerId);
        return (worker!=null?worker.numLoans():0);
    }

    public int numClosedLoansByWorker(String workerId) {
        Worker worker = getWorker(workerId);
        return (worker!=null?worker.numClosedLoans():0);
    }

    public int numLoansByCopy(String copyId) {
        CatalogedCopy a = catalogedCopies.get(new CatalogedCopy(copyId));
        return a.getNumOfLoans();
    }

    public int numCurrentLoansByReader(String readerId) {
       return readers.get(readerId).numAllLoans();
    }

    public int numClosedLoansByReader(String readerId) {
        Reader reader = getReader(readerId);
        return (reader!=null?reader.numLoans():0);
    }

    public int numDestroyedLoans() {

    }
}
