package com.wasylmowczan.library.io.file;

import com.wasylmowczan.library.model.Library;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}
