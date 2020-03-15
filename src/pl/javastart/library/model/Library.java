package pl.javastart.library.model;

public class Library {

    private static final int MAX_PUBLICATIONS = 2000;
    private int publicationsNumber = 0;
    private Publication [] publications = new Publication[MAX_PUBLICATIONS];

    public Publication[] getPublications() {
        Publication [] result = new Publication[publicationsNumber];
        for (int i = 0; i <result.length ; i++) {
            result[i]=publications[1];
        }
        return result;
    }

    public void addBook (Book book){
        addPublication(book);
    }

    public void addMagazine (Magazine magazine){
        addPublication(magazine);
    }

    public void addPublication (Publication publication){
        if (publicationsNumber >= MAX_PUBLICATIONS){
            throw new ArrayIndexOutOfBoundsException("Max publications exceeded " + MAX_PUBLICATIONS);
        }
        publications[publicationsNumber] = publication;
        publicationsNumber++;

    }

}
