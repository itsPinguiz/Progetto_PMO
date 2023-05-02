package model.progress;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import model.Model;
import model.exceptions.CustomExceptions.SaveIsCorruptedException;

/**
 * Interface that represents a backup manager
 */
interface Backup extends Serializable{
    /**
     * Saves current game session in a file
     * with the name of the current date
     * @return String Name of the save
     * @throws IOException
     */
    String saveCurrent() throws IOException;

    /**
     * Writes the current game session in a file
     * @return List<String> Saves name list
     */
    List<String> getSavesList();

    /**
     * Returns the saved class with the name saveName
     * @param saveName saveName
     * @throws IOException
     * @throws ClassNotFoundException 
     * @throws SaveIsCorruptedException The file is corrupted
     */
    Model loadSave(String saveName) throws IOException, 
                                           ClassNotFoundException,
                                           SaveIsCorruptedException;
}
