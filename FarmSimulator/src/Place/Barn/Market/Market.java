package Place.Barn.Market;

import java.util.ArrayList;
import Item.ItemCreator;
import Item.Interface.Item;

public class Market implements MarketInt{
    
    private final int MAX_SHOP_LENGTH = 10;
    private ArrayList<Item> itemShop;
    private ItemCreator itemCreator;

    public Market(){
        this.itemShop = new ArrayList<Item>(MAX_SHOP_LENGTH);
        this.itemCreator = new ItemCreator();
        this.itemShop.addAll(itemCreator.getWoodSet());
    }

    public ArrayList<Item> getItem(){
        return this.itemShop;
    }
    public void removeItem(int itemIndex){
        this.itemShop.remove(itemIndex);
    }

    public void upgradeItemShop(int actualDay){

        if(actualDay % 7 == 0){
            replaceItem();
        }
    }

    private void replaceItem(){
        this.itemShop.set((int)Math.random() * 10, this.itemCreator.getRandomItem((int)((Math.random() * 7))));
    }
    
    
}
