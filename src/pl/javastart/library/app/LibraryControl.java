package pl.javastart.library.app;

import pl.javastart.library.exceptions.NoSuchOptionException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private Library library = new Library();

    public void controlLoop(){
        Option option;

        do {
            printOptions();
            option = getOption();
            switch (option){
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBook();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji, wybierz inną!");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while(!optionOk){
          try{
              option = Option.createFromInt(dataReader.getInt());
              optionOk = true;
          } catch (NoSuchOptionException e){
              printer.printLine(e.getMessage()+ ", podaj ponownie:");
          } catch (InputMismatchException e){
              printer.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie:");
          }
        }
        return option;
    }

    private void printOptions() {
        printer.printLine("Wybierz opcje:");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private void printMagazines() {
        Publication[] publications = library.getPublications();
        printer.printMagazine(publications);
    }

    private void addMagazine() {
        try{
        Magazine magazine = dataReader.readAndCreateMagazine();
        library.addMagazine(magazine);
        }catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć książkę, niepoprawne dane.");
        }catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit pojemności, nie można dodać nowej książki");
        }
    }

    private void printBook() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addBook(book);
        }catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć książkę, niepoprawne dane.");
        }catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit pojemności, nie można dodać nowej książki");
        }
    }

    private void exit() {
        printer.printLine("Koniec programu, papa!");
        // zamykamy strumień wejścia
        dataReader.close();
    }
}
