/********************
 * IMPORT AND PACKAGE
 *******************/

package model.place.barn.market;

import java.util.ArrayList;
import java.util.stream.IntStream;

import model.Constants;
import model.actors.actions.placeActions.PlaceActions;
import model.calendar.Calendar;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoEnoughMoneyException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.inventory.Inventory;
import model.item.Item;
import model.item.ItemCreator;
import model.place.Place;
import model.place.Places;
import model.place.barn.Barn;
import model.place.land.LandAbstract;
import model.place.land.landTypes.AnimalLand;
import model.place.land.landTypes.PlantLand;

/**************
 * MARKET CLASS
 *************/
public class Market extends Place implements MarketInt{
    
    //attributes

    private Inventory itemShop;
    private ItemCreator itemCreator;
    private ArrayList<LandAbstract> landShop;
    private Calendar c;
    private Barn barn;
    
    //constructor
    public Market() throws NoItemFoundException, InventoryIsFullException,NoProductFoundException,NoAnimalFoundException, CloneNotSupportedException{
        super.type = Places.MARKET;
        super.actions = new PlaceActions(this);
        this.itemShop = new Inventory(Constants.MARKET_SHOP_MAX);
        this.itemCreator = new ItemCreator();
        replaceItem();
        this.c = Calendar.getInstance();

        //initialize the land shop
        this.landShop = new ArrayList<LandAbstract>();
        this.landShop.add(new AnimalLand());
        this.landShop.add(new PlantLand());
    }

    public void addBarn(Barn b){
        this.barn = b;
    }

    public Barn getBarn(){
        return this.barn;
    }
    
    //update the shop
    public void updateItemShop() throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException{
        if(c.getDay() % 7 == 0){
            replaceItem();
        }
    }

    //replace the items in the shop
    private void replaceItem() throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException{
        this.itemShop.getInventory().clear();

        IntStream.range(0, Constants.MARKET_SHOP_MAX)
                 .mapToObj(i -> this.itemCreator.getRandomItem())
                 .filter(item -> !this.itemShop.getInventory().contains(item))
                 .forEach(item -> {
                                    try {
                                        this.itemShop.addItem(item);
                                    } catch (NoItemFoundException e) {
                                        
                                        e.printStackTrace();
                                    } catch (InventoryIsFullException e) {
                                        
                                        e.printStackTrace();
                                    } catch (CloneNotSupportedException e) {
                                        
                                        e.printStackTrace();
                                    }
                });
    }
    
    //buy an item from the shop
    public Item buyItem (Item boughtItem, int balance) throws NoEnoughMoneyException, NoItemFoundException, CloneNotSupportedException{
        if(boughtItem.getPrice() <= balance){
            return this.itemShop.getItem(1, boughtItem);
        }
        else{
            throw new NoEnoughMoneyException();
        }
    }
    
    //buy a land from the shop
    public LandAbstract buyLandAbstract(int landIndex, int balance) throws NoEnoughMoneyException{
        if(this.landShop.get(landIndex).getPrice() <= balance){
            return this.landShop.get(landIndex);
        }
        else{
            throw new NoEnoughMoneyException();
        }
    }

    //return the price of the item
    public int sellItem(Item itemToSell) throws NoItemFoundException{
        return (itemToSell.getPrice()/2);
    }

    //return the item shop
    public Inventory getItemShop() {
        
        return this.itemShop;
    }

    //return the land shop
    public ArrayList<LandAbstract> getLandShop() {
        return this.landShop;
    }
}

