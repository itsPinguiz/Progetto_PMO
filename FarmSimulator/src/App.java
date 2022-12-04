import Actors.Person.Farmer;
import Tools.ConcreteTool.Interface.Tool;
import Tools.ConcreteTool.WoodTool.WoodHoe;

public class App {
    public static void main(String[] args) throws Exception {
        Farmer  f = new Farmer();
        Tool newTool = new WoodHoe();

        f.getInventory();
        f.removeItem(2);
        f.getInventory();
        f.removeItem(2);
        f.addItem(newTool, 2);
        f.getInventory();
        f.addItem(newTool, 2);

    }
}
