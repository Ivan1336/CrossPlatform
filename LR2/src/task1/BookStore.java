package task1;

import java.io.Serializable;
import java.util.ArrayList;


public class BookStore implements Serializable{
    private static final long serialVersionUID = 1L;

    private final ArrayList<Book> books;
    private String theme;

    public BookStore(String theme){
        this.theme = theme;
        this.books = new ArrayList<>();
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

}
