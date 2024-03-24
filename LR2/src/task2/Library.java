package task2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Library implements Serializable{
    private static final long serialVersionUID = 1L;

    private  ArrayList <BookStore> bookStores = new ArrayList<>();
    private  transient ArrayList <BookReader> registeredReaders = new ArrayList<>();
    private  String name;


    public Library(String name){
        this.name = name;
    }

    public void addBookStore(BookStore bookStore){
        bookStores.add(bookStore);
    }

    public ArrayList<BookReader> getRegisteredReaders() {
        return registeredReaders;
    }


    public Book giveBook(Book book){
        Book neededBook;
        for (int i = 0; i < bookStores.size(); i++) {
            if((neededBook = bookStores.get(i).getBook(book)) != null){
                return neededBook;
            }
        }

        return null;
    }


    public void registerReader(BookReader bookReader){
        registeredReaders.add(bookReader);
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        StringBuilder libraryString = new StringBuilder();
        libraryString.append("Назва бібліотеки: ").append(name);

        libraryString.append("\nСховище книг: ");
        for (BookStore bookStore: bookStores){
            libraryString.append(bookStore.toString());
        }

        libraryString.append("\nЧитаті:");
        for (BookReader bookReader : registeredReaders){
            libraryString.append(bookReader.toString()).append("\n\n");
        }

        return libraryString.toString();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        out.writeInt(registeredReaders.size());
        for(BookReader bookReader : registeredReaders){
            out.writeObject(bookReader.getName());
            out.writeObject(bookReader.getSurname());
            out.writeInt(bookReader.getRegisterNumber());
            out.writeInt(bookReader.getBooks().size());

            for (Book book : bookReader.getBooks()) {
                out.writeObject(book.getTitle());

                out.writeInt(book.getAuthors().size());
                for (Author author : book.getAuthors()) {
                    out.writeObject(author.getName());
                    out.writeObject(author.getSurname());
                }

                out.writeInt(book.getYearOfWriting());
                out.writeInt(book.getSeries());
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();

        int readersCount = in.readInt();
        registeredReaders = new ArrayList<>();
        for (int i = 0; i < readersCount; i++) {
            String name = (String)in.readObject();
            String surname = (String)in.readObject();
            int bookReaderNumber = in.readInt();
            BookReader bookReader = new BookReader(name, surname, bookReaderNumber);

            int takenBooksCount = in.readInt();
            for (int j = 0; j < takenBooksCount; j++) {
                String bookName = (String)in.readObject();
                int authorsCount = in.readInt();

                ArrayList <Author> authors = new ArrayList<>();
                for (int k = 0; k < authorsCount; k++) {
                    String authorName = (String)in.readObject();
                    String authorSurname = (String)in.readObject();
                   Author author = new Author(authorName, authorSurname);

                    authors.add(author);
                }

                int yearOfWriting = in.readInt();
                int series = in.readInt();

                Book book = new Book(bookName, authors, yearOfWriting, series);
                bookReader.takeBook(book);
            }

            registeredReaders.add(bookReader);
        }
    }
}
