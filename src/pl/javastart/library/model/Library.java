package pl.javastart.library.model;

public class Library {

    private final int maxBooks = 1000;
    private Book [] books = new Book[maxBooks];
    private int booksNumber = 0;

    public void addBook (Book book){
        if(booksNumber<maxBooks){
            books[booksNumber] = book;
            booksNumber++;
        }
        else {
            System.out.println("Osiągnięta majsymalna liczba książek");
        }
    }

}
