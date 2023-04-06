package model.progress;

import java.io.IOException;
import java.util.List;

import model.Model;

interface Backup {
    String saveCurrent() throws IOException;
    List<String> getSavesList();
    Model loadSave(String saveName) throws IOException, ClassNotFoundException;
}
