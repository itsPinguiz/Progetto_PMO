package Progress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Main.Game;

public class GameBackup implements Backup{
    // attributes
    private Game classesBackup;
    private final File savePath;
    private List<String> classesNamesBackup;

    // constructor
    public GameBackup(Game currentClasses){
        this.classesBackup = currentClasses;
        this.classesNamesBackup = new ArrayList<>();
        this.savePath = new File("saves");
        this.savePath.mkdirs();
    }

    public void saveCurrent() throws IOException{
        /*
         * Saves current game session in a file
         * with the name of the current date
         */
        // get current time to save
        LocalDateTime saveTime = LocalDateTime.now();
        String formattedDate = saveTime.format(DateTimeFormatter.ofPattern("dd_MM_yyyy-HH_mm_ss"));   

        // create file and save
        File Backup = new File(this.savePath,formattedDate + ".txt");
        Backup.createNewFile();
        FileOutputStream fos = new FileOutputStream(Backup,false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        this.writeToFile(oos);
    }

    private void writeToFile (ObjectOutputStream out) throws IOException {
        /*
         * Writes list of classes 
         * to the file
         */
        out.writeObject(this.classesBackup);
        out.close();
   }

    public List<String> getSavesList(){
        /*
         * Prints all the saved files
         * of the game session
         */
        this.updateSavesList();

        return this.classesNamesBackup;
    }

    public Game loadSave(String saveName) throws Exception{
        /*
         * Returns a specific saved game session
         */
        this.updateSavesList();
        return (Game)(this.readFromFile(new File(this.savePath,saveName))).get(0);
    }

    private void updateSavesList(){
        /*
         * Updates saves list before showing it to the user
         */
        this.classesNamesBackup = List.copyOf(Arrays.asList(this.savePath.list()));
    }

    @SuppressWarnings("unchecked")
    private List<Serializable> readFromFile(File f) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Serializable> classesBackup = (List<Serializable>) ois.readObject();
        ois.close();
        return classesBackup;
    }   
}

