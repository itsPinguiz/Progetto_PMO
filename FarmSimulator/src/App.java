import Actors.Person.Farmer;
import Tools.ConcreteTool.Interface.Item;
import Tools.ConcreteTool.WoodTool.WoodHoe;

public class App {
    public static void main(String[] args) throws Exception {
        Farmer  f = new Farmer();
        Item newTool = new WoodHoe();

        f.getInventory();
        f.removeItem(0);
        f.getInventory();
        f.addItem(newTool);
        f.getInventory();
        f.addItem(newTool);
        f.addItem(newTool);
        f.getInventory();
    }
}
