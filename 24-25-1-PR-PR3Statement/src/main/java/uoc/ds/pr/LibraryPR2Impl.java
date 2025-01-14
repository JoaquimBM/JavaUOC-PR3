package uoc.ds.pr;

import java.time.LocalDate;

import edu.uoc.ds.adt.nonlinear.Dictionary;
import edu.uoc.ds.adt.nonlinear.DictionaryAVLImpl;
import edu.uoc.ds.adt.nonlinear.HashTable;
import edu.uoc.ds.adt.nonlinear.graphs.UnDirectedGraph;
import edu.uoc.ds.adt.nonlinear.graphs.UnDirectedGraphImpl;
import edu.uoc.ds.adt.nonlinear.graphs.Vertex;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.util.BookWareHouse;
import uoc.ds.pr.util.DSArray;
import uoc.ds.pr.util.OrderedVector;

import static uoc.ds.pr.LibraryPR3.MAX_NUM_THEMES;


public class LibraryPR2Impl implements Library {

    public LibraryPR2Impl() {
        // TO-DO
    }

    @Override
    public void addReader(String id, String name, String surname, String docId, LocalDate birthDate, String birthPlace, String address, int points) {
        // TO-DO
    }

    public void addWorker(String id, String name, String surname) {
        // TO-DO
    }

    public void storeCopy(String copyId, String title, String publisher, String edition, int publicationYear, String isbn, String author, String theme) throws AuthorNotFoundException, ThemeNotFoundException {
        // TO-DO
    }


    public Copy catalogCopy(String workerId) throws NoBookException, WorkerNotFoundException {
        // TO-DO
    }

    @Override
    public Request lendCopy(String loanId, String copyId, String workerId, LocalDate date, LocalDate expirationDate)
            throws CopyNotFoundException, WorkerNotFoundException, MaximumNumberOfBooksException, NoRequestException,
            CopyNotAvailableException {
        // TO-DO
    }

    public Loan giveBackBook(String loanId, LocalDate date, CopyReturnStatus status) throws LoanNotFoundException {
        // TO-DO
    }

    public int timeToBeCataloged(String bookId, int lotPreparationTime, int bookCatalogTime) throws BookNotFoundException, InvalidLotPreparationTimeException, InvalidCatalogTimeException {
        // TO-DO
    }

    public Iterator<Loan> getAllLoansByReader(String readerId) throws NoLoansException {
        // TO-DO
    }

    public Iterator<Loan> getAllLoansByState(String readerId, LoanState state) throws NoLoansException {
        // TO-DO
    }

    public Iterator<Loan> getAllLoansByCopy(String copyId) throws NoLoansException {
        // TO-DO
    }

    public Reader getReaderTheMost() throws NoReaderException {
        // TO-DO
    }

    public Book getMostReadBook() throws NoBookException {
        // TO-DO
    }


    /***********************************************************************************/
    /******************** AUX OPERATIONS  **********************************************/
    /***********************************************************************************/
    @Override
    public Reader getReader(String id) {
        // TO-DO
    }

    private Reader getReader2(String id) throws ReaderNotFoundException {
        // TO-DO
    }

    @Override
    public int numReaders() {
        // TO-DO
    }

    @Override
    public Worker getWorker(String id) {
        // TO-DO
    }

    private Worker getWorker2(String id) throws WorkerNotFoundException {
        // TO-DO
    }

    @Override
    public int numWorkers() {
        // TO-DO
    }

    public int numBooks() {
        // TO-DO
    }

    public int numStacks() {
        // TO-DO
    }

    protected CatalogedBook getCatalogedBook(String bookId) {
        // TO-DO
    }

    public CatalogedBook getCatalogedBook2(String bookId) throws BookNotFoundException {
        // TO-DO
    }

    public int numCatalogBooks() {
        // TO-DO
    }

    public int numCopiesToCatalog() {
        // TO-DO
    }

    public int numCatalogCopies() {
        // TO-DO
    }


    public int numCatalogCopiesByWorker(String workerId) {
        // TO-DO
    }

    public int numCatalogBooksByWorker(String workerId) {
        // TO-DO
    }


    public int numCopiesToCatalog(String bookId) {
        // TO-DO
    }

    public int numAvailableCopies(String bookId) {
        // TO-DO
    }

    public Loan getLoan(String loanId) {
        // TO-DO
    }

    public Loan getLoan2(String loanId) throws LoanNotFoundException {
        // TO-DO
    }

    public int numLoans() {
        // TO-DO
    }

    public int numLoansByWorker(String workerId) {
        // TO-DO
    }

    public int numClosedLoansByWorker(String workerId) {
        // TO-DO
    }

    public int numLoansByCopy(String copyId) {
        // TO-DO
    }

    public int numCurrentLoansByReader(String readerId) {
        // TO-DO
    }

    public int numClosedLoansByReader(String readerId) {
        // TO-DO
    }

    public int numDestroyedLoans() {
        // TO-DO
    }
}
