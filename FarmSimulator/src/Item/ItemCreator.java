/********************
 * IMPORT AND PACKAGE
 *******************/

package Item;

import java.io.Serializable;
import java.util.ArrayList;

import Item.Interface.Item;
import Item.Plants.PlantFactory;
import Item.Plants.species.Plant;
import Item.Tools.Factory.IronFactory;
import Item.Tools.Factory.ToolFactory;
import Item.Tools.Factory.WoodFactory;
import Item.Tools.Interface.AbstractTool;
import Item.Animal.*;

/*******************
 * ITEMCREATOR CLASS
 ******************/
public class ItemCreator implements Serializable{
    

    private final int MAX_INVENTORY_SIZE = 4;
    private final WoodFactory woodFactory = new WoodFactory();
    private final IronFactory ironFactory = new IronFactory();
    private final PlantFactory plantFactory = new PlantFactory();
    private final AnimalFactory animalFactory = new AnimalFactory();

    private final Item[] randomMethods = {this.ironFactory.createHoe(),
                                          this.ironFactory.createWateringCan(),
                                          this.ironFactory.createScissors(),
                                          this.ironFactory.createSickle(),
                                          this.woodFactory.createHoe(),
                                          this.woodFactory.createScissors(),
                                          this.woodFactory.createSickle(),
                                          this.woodFactory.createWateringCan(),
                                          this.plantFactory.createPotato(),
                                          this.plantFactory.createCarrot(),
                                          this.plantFactory.createOnion(),
                                          this.plantFactory.createWeat(),
                                          this.animalFactory.createCow(),
                                          this.animalFactory.createChicken(),
                                          this.animalFactory.createPig(),
                                          this.animalFactory.createGoat()
                                                };
    //fields
    private ArrayList<Item> inventory;

    //constructor
    public ItemCreator(){
        this.inventory   = new ArrayList<Item>(MAX_INVENTORY_SIZE);
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
    
    public Item getRandomItem(){
        return this.randomMethods[(int)((Math.random() * this.randomMethods.length))];
    }

}
