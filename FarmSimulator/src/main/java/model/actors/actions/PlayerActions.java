package model.actors.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

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
import model.place.Place;
import model.place.Places;
import model.place.barn.Barn;
import model.place.barn.market.Market;
import model.place.land.AnimalLand;
import model.place.land.LandAbstract;
import model.place.land.PlantLand;
import model.place.land.chunks.AnimalChunk;
import model.place.land.chunks.PlantChunk;

public class PlayerActions extends ActionsManager{
    // attributes
    private Person person;
    private HashSet<Places> accessiblePlaces;

    // constructor for Landlord    
    public PlayerActions(Landlord p){
        this.person = p;
        this.accessiblePlaces = new HashSet<>(){{
            add(Places.BARN);
            add(Places.MARKET);
            }};
    }
    
    // constructor for Farmer
    public PlayerActions(Farmer p){
        this.person = p;
        this.accessiblePlaces = new HashSet<>(){{
            add(Places.BARN);
            add(Places.ANIMAL_LAND);
            add(Places.PLANT_LAND);
            add(Places.PLANT_CHUNK);
            add(Places.ANIMAL_CHUNK);
            }};
    }

    public void executeAction(Action s,Object argument) throws Exception{
        /*
        * Method to find a method to execute
        */

        // find the desired method
        Method method = getMethodByName(s.name().toLowerCase());


        // execute the method if it exists and is available
        if (method != null && this.availableActions.contains(s)) {
            person.getActions().updateActions(person.getPlace().getActions().getActions(), false);
            method.invoke(this,argument);        
            person.getActions().updateActions(person.getPlace().getActions().getActions(), true);
        } else {
            throw new ActionNotAvailableException(s, this.availableActions);
        }

    }

