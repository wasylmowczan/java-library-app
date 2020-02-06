package pl.javastart.library.app;

import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;

public class LibraryControl {

    private final int exit = 0;
    private final int addBook = 1;
    private final int printBook = 2;
    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        int option;

        do {
            printOptions();
            option = dataReader.getInt();

            switch (option){
                case addBook:
                    addBook();
                    break;
                case printBook:
                    printBook();
                    break;
                case exit:
                    System.out.println("Koniec programu!");
                    dataReader.close();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wybierz inną!");
            }
        } while (option != exit);
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
        System.out.println(exit + " - wyjście z programu");
        System.out.println(addBook + " - dodanie nowej książki");
        System.out.println(printBook + " - wyświetlenie wszystkich książek");
    }
}
