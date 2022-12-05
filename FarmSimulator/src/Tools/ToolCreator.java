package Tools;

import java.util.ArrayList;

import Tools.ConcreteTool.Interface.Item;
import Tools.Factory.WoodFactory;
import Tools.Factory.IronFactory;
import Tools.Factory.ToolFactory;

public class ToolCreator {
    private final int MAX_INVENTORY_SIZE = 4;
    private final WoodFactory woodFactory;
    private final IronFactory ironFactory;

    private ArrayList<Item> inventory;

    public ToolCreator(){
        this.inventory   = new ArrayList<Item>(MAX_INVENTORY_SIZE);
        this.woodFactory = new WoodFactory();
        this.ironFactory = new IronFactory();
    }

    private void createStandardInventory(ToolFactory factory){
        this.inventory.add(factory.createHoe());
        this.inventory.add(factory.createSickle());
        this.inventory.add(factory.createScissors());
        this.inventory.add(factory.createWateringCan());
    }

    public ArrayList<Item> getWoodSet(){
        createStandardInventory(woodFactory);
        return this.inventory;
    }
    
    public ArrayList<Item> getIronSet(){
        createStandardInventory(ironFactory);
        return this.inventory;
    }
}
