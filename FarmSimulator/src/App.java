import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Actors.Person.Farmer;
import Progress.Backup;
import Tools.ConcreteTool.Interface.Item;
import Tools.ConcreteTool.WoodTool.WoodHoe;

public class App {
    public static void main(String[] args) throws Exception {
        String s = "Ora sono questo";

        List<Serializable> actualObjects = new ArrayList<>();
        actualObjects.add(s);

        Backup backup = new Backup(actualObjects);

        //backup.saveCurrent();

        backup.printSavesList();

        actualObjects = List.copyOf(backup.loadSave(1));

        s = (String)actualObjects.get(0);

        System.out.println(s);
    }
}
