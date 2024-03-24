package task3;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class BookReader extends Human {

    private int registerNumber;
    private ArrayList<Book> books;

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

    public BookReader(){

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeInt(registerNumber);

        out.writeInt(books.size());
        for (Book book : books)
            book.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        registerNumber = in.readInt();

        int booksCount = in.readInt();
        books = new ArrayList<>();
        for (int i = 0; i < booksCount; i++) {
            Book takenBook = new Book();
            takenBook.readExternal(in);

            books.add(takenBook);
        }
    }
}
