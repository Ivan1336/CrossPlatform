package task2;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BookStore implements Serializable{
    private static final long serialVersionUID = 1L;

    private transient ArrayList<Book> books;
    private final String theme;

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
                    if (book == books.get(i)) {
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        out.writeInt(books.size());
        for (Book book : books) {
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

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        books = new ArrayList<>();

        int bookCount = in.readInt();
        for (int i = 0; i < bookCount; i++) {
            String name = (String)in.readObject();
            int authorsCount = in.readInt();

            ArrayList <Author> authors = new ArrayList<>();
            for (int j = 0; j < authorsCount; j++) {
                String authorName = (String)in.readObject();
                String authorSurname = (String)in.readObject();

                Author author = new Author(authorName, authorSurname);
                authors.add(author);
            }

            int yearOfWriting = in.readInt();
            int series = in.readInt();

            Book book = new Book(name, authors, yearOfWriting, series);
            books.add(book);
        }
    }

}
