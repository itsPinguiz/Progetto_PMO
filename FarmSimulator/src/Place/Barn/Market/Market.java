/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn.Market;

import java.util.ArrayList;
import java.util.HashSet;

import Item.ItemCreator;
import Item.Interface.Item;

/**************
 * MARKET CLASS
 *************/
public class Market implements MarketInt{
    
    private final int MAX_SHOP_LENGTH = 10;
    private ArrayList<Item> itemShop;
    private ItemCreator itemCreator;
    private HashSet<Integer> boughtItems;
    

    public Market(){
        this.itemShop = new ArrayList<Item>(MAX_SHOP_LENGTH);
        this.itemCreator = new ItemCreator();
        for(int i = 0; i < MAX_SHOP_LENGTH; i++){
            this.itemShop.add(this.itemCreator.getRandomItem());
        }
        this.boughtItems = new HashSet<Integer>();
    }

    public ArrayList<Item> getItem(){
        return this.itemShop;
    }

    

    public void buyItem(int itemIndex){
        this.boughtItems.add(itemIndex);
    }

    {
        int availableIndex = -1;
        do {
            availableIndex = (int)(Math.random() * 10);
        } while(boughtItems.contains(availableIndex));
        this.itemShop.set(availableIndex, this.itemCreator.getRandomItem());
    }

    public void upgradeItemShop(int actualDay){
        if(actualDay % 7 == 0){
            replaceItem();
        }
    }

    private void replaceItem(){
        this.itemShop.set((int)Math.random() * 10, this.itemCreator.getRandomItem());
    }  
}
