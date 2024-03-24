package task3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class BookStore implements Externalizable {
    private static final long serialVersionUID = 666L;

    private ArrayList<Book> books;
    private String theme;

    public BookStore(String theme){
        this.theme = theme;
        this.books = new ArrayList<>();
    }
    public BookStore(){

    }

    public void addBook(Book book){
        books.add(book);
    }


    public Book getBook(Book book){
        Book neededBook = null;
        if(!(books.size() == 0)){
            for (int i = 0; i < books.size(); i++) {
                if(book == books.get(i)){
                    neededBook = books.get(i);
                    removeBook(neededBook);
                }
            }
        }
      return neededBook;
    }

    private void removeBook(Book book){
        books.remove(book);
    }


    public String toString(){
        StringBuilder bookStoreString = new StringBuilder();
        bookStoreString.append("\nНазва сховища: ").append(theme);
        for (Book book : books){
            bookStoreString.append(book.toString());
        }

        return bookStoreString.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(theme);

        out.writeInt(books.size());
        for(Book book : books)
            book.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        theme = (String)in.readObject();

        int booksCount = in.readInt();
        books = new ArrayList<>();
        for (int i = 0; i < booksCount; i++) {
            Book book = new Book();
            book.readExternal(in);
            books.add(book);
        }
    }
}
