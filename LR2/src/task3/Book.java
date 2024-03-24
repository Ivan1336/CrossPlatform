package task3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class Book implements Externalizable {
    private ArrayList <Author> authors;
    private String title;
    private int yearOfWriting, series;

    public Book(String name, ArrayList<Author> authors, int yearOfWriting, int series){
        this.title = name;
        this.authors = authors;
        this.yearOfWriting = yearOfWriting;
        this.series = series;
    }
     public Book(){
    this("Untitled", new ArrayList<Author>(), 0, 0);
}

    public Book(String name, Author author, int yearOfWriting, int series){
        ArrayList <Author> authors = new ArrayList();
        authors.add(author);

        this.title = name;
        this.authors = authors;
        this.yearOfWriting = yearOfWriting;
        this.series = series;
    }
    public Book(String title, Author author1, Author author2, int yearOfWriting, int series){
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        this.title = title;
        this.authors = authors;
        this.yearOfWriting = yearOfWriting;
        this.series = series;
    }
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }

    public int getSeries() {
        return series;
    }

    public String toString(){
        StringBuilder bookString = new StringBuilder("\n\nНазва книга: " + title + "\n");
        bookString.append("Автори:");
        for (Author author : authors){
            bookString.append(" ").append(author.toString()).append("\n");
        }
        bookString.append("Рік видання: ").append(yearOfWriting).append("\n");
        bookString.append("Номер видання: ").append(series).append("\n");

        return bookString.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeInt(yearOfWriting);
        out.writeInt(series);

        out.writeInt(authors.size());
        for(Author author : authors)
            author.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String)in.readObject();
        yearOfWriting = in.readInt();
        series = in.readInt();
        authors = new ArrayList<>();

        int authorsCount = in.readInt();
        for (int i = 0; i < authorsCount; i++) {
            Author author = new Author();
            author.readExternal(in);
            authors.add(author);
        }
    }
}
