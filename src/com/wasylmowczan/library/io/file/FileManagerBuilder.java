package com.wasylmowczan.library.io.file;

import com.wasylmowczan.library.exceptions.NoSuchFileTypeException;
import com.wasylmowczan.library.io.ConsolePrinter;
import com.wasylmowczan.library.io.DataReader;

public class FileManagerBuilder  {
    private ConsolePrinter printer;
    private DataReader reader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public FileManager build(){
        printer.printLine("Wybierz format danych:");
        FileType fileType = getFileType();
        switch (fileType) {
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Nieobsługiwany typ formatu");
        }
    }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printType();
            //serial, SERIAL
            String type = reader.getString().toUpperCase();
            try{
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e){
                printer.printLine("Nieobsługiwany typ danych, wybierz ponownie.");
            }
        } while (!typeOk);
        return result;
    }

    private void printType() {
        for (FileType value : FileType.values()) {
             printer.printLine(value.name());
        }

    }
}
