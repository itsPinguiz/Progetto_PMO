package model.progress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import model.exceptions.CustomExceptions.SaveIsCorruptedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Model;

/**
 * Class that manages the backup of the game
 */
public class GameBackupManager implements Backup{
    /**
     * Attributes
     */
    private Model classesBackup;
    private final File savePath;
    private List<String> classesNamesBackup;

    /**
     * Constructor
     * @param currentClasses Model
     */
    public GameBackupManager(Model currentClasses){
        this.classesBackup = currentClasses;
        this.classesNamesBackup = new ArrayList<>();
        this.savePath = new File("saves");
        this.savePath.mkdirs();
    }

    /** 
     * Saves current game session in a file
     * with the name of the current date
     * @return String
     * @throws IOException
     */
    public String saveCurrent() throws IOException{
        // get current time to save
        LocalDateTime saveTime = LocalDateTime.now();
        String formattedDate = saveTime.format(DateTimeFormatter.ofPattern("dd_MM_yyyy-HH_mm_ss"));   

        // create file and save
        File Backup = new File(this.savePath,formattedDate + ".txt");
        Backup.createNewFile();
        FileOutputStream fos = new FileOutputStream(Backup,false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        this.writeToFile(oos);
        return formattedDate;
    }

    /**
     * Writes list of classes 
     * to the file
     * @param out ObjectOutputStream
     */
    private void writeToFile (ObjectOutputStream out) throws IOException {
        out.writeObject(this.classesBackup);
        out.close();
    }

    /**
     * Returns all the saved files
     * of the game session
     * @return List<String>
     */
    public List<String> getSavesList(){
        this.updateSavesList();
        return this.classesNamesBackup;
    }

    /**
     * Deletes a specific saved game session
     * @param saveName String
     * 
     * @throws IOException
     */
    public void deleteSave(String saveName) throws IOException{
        File fileToDelete = new File(this.savePath,saveName);
        fileToDelete.delete();
        this.updateSavesList();
    }

    /**
     * Returns the saved class with the name saveName
     * @param saveName saveName
     * @throws IOException
     * @throws ClassNotFoundException 
     * @throws SaveIsCorruptedException The file is corrupted
     */
    public Model loadSave(String saveName) throws IOException, 
                                                  ClassNotFoundException,
                                                  SaveIsCorruptedException {
        this.updateSavesList();
        try {
            Model loadedClasses = readFromFile(new File(this.savePath, saveName)); // load the saved classes from the file
            return loadedClasses;
        } catch (StreamCorruptedException e) {
            throw new SaveIsCorruptedException(saveName);
        }
    }
    
    /**
     * Updates saves list before showing it to the user
     * 
     */
    private void updateSavesList(){
        
        this.classesNamesBackup = List.copyOf(Arrays.asList(this.savePath.list()));
    }

    /**
     * Reads the saved classes from the file
     * @param f File
     * @return Model
     * @throws IOException, ClassNotFoundException
     */
    private Model readFromFile(File f) throws IOException, 
                                              ClassNotFoundException {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Model classesBackup = (Model) ois.readObject();
        ois.close();
        return classesBackup;
    }   
}
