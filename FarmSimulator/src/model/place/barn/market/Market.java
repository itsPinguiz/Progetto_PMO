/********************
 * IMPORT AND PACKAGE
 *******************/

package model.place.barn.market;

import java.util.ArrayList;

import model.actors.actions.PlaceActions;
import model.calendar.Calendar;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoEnoughMoneyException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NoSellableLandException;
import model.inventory.Inventory;
import model.item.Item;
import model.item.ItemCreator;
import model.place.Place;
import model.place.Places;
import model.place.barn.Barn;
import model.place.land.AnimalLand;
import model.place.land.LandAbstract;
import model.place.land.PlantLand;

/**************
 * MARKET CLASS
 *************/
public class Market extends Place implements MarketInt{
    
    //attributes
    private final int MAX_SHOP_LENGTH = 10;
    private Inventory itemShop;
    private ItemCreator itemCreator;
    private ArrayList<LandAbstract> landShop;
    private Calendar c;
    private Barn barn;
    
    //constructor
    public Market() throws NoItemFoundException, InventoryIsFullException{
        super.type = Places.MARKET;
        super.actions = new PlaceActions(this);
        this.itemShop = new Inventory(MAX_SHOP_LENGTH);
        this.itemCreator = new ItemCreator();
        for(int i = 0; i < MAX_SHOP_LENGTH; i++){
            this.itemShop.addItem(this.itemCreator.getRandomItem());
        }
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
    public void updateItemShop(){
        if(c.getDay() % 7 == 0){
            replaceItem();
        }
    }

    //replace the items in the shop
    private void replaceItem(){
        for (int i = 0; i < itemShop.getInventory().size(); i++) {
            this.itemShop.setItemInventory(i, this.itemCreator.getRandomItem());
        }
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

    //sell a land to the shop
    public int sellLand(LandAbstract landToSell) throws NoSellableLandException{
        if(landToSell.isSellable()){
            return (LandAbstract.SELL_PRICE);
        }
        else{
            throw new NoSellableLandException();
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

