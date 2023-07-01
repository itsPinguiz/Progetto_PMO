/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.item.animal.*;
import model.item.plants.PlantFactory;
import model.item.tools.factory.IronFactory;
import model.item.tools.factory.ToolFactory;
import model.item.tools.factory.WoodFactory;

/*******************
 * ITEMCREATOR CLASS
 ******************/
public class ItemCreator implements Serializable {
    

    private final int MAX_INVENTORY_SIZE = 4;
    private final WoodFactory woodFactory = new WoodFactory();
    private final IronFactory ironFactory = new IronFactory();
    private final PlantFactory plantFactory = new PlantFactory();
    private final AnimalFactory animalFactory = new AnimalFactory();

    private final Item[] randomMethods;
    //fields
    private ArrayList<Item> inventory;
    private Random random;

    //constructor
    public ItemCreator() throws NoAnimalFoundException, NoProductFoundException{
        this.inventory   = new ArrayList<Item>(MAX_INVENTORY_SIZE);
        randomMethods = new Item[] {
            this.ironFactory.createSickle(),
            this.woodFactory.createHoe(),
            this.woodFactory.createFertilizer(),
            this.woodFactory.createScissors(),
            this.woodFactory.createSickle(),
            this.woodFactory.createWateringCan(),
            this.ironFactory.createHoe(),
            this.ironFactory.createFertilizer(),
            this.ironFactory.createWateringCan(),
            this.ironFactory.createScissors(),
            this.plantFactory.createPotato(),
            this.plantFactory.createCarrot(),
            this.plantFactory.createOnion(),
            this.plantFactory.createWeat(),
            this.animalFactory.createCow(),
            this.animalFactory.createChicken(),
            this.animalFactory.createPig(),
            this.animalFactory.createGoat()
        };
        this.random = new Random();
    }

    private void createStandardInventory(ToolFactory factory){
        this.inventory.add(factory.createHoe());
        this.inventory.add(factory.createSickle());
        this.inventory.add(factory.createWateringCan());
        this.inventory.add(factory.createFertilizer());
    }

    /**
     * Returns the wood set inventory.
     *
     * @return the wood set inventory
     */
    public ArrayList<Item> getWoodSet(){
        createStandardInventory(woodFactory);
        return this.inventory;
    }
    
    /**
     * Returns a random item from the available items.
     *
     * @return a random item
     */
    public Item getRandomItem(){
        return this.randomMethods[random.nextInt(randomMethods.length)];
    }

}
