package Inventory;

import java.io.Serializable;
import Item.Interface.Item;

public interface InventoryInterface extends Serializable {
    public int addItem(Item newItem) throws Exception;
    public int removeItem(Item item) throws Exception;
    public Item getItem(int numItemReq, Item itemRequest) throws CloneNotSupportedException;
    public int searchItem(Item itemtofind);
}
