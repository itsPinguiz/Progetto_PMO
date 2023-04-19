package app;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import model.Constants;
import model.Model;
import model.actors.actions.ActionsManager.Action;
import model.actors.person.Farmer;
import model.actors.person.Landlord;
import model.actors.person.PersonAbstract.Role;
import model.item.Item;
import model.item.ItemType;
import model.item.plants.PlantAbstract.PlantLife;
import model.item.plants.products.Products;
import model.place.land.PlantLand;
import model.place.land.AnimalLand;
import model.place.land.LandAbstract;
import model.place.land.chunks.AnimalChunk;
import model.place.land.chunks.PlantChunk;
import model.place.Places;

public class MainTest {

    // Test for the planting of a carrot in a chunk and then harvesting it
    @Test
    public void plantedToHarvestCarrot() throws Exception{

        Model model = new Model();


        model.setSelectedPerson(model.getPlayer().get(Role.FARMER)); // select farmer
        assertEquals(Role.FARMER, model.getSelectedPerson().getRole()); // ASSERT that the selected person is Farmer
        model.getSelectedPerson().getActions().enter(model.getLands().get(0)); // enter plant land
        assertEquals(model.getLands().get(0), model.getSelectedPerson().getPlace()); // ASSERT that the farmer is in land


        final Model tmpModel = model;
        Farmer f = (Farmer)model.getSelectedPerson();
        model.setSelectedItem(f.getInventory().getInventory().get(2)); // get the hoe
        model.getSelectedPerson().getActions().executeAction(Action.PLOW_ALL,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                add(tmpModel.getSelectedItem());
                                                                                                add(tmpModel.getMap());}});

        PlantLand land = (PlantLand) model.getLands().get(0);
        Iterator<PlantChunk> iterator = land.iterator();

        
        while (iterator.hasNext()) {
            PlantChunk p = iterator.next();

            model.getSelectedPerson().getActions().enter(p);
            assertEquals(true, p.isPlowed()); // ASSERT that all the chunks are plowed
        }

        f = (Farmer)model.getSelectedPerson();
        model.setSelectedItem(f.getInventory().getInventory().get(0)); // get the carrot
        model.getSelectedPerson().getActions().executeAction(Action.PLANT,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                             add(tmpModel.getSelectedItem());
                                                                                             add(tmpModel.getMap());}});

        assertEquals(ItemType.Plants.CARROT,((PlantChunk)(f.getPlace())).getPlant().getType()); // ASSERT that the carrot is planted

        for(int i = 0; i < 3; i++){
            assertTrue("Water and fertilize aren't min", ((PlantChunk)(f.getPlace())).getFertilizationLevel() < 20 && ((PlantChunk)(f.getPlace())).getWaterLevel() < 20); 
            model.setSelectedItem(f.getInventory().getInventory().get(5)); // get the wateringcan
            model.getSelectedPerson().getActions().executeAction(Action.WATER,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                 add(tmpModel.getSelectedItem());
                                                                                                 add(tmpModel.getMap());}});
            model.setSelectedItem(f.getInventory().getInventory().get(6)); // get the fertilizer
            for(int j = 0; j < 2; j++){
                model.getSelectedPerson().getActions().executeAction(Action.FERTILIZE,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                     add(tmpModel.getSelectedItem());
                                                                                                     add(tmpModel.getMap());}});
            }
            assertTrue("Water and fertilize aren't max", ((PlantChunk)(f.getPlace())).getFertilizationLevel() == Constants.FERTILIZATION_MAX && ((PlantChunk)(f.getPlace())).getWaterLevel() == Constants.WATERING_MAX);
            for(int z = 0; z < 15; z++){
                model.update();
            }
            
        }
        assertEquals(PlantLife.HARVESTABLE, ((PlantChunk)(f.getPlace())).getPlant().getLifeStage()); // ASSERT that the carrot is harvestable

        

        int tmpCarrot = f.getInventory().getInventory().get(0).getNumber(); // temporary carrot to save actual number of carrots

        model.getSelectedPerson().getActions().executeAction(Action.HARVEST,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                               add(tmpModel.getSelectedItem());
                                                                                               add(tmpModel.getMap());}});
        assertEquals(tmpCarrot + Constants.HARVEST_MULTIPLIER, f.getInventory().getInventory().get(0).getNumber()); // ASSERT that there are 2 more carrots in inventory after harvesting
    }

    @Test
    public void addedAnimalToTheChunk() throws Exception{
        Model model = null;
        model = new Model();

        model.setSelectedPerson(model.getPlayer().get(Role.FARMER)); // select farmer
        assertEquals(Role.FARMER, model.getSelectedPerson().getRole()); // ASSERT that the selected person is Farmer
        model.getSelectedPerson().getActions().enter(((AnimalLand)(model.getLands().get(2))).getElements().get(0)); // enter animal land
        assertEquals(((AnimalLand)(model.getLands().get(2))).getElements().get(0), model.getSelectedPerson().getPlace()); // ASSERT that the farmer is in land

        final Model tmpModel = model;
        Farmer f = (Farmer)model.getSelectedPerson();

        model.setSelectedItem(f.getInventory().getInventory().get(1)); // get the chicken
        model.getSelectedPerson().getActions().executeAction(Action.ADD_ANIMAL,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                  add(tmpModel.getSelectedItem());
                                                                                                  add(tmpModel.getMap());}});
        assertEquals(ItemType.Animals.CHICKEN, ((AnimalChunk)(f.getPlace())).getAnimal().getType()); // ASSERT that the animal has been added in the chunk
        
        // make time pass
        for(int i = 0; i < 33; i++){
            model.update();
        }
        // ASSERT that hunger and thirst are increased
        assertTrue("Hunger and thirst aren't increased", ((AnimalChunk)(f.getPlace())).getAnimal().getHunger() > 0 && ((AnimalChunk)(f.getPlace())).getAnimal().getThirst() > 0);
        
        // ASSERT that eggs are not present to the inventory
        assertEquals(-1, f.getInventory().searchItem(new Products(ItemType.productsType.EGGS) , false));

        // get resources after 33 days
        model.getSelectedPerson().getActions().executeAction(Action.GET_RESOURCES,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                     add(tmpModel.getSelectedItem());
                                                                                                     add(tmpModel.getMap());}});

        // ASSERT that eggs are added to the inventory
        assertNotEquals(-1, f.getInventory().searchItem(new Products(ItemType.productsType.EGGS) , false));
        
        int tmpWater = ((AnimalChunk)(f.getPlace())).getAnimal().getThirst();
        int tmpFood = ((AnimalChunk)(f.getPlace())).getAnimal().getHunger();

        // give water to the animal
        model.getSelectedPerson().getActions().executeAction(Action.GIVE_WATER,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                  add(tmpModel.getSelectedItem());
                                                                                                  add(tmpModel.getMap());}});
        // select carrot                                 
        model.setSelectedItem(f.getInventory().getInventory().get(0));
        
        // feed the animal
        model.getSelectedPerson().getActions().executeAction(Action.FEED_ANIMAL,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                   add(tmpModel.getSelectedItem());
                                                                                                   add(tmpModel.getMap());}});
        // ASSERT that hunger and thirst are decreased
        assertTrue("Hunger and thirst aren't decreased", ((AnimalChunk)(f.getPlace())).getAnimal().getHunger() < tmpFood && ((AnimalChunk)(f.getPlace())).getAnimal().getThirst() < tmpWater);

        // make time pass
        for(int i = 0; i < 5; i++){
            model.update();
        }
        // ASSERT that the animal is dead
        assertFalse("The animal isn't dead", ((AnimalChunk)(f.getPlace())).getAnimal().isAlive());

        // get resources after 5 days
        model.getSelectedPerson().getActions().executeAction(Action.GET_RESOURCES,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                     add(tmpModel.getSelectedItem());
                                                                                                     add(tmpModel.getMap());}});
        // ASSERT that after the animal is dead, it will give meat
        assertNotEquals(-1, f.getInventory().searchItem(new Products(ItemType.productsType.MEAT) , false)); 
        
        // ASSERT that the animal is not present in the chunk
        assertEquals(null, ((AnimalChunk)(f.getPlace())).getAnimal());
    }

    @Test
    public void theProductsShouldBeStacked() throws Exception{
        Model model = null;
        model = new Model();

        Random rand = new Random();
        int randomNumber = (rand.nextInt(3) + 1) * 63;
        int expectedStacks = randomNumber / Constants.STACK_MAX;
        int eggStack = 0;

        // if the number of eggs is not divisible by 64, then there will be one more stack
        if ((randomNumber) % Constants.STACK_MAX > 0) {
            expectedStacks++;
        }
        
        // add eggs to the inventory
        for(int i = 0; i < randomNumber; i++){
            ((Farmer)(model.getPlayer().get(Role.FARMER))).getInventory().addItem(new Products(ItemType.productsType.EGGS){{setNumber(1);}});
        }

        // count the number of stacks
        for(int i = 0; i < ((Farmer)(model.getPlayer().get(Role.FARMER))).getInventory().getInventory().size(); i++){
            if(((Farmer)(model.getPlayer().get(Role.FARMER))).getInventory().getInventory().get(i).getType() == ItemType.productsType.EGGS){
                eggStack ++;
            }
        }
        // ASSERT that the number of stacks is correct
        assertEquals(expectedStacks, eggStack);
    }

    @Test
    public void boughtItemShouldBeAddedToTheBarn() throws Exception{
        Model model = null;
        model = new Model();

        model.setSelectedPerson(model.getPlayer().get(Role.LANDLORD)); // select landlord
        assertEquals(Role.LANDLORD, model.getSelectedPerson().getRole()); // ASSERT that the selected person is Landlord

        model.getSelectedPerson().getActions().enter(model.getBarn()); // enter barn
        assertEquals(Places.BARN, model.getSelectedPerson().getPlace().getType()); // ASSERT that the landlord is in barn

        model.getSelectedPerson().getActions().enter(model.getBarn().getMarket()); // enter market
        assertEquals(Places.MARKET, model.getSelectedPerson().getPlace().getType()); // ASSERT that the landlord is in market

        model.setSelectedItem(model.getBarn().getMarket().getItemShop().getInventory().get(0)); // select the first item in the market

        Item item = (Item)model.getSelectedItem(); // get the item

        HashMap<Integer, Integer> barnBeforeBuy = new HashMap<>();
        HashMap<Integer, Integer> barnAfterBuy = new HashMap<>();

        for(int i = 0; i < (model.getBarn().getBarnInventory().getInventory().size()); i++){
            if(model.getBarn().getBarnInventory().getInventory().get(i).getType() == item.getType()){
                barnBeforeBuy.put(i, model.getBarn().getBarnInventory().getInventory().get(i).getNumber());
            }
        }

        final Model tmpModel = model;
        model.getSelectedPerson().getActions().executeAction(Action.BUY_ITEM, new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                 add(tmpModel.getSelectedItem());
                                                                                                 add(tmpModel.getMap());}}); // buy the item

        // ASSERT that the item is added to the barn
        for(int i = 0; i < model.getBarn().getBarnInventory().getInventory().size(); i++){
            if(model.getBarn().getBarnInventory().getInventory().get(i).getType() == item.getType()){
                barnAfterBuy.put(i, model.getBarn().getBarnInventory().getInventory().get(i).getNumber());
            }
        }
        assertFalse("The item was not added to the barn", barnBeforeBuy.equals(barnAfterBuy));
    }

    @Test
    public void balanceShouldBeGreaterAfterSell() throws Exception{
        Model model = null;
        model = new Model();

        model.setSelectedPerson(model.getPlayer().get(Role.LANDLORD)); // select landlord
        assertEquals(Role.LANDLORD, model.getSelectedPerson().getRole()); // ASSERT that the selected person is Landlord

        model.getSelectedPerson().getActions().enter(model.getBarn()); // enter barn
        assertEquals(Places.BARN, model.getSelectedPerson().getPlace().getType()); // ASSERT that the landlord is in barn

        model.getSelectedPerson().getActions().enter(model.getBarn().getMarket()); // enter market
        assertEquals(Places.MARKET, model.getSelectedPerson().getPlace().getType()); // ASSERT that the landlord is in market

        model.setSelectedItem(model.getBarn().getBarnInventory().getInventory().get(0));
        
        int balanceBeforeSell = ((Landlord)(model.getSelectedPerson())).getBalance();

        final Model tmpModel = model;
        model.getSelectedPerson().getActions().executeAction(Action.SELL_ITEM, new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                 add(tmpModel.getSelectedItem());
                                                                                                 add(tmpModel.getMap());}}); // buy the item

        int balanceAfterSell = ((Landlord)(model.getSelectedPerson())).getBalance();

        assertTrue("The balance hasn't increased", balanceAfterSell > balanceBeforeSell);
    }

    @Test
    public void landShouldBeAddedAfterBoughtIt() throws Exception{
        
        Model model = null;
        model = new Model();

        model.setSelectedPerson(model.getPlayer().get(Role.LANDLORD)); // select landlord
        assertEquals(Role.LANDLORD, model.getSelectedPerson().getRole()); // ASSERT that the selected person is Landlord

        model.getSelectedPerson().getActions().enter(model.getBarn()); // enter barn
        assertEquals(Places.BARN, model.getSelectedPerson().getPlace().getType()); // ASSERT that the landlord is in barn

        model.getSelectedPerson().getActions().enter(model.getBarn().getMarket()); // enter market
        assertEquals(Places.MARKET, model.getSelectedPerson().getPlace().getType()); // ASSERT that the landlord is in market

        model.setSelectedItem(model.getBarn().getMarket().getLandShop().get(0));
        assertTrue("Land not selected correctly", model.getSelectedItem() instanceof LandAbstract);

        int numberOfLandsBeforeBuy = 0;
        int numberOfLandsAfterBuy = 0;

        for (LandAbstract land : model.getLands()) {
            numberOfLandsBeforeBuy++;
        }

        final Model tmpModel = model;
        model.getSelectedPerson().getActions().executeAction(Action.BUY_ITEM, new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                  add(tmpModel.getSelectedItem());
                                                                                                  add(tmpModel.getMap());}});
        for (LandAbstract land : model.getLands()) {
            numberOfLandsAfterBuy++;
        }
        
        assertTrue("The land bought as not been added to the lands", numberOfLandsAfterBuy > numberOfLandsBeforeBuy);
    }
    
}


