package model.actors.actions.playerActions;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Constants;
import model.actors.person.Farmer;
import model.exceptions.CustomExceptions.*;
import model.item.Item;
import model.item.ItemType;
import model.item.animal.AnimalAbstract;
import model.item.plants.Plant;
import model.item.plants.Plant.PlantLife;
import model.item.tools.AbstractTool;
import model.place.barn.Barn;
import model.place.land.chunks.AnimalChunk;
import model.place.land.chunks.PlantChunk;

public class FarmerActions extends PlayerActions<Farmer> {

    // constructor
    public FarmerActions(Farmer farmer) {
        super(farmer);
    }
    
    // METHODS FOR THE FARMER
    public void move_item() throws CloneNotSupportedException,
                                   InventoryIsFullException,
                                   NoItemFoundException,
                                   NotEnoughItemsException{
        /*
         * Method to move the item
         * to the barn from the farmer's inventory
         */
        Item itemToMove = (Item)argument.getArg2();
        Farmer farmer = (Farmer)this.person;
        Barn barn = (Barn)farmer.getPlace();

        if (barn.getBarnInventory().getInventory().contains(itemToMove)){
            // if the item is in the barn, remove it and add it to the farmer's inventory
            try {
                farmer.getInventory().addItem(barn.getBarnInventory().getItem(1,itemToMove));
            } catch (InventoryIsFullException e) {
                // if inventory is full, add the item back to the barn
                Item oneItemToMove = (Item)itemToMove.clone();
                oneItemToMove.setNumber(1);
                barn.getBarnInventory().addItem(oneItemToMove);
                throw new InventoryIsFullException();
            }
        } else if (farmer.getInventory().getInventory().contains(itemToMove)){
            // if the item is in the inventory, remove it and add it to the barn
            try {
                barn.getBarnInventory().addItem(farmer.getInventory().getItem(1,itemToMove));
            } catch (InventoryIsFullException e) {
                // if the item was not found add the item back to the inventory
                Item oneItemToMove = (Item)itemToMove.clone();
                oneItemToMove.setNumber(1);
                farmer.getInventory().addItem(oneItemToMove);
                throw new InventoryIsFullException();
            }
        }
    }
    
    public void plant() throws NoSeedFoundException,
                               CloneNotSupportedException,
                               LandIsNotPlowedException,
                               NoItemFoundException {
        Farmer f = (Farmer) this.person;
        PlantChunk c = (PlantChunk) argument.getArg1();
        Item seed = (Item) argument.getArg2();

        // check if the farmer has a seed and the chunk is plowed
        if (seed instanceof Plant) {
            if (c.isPlowed() && f.getInventory().searchItem(seed, false) != -1 && c.getPlant() == null) {
                // add plant to the land
                c.setPlant((Plant) (f.getInventory().getItem(1, seed)));
                c.getPlant().planted(c);

                // add new possible actions
                Set<Action> chunkActions = Stream.of(Action.WATER, Action.FERTILIZE)
                                                 .collect(Collectors.toCollection(HashSet::new));
                c.getActions().resetActions();
                c.getActions().updateActions(chunkActions, true);

                Set<Action> landActions = Stream.of(Action.FERTILIZE_ALL, Action.WATER_ALL)
                                                .collect(Collectors.toCollection(HashSet::new));
                c.getLand().getActions().updateActions(landActions, true);

                // if all lands are planted remove the plant all action
                if (c.getLand().getNumElements() == 10) {
                    c.getLand().getActions().updateActions(Collections.singleton(Action.PLANT_ALL), false);
                }
            }
        } else {
            throw new NoSeedFoundException();
        }
    }


    public void water() throws NoToolFoundException, 
                               NoItemFoundException,
                               NotEnoughItemsException{
        /*
         * Method to water a plant
         */
        PlantChunk c = (PlantChunk)argument.getArg1();
        Item tool = (Item)argument.getArg2();

        //check if the farmer has the watering can
        if (tool.getType() == ItemType.Tools.WATERINGCAN && super.damageTool(tool)){
            // increase water level
            c.increaseWaterLevel(Constants.WATERING_INDEX*(((AbstractTool)(tool)).getMaterial().getModifier()));
        } else throw new NoToolFoundException(tool.getType(),ItemType.Tools.WATERINGCAN);
    }

