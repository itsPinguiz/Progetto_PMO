package model.progress;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import model.Model;
import model.exceptions.CustomExceptions.SaveIsCorruptedException;

interface Backup extends Serializable{
    String saveCurrent() throws IOException;
    List<String> getSavesList();
    Model loadSave(String saveName) throws IOException, 
                                           ClassNotFoundException,
                                           SaveIsCorruptedException;
}
