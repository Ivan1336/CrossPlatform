package task1;

import java.io.Serializable;
import java.util.ArrayList;

public class BookReader extends Human implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int registerNumber;
    private final ArrayList<Book> books;

    public BookReader(String name, String surname, int registerNumber){
        setName(name);
        setSurname(surname);
        this.registerNumber = registerNumber;
        this.books = new ArrayList<>();
    }

    public void takeBook(Book book){
        if(book != null){
            books.add(book);
        }
    }

    @Override
    public String toString(){
        StringBuilder bookReaderString = new StringBuilder();
        bookReaderString.append("\nРеєстраційний номер: ").append(registerNumber).append("\n");
        bookReaderString.append(super.toString()).append("\n");
        bookReaderString.append("\nКниги:\n");
        for(Book book: books){
            bookReaderString.append(book.toString()).append("\n");
        }
        return bookReaderString.toString();
    }
}
