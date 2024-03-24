package task2;

import java.util.ArrayList;

public class BookReader extends Human{

    private int registerNumber;
    private ArrayList<Book> books;
    private String name;
    private String surName;

    public BookReader(String name, String surname, int registerNumber){
        setName(name);
        setSurname(surname);
        this.name = name;
        this.surName = surname;
        this.registerNumber = registerNumber;
        this.books = new ArrayList<>();
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
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
