package pl.javastart.library.io;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

public class ConsolePrinter {
    public void printBooks(Publication[] publications){
        int countBooks = 0;
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());
                countBooks++;
            }
        }
        if (countBooks == 0) {
            printLine("Brak książek w bibliotece");
        }
    }

    public void printMagazine(Publication[] publications){
        int countMagazines = 0;
        for (Publication publication : publications) {
            if (publication instanceof Magazine){
                printLine(publication.toString());
                countMagazines++;
            }
        }
        if (countMagazines == 0){
            printLine("Brak magazynów w bibliotece");
        }
    }

    public void printLine (String text){
        System.out.println(text);
    }
}
