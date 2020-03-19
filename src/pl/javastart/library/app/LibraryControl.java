package pl.javastart.library.app;

import pl.javastart.library.exceptions.DataExportException;
import pl.javastart.library.exceptions.DataImportException;
import pl.javastart.library.exceptions.NoSuchOptionException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.io.file.FileManagerBuilder;
import pl.javastart.library.io.file.FileManger;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManger fileManager;
    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Zaimportowano dane z pliku");
        }catch (DataImportException e){
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę.");
            library = new Library();
        }
    }

    void controlLoop(){
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
        try {
            fileManager.exportData(library);
            printer.printLine("Export danych do pliku zakończony powodzeniem");
        }catch (DataExportException e){
            printer.printLine(e.getMessage());
        }
        printer.printLine("Koniec programu, papa!");
        dataReader.close();
    }

    private enum Option {
        EXIT(0, "Wyjście z programu"),
        ADD_BOOK(1, "Dodanie książki"),
        ADD_MAGAZINE(2,"Dodanie magazynu/gazety"),
        PRINT_BOOKS(3, "Wyświetlenie dostępnych książek"),
        PRINT_MAGAZINES(4, "WYświetlenie dostępnych magazynów/gazet");

        private int value;
        private String description;

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        Option(int value, String desc) {
            this.value = value;
            this.description = desc;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            }catch (ArrayIndexOutOfBoundsException e){
                throw new NoSuchOptionException("Brak opcji o id " + option);
            }
        }
    }

}
