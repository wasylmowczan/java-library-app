package pl.javastart.library.app;

public class LibraryApp {

    private static final String APP_NAME = "version 1.5";
    public static void main(String[] args) {
        System.out.println(APP_NAME);
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.controlLoop();
    }
}
