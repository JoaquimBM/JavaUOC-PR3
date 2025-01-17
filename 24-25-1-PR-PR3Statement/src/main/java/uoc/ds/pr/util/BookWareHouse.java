package uoc.ds.pr.util;

import edu.uoc.ds.adt.sequential.Stack;
import edu.uoc.ds.adt.sequential.StackArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.model.Copy;


import static uoc.ds.pr.Library.MAX_BOOK_STACK;

public class BookWareHouse {

    QueueLinkedList<StackArrayImpl<Copy>> storedBooks;
    StackArrayImpl<Copy> lastStack;

    int numFullStacks;

    public BookWareHouse() {
        storedBooks = new QueueLinkedList<>();
        lastStack = null;
        numFullStacks = 0;
    }

    public StackArrayImpl newStack() {
        lastStack = new StackArrayImpl<Copy>(MAX_BOOK_STACK);
        storedBooks.add(lastStack);
        numFullStacks++;
        return lastStack;
    }

    public void storeBook (String bookId, String title, String publisher, String edition, int publicationYear, String isbn, String author, String theme) {

        storeBook(new Copy(bookId, title, publisher, edition, publicationYear, isbn, author, theme));

    }
    public void storeBook(Copy copy) {
        if (( lastStack == null) || (lastStack.isFull()) ) {
            lastStack = newStack();
        }
        lastStack.push(copy);
    }

    public Position getPosition(String bookId) {
        Iterator<StackArrayImpl<Copy>> it = this.storedBooks.values();
        boolean found = false;
        int numStack = 0;
        int num = 0;

        while (it.hasNext() && !found) {
            num = processStack(bookId, it.next());
            found = num >= 0;
            if (!found) {
                numStack++;
            }
        }
        return (found?new Position(numStack, num):null);
    }

    protected int processStack(String bookId, StackArrayImpl<Copy> stack) {
        int num = -1;
        boolean found = false;
        Iterator<Copy> it = stack.values();
        Copy copy = null;
        while (it.hasNext() && !found) {
            copy = it.next();
            found = copy.getBookId().equals(bookId);
            num++;
        }
        return (found?num:-1);


    }

    public Copy getBookPendingCataloging() {
        Copy pending = null;
        Stack<Copy> firstStack = this.storedBooks.peek();
        if (firstStack.size()>1) {
            pending = firstStack.pop();
        }
        else {
            pending = storedBooks.peek().pop();
            storedBooks.deleteFirst();
        }
        return pending;
    }

    public int numBooks() {
        int numBooks = 0;
        Iterator<StackArrayImpl<Copy>> it = storedBooks.values();
        while (it.hasNext()) {
            numBooks+=it.next().size();
        }

        return (numBooks);
    }

    public boolean isEmpty() {
        return (this.numBooks()==0);
    }

    public int numStacks() {
        return this.storedBooks.size();
    }


    public class Position {
        private int numStack;
        private int num;

        public Position(int numStack, int num) {
            this.numStack = numStack;
            this.num = num;
        }

        public int getNumStack() {
            return numStack;
        }

        public int getNum() {
            return num;
        }
    }
}