    public void plow() throws NoToolFoundException, 
                              LandIsAlreadyPlowedException, 
                              NoItemFoundException,
                              NotEnoughItemsException{
        /*
         * Method to plow dirt
         */
        PlantChunk c = (PlantChunk)argument.getArg1();
        Item tool = (Item)argument.getArg2();
        
        //check if the farmer has the hoe
        if (tool.getType() == ItemType.Tools.HOE && this.damageTool(tool)){
            if (!c.isPlowed()){
                // change land status
                c.setDirtStatus(true);
                // add new possible actions
                c.getActions().resetActions();
                c.getActions().updateActions(new HashSet<>(){{
                    add(Action.PLANT);
                    }}, true);
                c.getLand().getActions().updateActions(new HashSet<>(){{
                    add(Action.PLANT_ALL);
                    }}, true);

                // if all the chunks are plowed, remove the plow all action
                if (c.getLand().getElements().stream().filter(chunk -> chunk.isPlowed() == false).count() == 0){
                    Set<Action> landActions = Stream.of(Action.PLOW_ALL)
                                                    .collect(Collectors.toCollection(HashSet::new));
                    c.getLand().getActions().updateActions(landActions, false);
                }
            } else throw new LandIsAlreadyPlowedException();
        }else throw new NoToolFoundException(tool.getType(),ItemType.Tools.HOE);
    }

    public void fertilize() throws NoToolFoundException, 
                                   NoItemFoundException,
                                   NotEnoughItemsException{
        /*
         * Method to fertilize a plant
         */
        PlantChunk c = (PlantChunk)argument.getArg1();
        Item tool = (Item)argument.getArg2();

        //check if the farmer has the fertilizer
        if (tool.getType() == ItemType.Tools.FERTILIZER && this.damageTool(tool)){ 
            // increase water level
            c.increaseFertilizationLevel(Constants.FERTILIZATION_INDEX*(((AbstractTool)(tool)).getMaterial().getModifier()));
        }else throw new NoToolFoundException(tool.getType(),ItemType.Tools.FERTILIZER);
    }

    public void harvest() throws InventoryIsFullException, 
                                 NoItemFoundException,
                                 NotEnoughItemsException,
                                 CloneNotSupportedException{
        /*
         * Method to harvest a plant
         */
        PlantChunk c = (PlantChunk)argument.getArg1();
        Item tool = (Item)argument.getArg2();
        Farmer f = (Farmer)this.person;
        Item product;

        // check if the plant is harvestable 
        if (c.getPlant() == null || c.getPlant().getLifeStage() != PlantLife.HARVESTABLE) return;

        // get the product
        product = c.getPlant().getProduct();
        
        // if sickle is equipped double the harvested resources
        if (tool != null && tool.getType() == ItemType.Tools.SICKLE && this.damageTool(tool)){
            product.setNumber(product.getNumber()*(((AbstractTool)(tool)).getMaterial().getModifier()));
        } else{
            product.setNumber(product.getNumber()*Constants.HARVEST_MULTIPLIER);
        }

        // add resources to the inventory
        
        f.getInventory().addItem(product);
  
        // remove plant
        c.setPlant(null);
        c.resetActions();

        // if all the chunks do not have plants, remove the doAllall action
        if (c.getLand().getNumElements() == 0){
            Set<Action> landActions = Stream.of(Action.FERTILIZE_ALL, Action.WATER_ALL,Action.HARVEST_ALL)
                                            .collect(Collectors.toCollection(HashSet::new));
            c.getLand().getActions().updateActions(landActions, false);
        }

        if(c.getLand().getElements().stream().filter(chunk -> chunk.getPlant() != null &&
           chunk.getPlant().getLifeStage() == PlantLife.HARVESTABLE).count() == 0){
            Set<Action> landActions = Stream.of(Action.HARVEST_ALL)
                                            .collect(Collectors.toCollection(HashSet::new));
            c.getLand().getActions().updateActions(landActions, false);
        }
    }

    public void plant_all() throws NoSuchMethodException,
                                   IllegalAccessException,
                                   InvocationTargetException,
                                   ActionNotAvailableException{
        /*
         * Method to plant all plants
         */
        this.doAll(Action.PLANT);
    }

    public void water_all() throws NoSuchMethodException,
                                   IllegalAccessException,
                                   InvocationTargetException,
                                   ActionNotAvailableException{
        /*
         * Method to water all plants
         */
        this.doAll(Action.WATER);
    }

    public void fertilize_all()throws NoSuchMethodException,
                                      IllegalAccessException,
                                      InvocationTargetException,
                                      ActionNotAvailableException{
        /*
         * Method to fertilize all plants
         */
        this.doAll(Action.FERTILIZE);
    }

    public void harvest_all() throws NoSuchMethodException,
                                     IllegalAccessException,
                                     InvocationTargetException,
                                     ActionNotAvailableException{
        /*
         * Method to harvest all plants
         */
        this.doAll(Action.HARVEST);
    }

    public void plow_all() throws NoSuchMethodException,
                                  IllegalAccessException,
                                  InvocationTargetException,
                                  ActionNotAvailableException{
        /*
         * Method to plow dirt
         */
        this.doAll(Action.PLOW);
    }