    private  Method getMethodByName(String methodName) {
        /*
         * Method to get a method by its name
         */
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().equals(methodName.toLowerCase())) {
                return m;
            }
        }
        return null;
    }

    public void enter(Place p) throws PlaceNotAvailableException {
        /*
         * Method to change actions when
         * an actors enters somewhere
         */
        if (this.accessiblePlaces.contains(p.getType())){
            if (person.getPlace() != null)
                leave();
            this.person.setPlace(p);
            // the landlord cannot move items
            if (!(person instanceof Landlord && p.getType() == Places.BARN)){
                this.person.getActions().updateActions(person.getPlace().getActions().getActions(), true);
            }
        } else throw new PlaceNotAvailableException(p, this.accessiblePlaces);
    }

    public void leave() {
        /*
         * Method to change actions when
         * an actors leaves from somewhere
         */
        if (person.getPlace()!= null){
            person.getActions().updateActions(person.getPlace().getActions().getActions(), false);
        }
        person.setPlace(null);
    }

    // METHODS FOR THE FARMER
    public void move_item(ArrayList<? extends Object> items) throws CloneNotSupportedException,
                                                                    InventoryIsFullException,
                                                                    NoItemFoundException,
                                                                    NotEnoughItemsException{
        /*
         * Method to move the item
         * to the barn from the farmer's inventory
         */
        Item itemToMove = (Item)items.get(1);
        Farmer farmer = (Farmer)this.person;
        Barn barn = (Barn)farmer.getPlace();

        if (barn.getBarnInventory().getInventory().contains(itemToMove)){
            // if the item is in the barn, remove it and add it to the farmer's inventory
            try {
                farmer.getInventory().addItem(barn.getBarnInventory().getItem(1,itemToMove));
            } catch (NoItemFoundException e) {
                // if inventory is full, add the item back to the barn
                farmer.getInventory().removeItem(itemToMove,1);
                throw new NoItemFoundException();
            }
        } else if (farmer.getInventory().getInventory().contains(itemToMove)){
            // if the item is in the inventory, remove it and add it to the barn
            try {
                barn.getBarnInventory().addItem(farmer.getInventory().getItem(1,itemToMove));
            } catch (NoItemFoundException e) {
                // if the item was not found add the item back to the inventory
                barn.getBarnInventory().removeItem(itemToMove,1);
                throw new NoItemFoundException();
            }
        }
    }
    
    public void plant(ArrayList<? extends Object> items) throws LandIsNotPlowedException,
                                                                NoSeedFoundException, 
                                                                CloneNotSupportedException,
                                                                NoItemFoundException{
        /*
         * Method to plant a plant
         */
        Farmer f = (Farmer)this.person;
        PlantChunk c = (PlantChunk)items.get(0);
        Item seed = (Item)items.get(1);

        // check if the farmer has a seed and the chunk is plowed
        if (seed instanceof PlantAbstract){ 
            if(c.getDirtStatus() && f.getInventory().searchItem(seed, false) != -1 && c.getPlant() == null){
                // add plant to the land 
                c.setPlant((PlantAbstract)(f.getInventory().getItem(1,seed)));
                c.getPlant().planted(c);

                // add new possible actions
                c.getActions().resetActions();
                c.getActions().updateActions(new HashSet<>(){{
                    add(Action.WATER);
                    add(Action.FERTILIZE);
                    }}, true);
                c.getLand().getActions().updateActions(new HashSet<>(){{
                    add(Action.FERTILIZE_ALL);
                    add(Action.WATER_ALL);
                    }}, true);
                
                // if all lands are planted remove the plant all action
                if (c.getLand().getNumElements() == 10){
                    c.getLand().getActions().updateActions(new HashSet<>(){{
                        add(Action.PLANT_ALL);
                        }}, false);
                }
            } 
        } else throw new NoSeedFoundException();                                       
    }

    public void water(ArrayList<? extends Object> items) throws NoToolFoundException, 
                                                                NoItemFoundException,
                                                                NotEnoughItemsException{
        /*
         * Method to water a plant
         */
        PlantChunk c = (PlantChunk)items.get(0);
        Item tool = (Item)items.get(1);

        //check if the farmer has the watering can
        if (tool.getType() == ItemType.Tools.WATERINGCAN && this.damageTool(tool)){
            // increase water level
            c.setWaterLevel(Constants.WATERING_INDEX*(((AbstractTool)(tool)).getMaterial().getModifier()));
        } else throw new NoToolFoundException(tool.getType(),ItemType.Tools.WATERINGCAN);
    }

    public void plow(ArrayList<? extends Object> items) throws NoToolFoundException, 
                                                               LandIsAlreadyPlowedException, 
                                                               NoItemFoundException,
                                                               NotEnoughItemsException{
        /*
         * Method to plow dirt
         */
        PlantChunk c = (PlantChunk)items.get(0);
        Item tool = (Item)items.get(1);
        
        //check if the farmer has the hoe
        if (tool.getType() == ItemType.Tools.HOE && tool.getStatus() > 0 && this.damageTool(tool)){
            if (!c.getDirtStatus()){
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
            if (c.getLand().getElements().stream().filter(chunk -> chunk.getDirtStatus() == false).count() == 0){
                c.getLand().getActions().updateActions(new HashSet<>(){{
                    add(Action.PLOW_ALL);
                    }}, false);
            }
            } else throw new LandIsAlreadyPlowedException();
        }else throw new NoToolFoundException(tool.getType(),ItemType.Tools.HOE);
    }

    public void fertilize(ArrayList<? extends Object> items) throws NoToolFoundException, 
                                                                    NoItemFoundException,
                                                                    NotEnoughItemsException{
        /*
         * Method to fertilize a plant
         */
        PlantChunk c = (PlantChunk)items.get(0);
        Item tool = (Item)items.get(1);

        //check if the farmer has the fertilizer
        if (tool.getType() == ItemType.Tools.FERTILIZER && this.damageTool(tool)){ 
            // increase water level
            c.setFertilizationLevel(Constants.FERTILIZATION_INDEX*(((AbstractTool)(tool)).getMaterial().getModifier()));
        }else throw new NoToolFoundException(tool.getType(),ItemType.Tools.FERTILIZER);
    }

    public void harvest(ArrayList<? extends Object> items) throws InventoryIsFullException, 
                                                                  NoItemFoundException,
                                                                  NotEnoughItemsException{
        /*
         * Method to harvest a plant
         */
        PlantChunk c = (PlantChunk)items.get(0);
        Item tool = (Item)items.get(1);
        Farmer f = (Farmer)this.person;
        Item product;

        if (c.getPlant() == null || c.getPlant().getLifeStage() != PlantLife.HARVESTABLE) return;

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

        // if all the chunks do not have plants, remove the harvest all action
        if (c.getLand().getNumElements() == 0){
            c.getLand().getActions().updateActions(new HashSet<>(){{
                add(Action.FERTILIZE_ALL);
                add(Action.WATER_ALL);
                add(Action.HARVEST_ALL);
                }}, false);
        }

        if(c.getLand().getElements().stream().filter(chunk -> chunk.getPlant().getLifeStage() == PlantLife.HARVESTABLE).count() == 0){
            c.getLand().getActions().updateActions(new HashSet<>(){{
                add(Action.HARVEST_ALL);
                }}, false);
        }
    }

    public void plant_all(ArrayList<? extends Object> items) throws NoSuchMethodException,
                                                                    IllegalAccessException,
                                                                    InvocationTargetException,
                                                                    ActionNotAvailableException{
        /*
         * Method to plant all plants
         */
        this.doAll(items,Action.PLANT);
    }

    public void water_all(ArrayList<? extends Object> items) throws NoSuchMethodException,
                                                                    IllegalAccessException,
                                                                    InvocationTargetException,
                                                                    ActionNotAvailableException{
        /*
         * Method to water all plants
         */
        this.doAll(items,Action.WATER);
    }

    public void fertilize_all(ArrayList<? extends Object> items)throws NoSuchMethodException,
                                                                       IllegalAccessException,
                                                                       InvocationTargetException,
                                                                       ActionNotAvailableException{
        /*
         * Method to fertilize all plants
         */
        this.doAll(items,Action.FERTILIZE);
    }

    public void harvest_all(ArrayList<? extends Object> items) throws NoSuchMethodException,
                                                                      IllegalAccessException,
                                                                      InvocationTargetException,
                                                                      ActionNotAvailableException{
        /*
         * Method to harvest all plants
         */
        this.doAll(items,Action.HARVEST);
    }

    public void plow_all(ArrayList<? extends Object> items) throws NoSuchMethodException,
                                                                   IllegalAccessException,
                                                                   InvocationTargetException,
                                                                   ActionNotAvailableException{
        /*
         * Method to plow dirt
         */
        this.doAll(items,Action.PLOW);
    }

    private void doAll(ArrayList<? extends Object> items, Action action) throws IllegalAccessException,
                                                                                IllegalArgumentException,
                                                                                InvocationTargetException,
                                                                                SecurityException,
                                                                                ActionNotAvailableException {
        /*
         * Method to repeat the same action on all chunks in a land
         */

        if (items.get(0) instanceof PlantLand){
            PlantLand p = (PlantLand)items.get(0);
            for (PlantChunk chunk : p.getElements()){
                if (chunk.getActions().getActions().contains(action)){
                    this.getMethodByName((action.toString().toLowerCase()).replace(' ', '_')).invoke(this,new ArrayList<>() {{add(chunk); add(items.get(1));}});
                } 
            }
        } else if (items.get(0) instanceof AnimalLand){
            AnimalLand a = (AnimalLand)items.get(0);
            for (AnimalChunk chunk : a.getElements()){
                if (chunk.getAnimal()!=null){
                    if (chunk.getActions().getActions().contains(action)){
                        this.getMethodByName((action.toString().toLowerCase()).replace(' ', '_')).invoke(this,new ArrayList<>() {{add(chunk); add(items.get(1));}});
                    } 
                } 
            }
        }
    }

    public void add_animal(ArrayList<? extends Object> items) throws NoItemFoundException,
                                                                     NotEnoughItemsException{
        /*
         * Method to add an animal to the farm
         */
        AnimalChunk animalCunk = (AnimalChunk)items.get(0);
        AnimalAbstract animal = (AnimalAbstract)items.get(1);
        Farmer farmer = (Farmer)this.person;


        farmer.getInventory().removeItem(animal, 1);
        animalCunk.setAnimal(animal);
        animalCunk.getActions().resetActions();
        animalCunk.getActions().updateActions(new HashSet<Action>(){{add(Action.GET_RESOURCES);
                                                                        add(Action.REMOVE_ANIMAL);
                                                                        add(Action.FEED_ANIMAL);
                                                                        add(Action.GIVE_WATER);}}, true);
        animalCunk.getLand().getActions().updateActions(new HashSet<Action>(){{add(Action.GET_ALL_RESOURCES);
                                                                               add(Action.FEED_ALL_ANIMALS);
                                                                               add(Action.GIVE_WATER_ALL);}}, true);

    }

    public void remove_animal(ArrayList<? extends Object> items) throws NoItemFoundException, 
                                                                        InventoryIsFullException,
                                                                        NotEnoughItemsException{
        /*
         * Method to remove an animal from the farm
         */
        AnimalChunk animalCunk = (AnimalChunk)items.get(0);
        Farmer farmer = (Farmer)this.person;

        farmer.getInventory().addItem(animalCunk.getAnimal());
        animalCunk.setAnimal(null);
        animalCunk.getActions().resetActions();
        
        animalCunk.getActions().updateActions(new HashSet<Action>(){{add(Action.ADD_ANIMAL);}}, true);

        // if all the chunks do not have animals, remove the get all resources action
        if (animalCunk.getLand().getNumElements() == 0){
            animalCunk.getLand().getActions().resetActions();
        } 
    }

    public void get_resources(ArrayList<? extends Object> items) throws InventoryIsFullException, 
                                                                        NoItemFoundException,
                                                                        NotEnoughItemsException{
        /*
         * Method to get resources from the farm
         */
        AnimalChunk chunk = (AnimalChunk)items.get(0);
        Farmer farmer = (Farmer)this.person;
        Item item = (Item)items.get(1);

        for (Item product : chunk.getAnimal().getProducts()){
            if (chunk.getAnimal().getType() == ItemType.Animals.GOAT &&
                item != null &&
                item.getType() == ItemType.Tools.SCISSORS &&
                product.getType() == ItemType.productsType.WOOL &&
                damageTool(item)){

                product.setNumber(product.getNumber()*(((AbstractTool)(item)).getMaterial().getModifier()));
            }
            System.out.println(product.getNumber());
            farmer.getInventory().addItem(product);
        }
    }

    public void feed_animal(ArrayList<? extends Object> items) throws NoFoodFoundException, MinimumHungerException, NoItemFoundException, NotEnoughItemsException{
        /*
         * Method to feed an animal
         */
        AnimalChunk chunk = (AnimalChunk)items.get(0);
        Item food = (Item)items.get(1);
        Farmer farmer = (Farmer)this.person;

        chunk.getAnimal().feed(food);
        farmer.getInventory().removeItem(food, 1);
    }

    public void give_water(ArrayList<? extends Object> items) throws MaxWaterLevelReachedException, 
                                                                     IllegalAccessException{
        /*
         * Method to give water to an animal
         */
        AnimalChunk chunk = (AnimalChunk)items.get(0);

        chunk.getAnimal().waterAnimal();
    }

    public void feed_all_animals(ArrayList<? extends Object> items) throws NoSuchMethodException, 
                                                                   IllegalAccessException, 
                                                                   InvocationTargetException, 
                                                                   ActionNotAvailableException{
        /*
         * Method to feed all animals
         */
        this.doAll(items,Action.FEED_ANIMAL);
    }

    public void give_water_all(ArrayList<? extends Object> items) throws NoSuchMethodException, 
                                                                        IllegalAccessException, 
                                                                        InvocationTargetException, 
                                                                        ActionNotAvailableException{
        /*
         * Method to give water to all animals
         */
        this.doAll(items,Action.GIVE_WATER);
    }

    public void get_all_resources(ArrayList<? extends Object> items) throws IllegalAccessException, 
                                                                            IllegalArgumentException, 
                                                                            InvocationTargetException, 
                                                                            SecurityException, 
                                                                            ActionNotAvailableException{
        /*
         * Method to get all resources from the farm
         */
        doAll(items, Action.GET_RESOURCES);
    }


    private boolean damageTool(Item tool) throws NoItemFoundException,
                                                 NotEnoughItemsException{
        /*
         * Use an item and destroy it if it's worn out
         */
        Farmer f = (Farmer)this.person;

        if (tool != null){
            ((AbstractTool)tool).useTool();
            if (tool.getStatus() == 0){
                f.getInventory().removeItem(tool, 1);
            }
            return true;
        }
        return false;
    }

    // METHODS FOR THE LANDLORD
    @SuppressWarnings("unchecked")
    public void buy_item(ArrayList<? extends Object> items) throws NoItemFoundException, 
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
        Market market = (Market)items.get(0);
        ArrayList<Place> lands =((ArrayList<ArrayList<Place>>)items.get(2)).get(1);
        

        // check if the item is a land or not
        if (items.get(1) instanceof LandAbstract && !lands.contains(items.get(1))){
            land = (LandAbstract)items.get(1);

            if (lands.size()<10 && land.getPrice() <= landlord.getBalance()){
                lands.add(land);
                landlord.setBalance(- land.getPrice());
            }
            
        } else if (items.get(1) instanceof Item && market.getItemShop().getInventory().contains(items.get(1))){
            item = (Item)items.get(1);

            // add it to the barn
            market.getBarn().getBarnInventory().addItem(market.buyItem(item, landlord.getBalance()));
            // buy from maketz 
            // remove the money from the balance
            landlord.setBalance(- item.getPrice());
        } else {
            throw new CannotBuyItemException(items.get(1));
        }
    }

    @SuppressWarnings("unchecked")
    public void sell_item(ArrayList<? extends Object> items) throws NoItemFoundException, 
                                                                    CloneNotSupportedException,
                                                                    NoSellableLandException,
                                                                    CannotSellItemException,
                                                                    NotEnoughItemsException{
        /*
         * Method to sell item
         * to the market
         */
        Market market = (Market)items.get(0);
        ArrayList<Place> lands =((ArrayList<ArrayList<Place>>)items.get(2)).get(1);

        // check if the item is a land or not
         if (items.get(1) instanceof LandAbstract && lands.contains(items.get(1))){
            LandAbstract land = (LandAbstract)items.get(1);
            Landlord landlord = (Landlord)this.person;

            lands.remove(land);

            // add money to the balance
            landlord.setBalance(market.sellLand(land));
            
        } else if (items.get(1) instanceof Item && market.getBarn().getBarnInventory().getInventory().contains(items.get(1))){
            Item item = (Item)items.get(1);
            Landlord landlord = (Landlord)this.person;

            // remove item from the barn
            market.getBarn().getBarnInventory().removeItem(item,1);
            // add money to the balance
            landlord.setBalance(item.getPrice());
        } else {
            throw new CannotSellItemException(items.get(1));
        }
    }
}