/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn.Market;

import java.util.ArrayList;

import Calendar.Calendar;
import Exceptions.CustomExceptions.NoEnoughMoneyException;
import Exceptions.CustomExceptions.NoItemFoundException;
import Exceptions.CustomExceptions.NoSellableLandException;
import Inventory.Inventory;
import Item.ItemCreator;
import Item.Interface.Item;
import Place.Land.AnimalLand;
import Place.Land.LandAbstract;
import Place.Land.PlantLand;

/**************
 * MARKET CLASS
 *************/
public class Market implements MarketInt{
    
    //attributes
    private final int MAX_SHOP_LENGTH = 10;
    private Inventory itemShop;
    private ItemCreator itemCreator;
    private ArrayList<LandAbstract> landShop;
    private Calendar c;
    
    //constructor
    public Market() throws NoItemFoundException{
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
    public Item buyItem (int itemIndex, int balance) throws NoEnoughMoneyException, NoItemFoundException{
        if(this.itemShop.getInventory().get(itemIndex).getPrice() <= balance){
            Item  tmp = this.itemShop.getInventory().get(itemIndex);
            this.itemShop.removeItem(this.itemShop.getInventory().get(itemIndex));
            return tmp;
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

