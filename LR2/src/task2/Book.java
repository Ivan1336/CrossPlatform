package task2;

import java.util.ArrayList;

public class Book {
    private ArrayList <Author> authors;
    private String title;
    private int yearOfWriting, series;

    public Book(String title, ArrayList<Author> authors, int yearOfWriting, int series){
        this.title = title;
        this.authors = authors;
        this.yearOfWriting = yearOfWriting;
        this.series = series;
    }

    public Book(String title, Author author, int yearOfWriting, int series){
        ArrayList <Author> authors = new ArrayList<>();
        authors.add(author);

        this.title = title;
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


    public String getTitle() {
        return title;
    }


    public int getYearOfWriting() {
        return yearOfWriting;
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
}
