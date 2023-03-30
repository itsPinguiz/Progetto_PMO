/********************
 * IMPORT AND PACKAGE
 *******************/

package Item;

import java.io.Serializable;
import java.util.ArrayList;

import Item.Interface.Item;
import Item.Tools.Factory.IronFactory;
import Item.Tools.Factory.ToolFactory;
import Item.Tools.Factory.WoodFactory;
import Item.Tools.Interface.AbstractTool;

/*******************
 * ITEMCREATOR CLASS
 ******************/
public class ItemCreator implements Serializable{
    

    private final int MAX_INVENTORY_SIZE = 4;
    private final WoodFactory woodFactory = new WoodFactory();
    private final IronFactory ironFactory = new IronFactory();

    private ArrayList<Item> inventory;
    private AbstractTool[] randomMethods = {this.ironFactory.createHoe(),
                                            this.ironFactory.createWateringCan(),
                                            this.ironFactory.createScissors(),
                                            this.ironFactory.createSickle(),
                                            this.woodFactory.createHoe(),
                                            this.woodFactory.createScissors(),
                                            this.woodFactory.createSickle(),
                                            this.woodFactory.createWateringCan()};

    public ItemCreator(){
        this.inventory   = new ArrayList<Item>(MAX_INVENTORY_SIZE);
        //  this.woodFactory = new WoodFactory();
        // this.ironFactory = new IronFactory();
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
    public Item getRandomItem(int i){
        return this.randomMethods[i];
    }
}
