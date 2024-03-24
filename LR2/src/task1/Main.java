package task1;

public class Main {

    public static void main(String[] args) {
       Library library = new Library("Центральна бібліотека Богодухова");

        BookStore bookStore1 = new BookStore("Художня література");
        BookStore bookStore2 = new BookStore("Історія");
        BookStore bookStore3 = new BookStore("Наука");

        Book b1 = new Book("Танці з кістками", new Author("Андрій", "Сем'янків"), 2022, 1);
        Book b2 = new Book("СпАДок", new Author("Люко", "Дашвар"), 2023, 1);
        Book b3 = new Book("Я бачу, вас цікавить пітьма", new Author("Ілларіон", "Павлюк"), 2020, 1);
        Book b4 = new Book("Час", new Author("Юрій", "Щербак"), 2021, 3);
        Book b5 = new Book("Фактор Черчилля", new Author("Боріс", "Джонсон"), 2015, 1);
        Book b6 = new Book("Брама Європи. Історія України від скіфських воєн до незалежності", new Author("Сергій", "Плохій"), 2016, 1);
        Book b7 = new Book("Мальована історія Незалежності України", new Author("Віталій", "Капранов"), new Author("Дмитро", "Капранов"), 2021, 2);
        Book b8 = new Book("Теорія неймовірності", new Author("Макс ", "Кідрук"), 2023, 1);
        Book b9 = new Book("Екскурсія математикою. Як через готелі, риб, камінці і пасажирів зрозуміти цю науку", new Author("Стівен", "Строгац"), 2019, 1);

        bookStore1.addBook(b1);
        bookStore1.addBook(b2);
        bookStore1.addBook(b3);
        bookStore1.addBook(b4);
        bookStore2.addBook(b5);
        bookStore2.addBook(b6);
        bookStore2.addBook(b7);
        bookStore3.addBook(b8);
        bookStore3.addBook(b9);


        library.addBookStore(bookStore1);
        library.addBookStore(bookStore2);
        library.addBookStore(bookStore3);

        BookReader bookReader1 = new BookReader("Іван", "Чаплига", 1325);
        BookReader bookReader2 = new BookReader("Максим", "Полевич", 9875);


        library.registerReader(bookReader1);
        library.registerReader(bookReader2);

        bookReader2.takeBook(library.giveBook(b1));
        bookReader2.takeBook(library.giveBook(b2));
        bookReader2.takeBook(library.giveBook(b4));
        bookReader2.takeBook(library.giveBook(b6));

        bookReader1.takeBook(library.giveBook(b5));
        bookReader1.takeBook(library.giveBook(b9));

        System.out.println("Перед серіалізацією");
        System.out.println(library);

        System.out.println("Після десеріалізації");
        LibraryDriver.serializeObject(library);
        System.out.println(LibraryDriver.deserializeObject());

    }
}