package Market;

import java.util.ArrayList;
import Item.ItemCreator;
import Item.Interface.Item;

public class Market implements MarketInt{
    
    private final int MAX_SHOP_LENGTH = 20;
    private ArrayList<Item> itemShop;
    private ItemCreator itemCreator;

    public Market(){
        itemShop = new ArrayList<Item>(MAX_SHOP_LENGTH);
        this.itemCreator = new ItemCreator();
        itemShop.addAll(itemCreator.getWoodSet());
    }

    public ArrayList<Item> getItem(){
        return itemShop;
    }

    public void setItem(){
        this.itemShop.set(2, itemCreator.getWoodHoe());
    }

    public void removeItem(){

    }
}
