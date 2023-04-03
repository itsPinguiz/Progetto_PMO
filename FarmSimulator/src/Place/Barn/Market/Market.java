/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn.Market;

import Calendar.Calendar;
import Exceptions.CustomExceptions.NoEnoughMoneyException;
import Inventory.Inventory;
import Item.ItemCreator;
import Item.Interface.Item;

/**************
 * MARKET CLASS
 *************/
public class Market implements MarketInt{
    
    private final int MAX_SHOP_LENGTH = 10;
    private Inventory itemShop;
    private ItemCreator itemCreator;
    private Calendar c;
    
    public Market(){
        this.itemShop = new Inventory(MAX_SHOP_LENGTH);
        this.itemCreator = new ItemCreator();
        for(int i = 0; i < MAX_SHOP_LENGTH; i++){
            this.itemShop.addItem(this.itemCreator.getRandomItem());
        }
        this.c = Calendar.getInstance();
    }
    
    public void updateItemShop(){
        if(c.getDay() % 7 == 0){
            replaceItem();
        }
    }

    private void replaceItem(){
        for (int i = 0; i < itemShop.getInventory().size(); i++) {
            this.itemShop.setItemInventory(i, this.itemCreator.getRandomItem());
        }
    }

    public Item buyItem (int itemIndex, int balance) throws Exception{
        if(this.itemShop.getInventory().get(itemIndex).getPrice() <= balance){
            Item  tmp = this.itemShop.getInventory().get(itemIndex);
            this.itemShop.removeItem(this.itemShop.getInventory().get(itemIndex));
            return tmp;
        }
        else{
            throw new NoEnoughMoneyException();
        }
    }
}

