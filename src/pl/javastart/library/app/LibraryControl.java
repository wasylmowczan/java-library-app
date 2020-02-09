package pl.javastart.library.app;

import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;

public class LibraryControl {

    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int PRINT_BOOK = 2;
    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        int option;

        do {
            printOptions();
            option = dataReader.getInt();

            switch (option){
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOK:
                    printBook();
                    break;
                case EXIT:
                    System.out.println("Koniec programu!");
                    dataReader.close();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wybierz inną!");
            }
        } while (option != EXIT);
    }

    private void printBook() {
        library.printBooks();
    }

    private void addBook() {
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);
    }

    private void printOptions() {
        System.out.println("Wybierz opcje:");
        System.out.println(EXIT + " - wyjście z programu");
        System.out.println(ADD_BOOK + " - dodanie nowej książki");
        System.out.println(PRINT_BOOK + " - wyświetlenie wszystkich książek");
    }
}
