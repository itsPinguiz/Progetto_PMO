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
    
    /**
     * Constructs a new Market object.
     *
     * @throws NoItemFoundException         if no item is found
     * @throws InventoryIsFullException     if the inventory is full
     * @throws NoProductFoundException      if no product is found
     * @throws NoAnimalFoundException       if no animal is found
     * @throws CloneNotSupportedException if cloning is not supported
     */
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

    /**
     * Adds a barn to the market.
     *
     * @param b the barn to add
     */
    public void addBarn(Barn b){
        this.barn = b;
    }

    /**
     * Returns the barn associated with the market.
     *
     * @return the barn associated with the market
     */
    public Barn getBarn(){
        return this.barn;
    }
    
    /**
     * Updates the item shop.
     *
     * @throws NoItemFoundException         if no item is found
     * @throws InventoryIsFullException     if the inventory is full
     * @throws CloneNotSupportedException if cloning is not supported
     */
    public void updateItemShop() throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException{
        if(c.getDay() % 7 == 0){
            replaceItem();
        }
    }

    /**
     * Replaces the items in the shop.
     *
     * @throws NoItemFoundException         if no item is found
     * @throws InventoryIsFullException     if the inventory is full
     * @throws CloneNotSupportedException if cloning is not supported
     */
    private void replaceItem() throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException{
        this.itemShop.getItemInventory().clear();

        IntStream.range(0, Constants.MARKET_SHOP_MAX)
                 .mapToObj(i -> this.itemCreator.getRandomItem())
                 .filter(item -> !this.itemShop.getItemInventory().contains(item))
                 .forEach(item -> {
                                    try {
                                        this.itemShop.addItem(item);
                                    } catch (NoItemFoundException | 
                                             InventoryIsFullException |
                                             CloneNotSupportedException e) {
                                        //e.printStackTrace();
                                    }

                });
    }
    
    /**
     * Buys an item from the shop.
     *
     * @param boughtItem the item to buy
     * @param balance    the balance available for the purchase
     * @return the bought item
     * @throws NoEnoughMoneyException if there is not enough money for the purchase
     * @throws NoItemFoundException   if no item is found
     * @throws CloneNotSupportedException if cloning is not supported
     */
    public Item buyItem (Item boughtItem, int balance) throws NoEnoughMoneyException, NoItemFoundException, CloneNotSupportedException{
        if(boughtItem.getPrice() <= balance){
            return this.itemShop.getItem(1, boughtItem);
        }
        else{
            throw new NoEnoughMoneyException();
        }
    }
    
    /**
     * Buys a land from the shop.
     *
     * @param landIndex the index of the land to buy
     * @param balance   the balance available for the purchase
     * @return the bought land
     * @throws NoEnoughMoneyException if there is not enough money for the purchase
     */
    public LandAbstract buyLandAbstract(int landIndex, int balance) throws NoEnoughMoneyException{
        if(this.landShop.get(landIndex).getPrice() <= balance){
            return this.landShop.get(landIndex);
        }
        else{
            throw new NoEnoughMoneyException();
        }
    }

    /**
     * Sells an item to the market.
     *
     * @param itemToSell the item to sell
     * @return the price of the item
     * @throws NoItemFoundException if no item is found
     */
    public int sellItem(Item itemToSell) throws NoItemFoundException{
        return (itemToSell.getPrice()/2);
    }

    /**
     * Returns the item shop.
     *
     * @return the item shop
     */
    public Inventory getItemShop() {
        
        return this.itemShop;
    }

    /**
     * Returns the land shop.
     *
     * @return the land shop
     */
    public ArrayList<LandAbstract> getLandShop() {
        return this.landShop;
    }
}

