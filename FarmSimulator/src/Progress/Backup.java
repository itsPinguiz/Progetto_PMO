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

public class Backup {
    private List<Serializable> classesBackup;
    private final File savePath;
    private List<String> classesNamesBackup;

    public Backup(List<Serializable> currentClasses){
        this.classesBackup = List.copyOf(currentClasses);
        this.classesNamesBackup = new ArrayList<>();
        this.savePath = new File("saves");
        this.savePath.mkdirs();
    }

    public void saveCurrent() throws IOException{
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
        // write all simulator object into the file
        out.writeObject(this.classesBackup);
        out.close();
   }

    public void printSavesList(){
        this.updateSavesList();

        System.out.println("====== CURRENT BACKUPS ======");
        for(int i = 1; i <= this.classesNamesBackup.size(); i++){
            System.out.printf("[%d] %s\n",i,this.classesNamesBackup.get(i-1));
        }
    }

    public List<Serializable> loadSave(int index) throws Exception{
        this.updateSavesList();
        return this.readFromFile(new File(this.savePath,this.classesNamesBackup.get(index-1)));
    }

    private void updateSavesList(){
        this.classesNamesBackup = List.copyOf(Arrays.asList(this.savePath.list()));
    }

    public List<Serializable> readFromFile(File f) throws Exception{
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.classesBackup = (List)ois.readObject();
        ois.close();
        return this.classesBackup;
    }
}