    public void add_animal() throws NoItemFoundException,
                                    NotEnoughItemsException{
        /*
         * Method to add an animal to the farm
         */
        AnimalChunk animalCunk = (AnimalChunk)argument.getArg1();
        AnimalAbstract animal = (AnimalAbstract)argument.getArg2();
        Farmer farmer = (Farmer)this.person;


        farmer.getInventory().removeItem(animal, 1);
        animalCunk.setAnimal(animal);
        animalCunk.getActions().resetActions();
        animalCunk.getActions().updateActions(new HashSet<Action>(){{add(Action.REMOVE_ANIMAL);
                                                                     add(Action.FEED_ANIMAL);
                                                                     add(Action.GIVE_WATER);}}, true);
        animalCunk.getLand().getActions().updateActions(new HashSet<Action>(){{add(Action.FEED_ALL_ANIMALS);
                                                                               add(Action.GIVE_WATER_ALL);}}, true);                                                              
    }

    public void remove_animal() throws NoItemFoundException, 
                                       InventoryIsFullException,
                                       NotEnoughItemsException,
                                       CloneNotSupportedException{
        /*
         * Method to remove an animal from the farm
         */
        AnimalChunk animalCunk = (AnimalChunk)argument.getArg1();
        Farmer farmer = (Farmer)this.person;

        farmer.getInventory().addItem(animalCunk.getAnimal());
        animalCunk.removeAnimal();
        animalCunk.getActions().resetActions();
        
        animalCunk.getActions().updateActions(new HashSet<Action>(){{add(Action.ADD_ANIMAL);}}, true);

        // if all the chunks do not have animals, remove the get all resources action
        if (animalCunk.getLand().getNumElements() == 0){
            animalCunk.getLand().getActions().resetActions();
        } 
    }

    public void get_resources() throws InventoryIsFullException, 
                                       NoItemFoundException,
                                       NotEnoughItemsException,
                                       CloneNotSupportedException{
        /*
         * Method to get resources from the farm
         */
        AnimalChunk chunk = (AnimalChunk)argument.getArg1();
        Farmer farmer = (Farmer)this.person;
        Item item = (Item)argument.getArg2();

        for (Item product : chunk.getAnimal().getProducts()){
            // if the animal is a goat and the farmer has scissors, the wool will be cut
            if (chunk.getAnimal().getType() == ItemType.Animals.GOAT &&
                item != null &&
                item.getType() == ItemType.Tools.SCISSORS &&
                product.getType() == ItemType.productsType.WOOL &&
                damageTool(item)){

                product.setNumber(product.getNumber()*(((AbstractTool)(item)).getMaterial().getModifier()));
            }
            farmer.getInventory().addItem(product);
        }
        // if the animal has no products, remove the get resources action
        if (chunk.getAnimal().areProductsAvailable()){
            chunk.getActions().updateActions(new HashSet<Action>(){{add(Action.GET_RESOURCES);}}, true);
        } else {
            chunk.getActions().updateActions(new HashSet<Action>(){{add(Action.GET_RESOURCES);}}, false);
        }
        // if the animal is dead, remove it from the farm
        if (!chunk.getAnimal().isAlive()){
           chunk.removeAnimal();
           chunk.getActions().resetActions();
            chunk.getActions().updateActions(new HashSet<Action>(){{add(Action.ADD_ANIMAL);}}, true);
        }
    }

    public void feed_animal() throws NoFoodFoundException, 
                                     MinimumHungerException, 
                                     NoItemFoundException, 
                                     NotEnoughItemsException{
        /*
         * Method to feed an animal
         */
        AnimalChunk chunk = (AnimalChunk)argument.getArg1();
        Item food = (Item)argument.getArg2();
        Farmer farmer = (Farmer)this.person;

        chunk.getAnimal().feed(food);
        farmer.getInventory().removeItem(food, 1);
    }

    public void give_water() throws MaxWaterLevelReachedException, 
                                    IllegalAccessException{
        /*
         * Method to give water to an animal
         */
        AnimalChunk chunk = (AnimalChunk)argument.getArg1();

        chunk.getAnimal().waterAnimal();
    }

    public void feed_all_animals() throws NoSuchMethodException, 
                                          IllegalAccessException, 
                                          InvocationTargetException, 
                                          ActionNotAvailableException{
        /*
         * Method to feed all animals
         */
        this.doAll(Action.FEED_ANIMAL);
    }

    public void give_water_all() throws NoSuchMethodException, 
                                        IllegalAccessException, 
                                        InvocationTargetException, 
                                        ActionNotAvailableException{
        /*
         * Method to give water to all animals
         */
        this.doAll(Action.GIVE_WATER);
    }

    public void get_all_resources() throws IllegalAccessException, 
                                           IllegalArgumentException, 
                                           InvocationTargetException, 
                                           SecurityException, 
                                           ActionNotAvailableException{
        /*
         * Method to get all resources from the farm
         */
        doAll(Action.GET_RESOURCES);
    }

}
