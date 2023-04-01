package Inventory;

import Item.ItemType;
import Item.Interface.Item;

public interface InventoryInterface {
    public void addItem(Item newItem) throws Exception;
    public void removeItem(Item item) throws Exception;
    public Item getItem(int numItemReq, Item itemRequest) throws CloneNotSupportedException;
    public int searchItem(ItemType itemtofind);
}
