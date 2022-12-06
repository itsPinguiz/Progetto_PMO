package Progress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Land.LandInteface;

public class Saver {
    private LocalDateTime date;
    private int saveNumber;
    private Farmer farmer;
    private Landlord landlord;
    private List<LandInteface> lands;
    private Object barn;

    private List<Object> components;

    public Saver(){
        components = new ArrayList<>();
    }

    public void saveCurrent(List<Object> c){
        this.components.addAll(c);

        try (FileOutputStream f = new FileOutputStream("kirbyInfo.txt");
             ObjectOutputStream s = new ObjectOutputStream(f)) {
            this.writeToFile(s);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private void writeToFile (ObjectOutputStream out) throws IOException {
        out.writeObject(this);
        out.close();
   }
}
