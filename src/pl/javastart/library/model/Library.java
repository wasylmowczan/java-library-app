package pl.javastart.library.model;

public class Library {

    private static final int MAX_BOOKS = 1000;
    private static final int MAX_MAGAZIN = 1000;
    private Book [] books = new Book[MAX_BOOKS];
    private Magazine [] magazines = new Magazine[MAX_BOOKS];
    private int booksNumber = 0;
    private int magazinesNumber = 0;

    public void addBook (Book book){
        if(booksNumber< MAX_BOOKS){
            books[booksNumber] = book;
            booksNumber++;
        }
        else {
            System.out.println("Osiągnięta majsymalna liczba książek");
        }
    }

    public void printBooks(){
        if (booksNumber == 0){
            System.out.println("Brak książek w bibliotece");
        }
        for (int i=0; i<booksNumber; i++){
            books[i].printInfo();
        }
    }

    public void addMagazine (Magazine magazine){
        if(magazinesNumber< MAX_MAGAZIN){
            magazines[magazinesNumber] = magazine;
            magazinesNumber++;
        }
        else {
            System.out.println("Osiągnięta majsymalna liczba magazynów");
        }
    }

    public void printMagazine(){
        if (magazinesNumber == 0){
            System.out.println("Brak magazynów w bibliotece");
        }
        for (int i=0; i<magazinesNumber; i++){
            magazines[i].printInfo();
        }
    }

}
