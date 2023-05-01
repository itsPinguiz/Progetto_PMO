package model.actors.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Constants;
import model.actors.person.Farmer;
import model.actors.person.Landlord;
import model.actors.person.Person;
import model.exceptions.CustomExceptions.*;
import model.item.Item;
import model.item.ItemType;
import model.item.animal.AnimalAbstract;
import model.item.plants.PlantAbstract;
import model.item.plants.PlantAbstract.PlantLife;
import model.item.tools.AbstractTool;
import model.place.GameMap;
import model.place.Place;
import model.place.Places;
import model.place.barn.Barn;
import model.place.barn.market.Market;
import model.place.land.LandAbstract;
import model.place.land.chunks.AnimalChunk;
import model.place.land.chunks.PlantChunk;

public class PlayerActions<T extends Person> extends ActionsManager{
    // attributes
    private T person;
    private ActionArguments<Place,?,GameMap> argument;

    // constructor    
    public PlayerActions(T person){
        this.person = person;
    }

    public <V> void executeAction(Action s,ActionArguments<Place,V,GameMap> argument) throws Exception{
        /*
        * Method to find a method to execute
        */

        // find the desired method
        Method method = getMethodByName(s.name().toLowerCase());
        this.argument = argument;

        // execute the method if it exists and is available
        if (method != null && this.availableActions.contains(s)) {
            person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), false);
            method.invoke(this);        
            person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), true);
        } else {
            throw new ActionNotAvailableException(s, this.availableActions);
        }
        argument = null;
    }

    private  Method getMethodByName(String methodName) {
        /*
         * Method to get a method by its name
         */
        return Arrays.stream(this.getClass().getDeclaredMethods())
                     .filter(m -> m.getName().equals(methodName.toLowerCase()))
                     .findFirst()
                     .orElse(null);
    }

    public void enter(Place p) throws PlaceNotAvailableException {
        /*
         * Method to change actions when
         * an actors enters somewhere
         */
        if (this.person.getAccessiblePlaces().contains(p.getType())){
            if (person.getPlace() != null)
                leave();
            this.person.setPlace(p);
            // the landlord cannot move items
            if (!(person instanceof Landlord && p.getType() == Places.BARN)){
                this.person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), true);
            }
        } else throw new PlaceNotAvailableException(p, this.person.getAccessiblePlaces());
    }

    public void leave() {
        /*
         * Method to change actions when
         * an actors leaves from somewhere
         */
        if (person.getPlace()!= null){
            person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), false);
        }
        person.setPlace(null);
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
        if (seed instanceof PlantAbstract) {
            if (c.isPlowed() && f.getInventory().searchItem(seed, false) != -1 && c.getPlant() == null) {
                // add plant to the land
                c.setPlant((PlantAbstract) (f.getInventory().getItem(1, seed)));
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
        if (tool.getType() == ItemType.Tools.WATERINGCAN && this.damageTool(tool)){
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

    private void doAll(Action action) throws IllegalAccessException,
                                             IllegalArgumentException,
                                             InvocationTargetException,
                                             SecurityException,
                                             ActionNotAvailableException{
        /*
        * Method to repeat the same action on all chunks in a land
        */                                                                            
        ((LandAbstract)argument.getArg1()).getElements().stream()
            .filter(chunk -> chunk.getActions().getAvailableActions().contains(action))
            .forEach(chunk -> {
                try {
                    this.argument.setArg1(chunk);
                    this.getMethodByName((action.toString().toLowerCase()).replace(' ', '_')).invoke(this);
                } catch (IllegalAccessException | 
                            IllegalArgumentException | 
                            InvocationTargetException | 
                            SecurityException  e) {
                    e.printStackTrace();
                }
        });
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


    private boolean damageTool(Item tool) throws NoItemFoundException,
                                                 NotEnoughItemsException{
        /*
         * Use an item and destroy it if it's worn out
         */
        Farmer f = (Farmer)this.person;

        if (tool != null && tool.getStatus() > 0){
            ((AbstractTool)tool).useTool();
            if (tool.getStatus() == 0){
                f.getInventory().removeItem(tool, 1);
            }
            return true;
        }
        return false;
    }

    // METHODS FOR THE LANDLORD
    public void buy_item() throws NoItemFoundException, 
                                  InventoryIsFullException, 
                                  CloneNotSupportedException,
                                  NoEnoughMoneyException,
                                  CannotBuyItemException {
        /*
         * Method to buy item
         * from the market
         */
        Item item;
        LandAbstract land;
        Landlord landlord = (Landlord)this.person;
        Market market = (Market)argument.getArg1();
        ArrayList<LandAbstract> lands =(ArrayList<LandAbstract>)(argument.getArg3().getLands());
        

        // check if the item is a land or not
        if (argument.getArg2() instanceof LandAbstract && !lands.contains(argument.getArg2())){
            land = (LandAbstract)argument.getArg2();

            if (lands.size()<10 && land.getPrice() <= landlord.getBalance()){
                lands.add(land);
                landlord.setBalance(- Constants.BASE_LAND_PRICE);
            }
            
        } else if (argument.getArg2() instanceof Item && market.getItemShop().getInventory().contains(argument.getArg2())){
            item = (Item)argument.getArg2();

            // add it to the barn
            market.getBarn().getBarnInventory().addItem(market.buyItem(item, landlord.getBalance()));
            // buy from maketz 
            // remove the money from the balance
            landlord.setBalance(- item.getPrice());
        } else {
            throw new CannotBuyItemException(argument.getArg2());
        }
    }

    public void sell_item() throws NoItemFoundException, 
                                   CloneNotSupportedException,
                                   NoSellableLandException,
                                   CannotSellItemException,
                                   NotEnoughItemsException{
        /*
         * Method to sell item
         * to the market
         */
        Market market = (Market)argument.getArg1();
        ArrayList<LandAbstract> lands =(ArrayList<LandAbstract>)(argument.getArg3().getLands());

        // check if the item is a land or not
         if (argument.getArg2() instanceof LandAbstract && lands.contains(argument.getArg2())){
            LandAbstract land = (LandAbstract)argument.getArg2();
            Landlord landlord = (Landlord)this.person;

            lands.remove(land);

            // add money to the balance
            landlord.setBalance(Constants.LAND_SELL_PRICE);
            
        } else if (argument.getArg2() instanceof Item && market.getBarn().getBarnInventory().getInventory().contains(argument.getArg2())){
            Item item = (Item)argument.getArg2();
            Landlord landlord = (Landlord)this.person;

            // remove item from the barn
            market.getBarn().getBarnInventory().removeItem(item,1);
            // add money to the balance
            landlord.setBalance(item.getPrice());
        } else {
            throw new CannotSellItemException(argument.getArg2());
        }
    }
}
