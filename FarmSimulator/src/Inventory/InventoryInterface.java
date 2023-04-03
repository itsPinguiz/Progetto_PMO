package Inventory;

import java.io.Serializable;

import Exceptions.CustomExceptions.NoItemFoundException;
import Item.Interface.Item;

public interface InventoryInterface extends Serializable {
    public int addItem(Item newItem) throws NoItemFoundException;
    public int removeItem(Item item) throws NoItemFoundException;
    public Item getItem(int numItemReq, Item itemRequest) throws CloneNotSupportedException, NoItemFoundException;
    public int searchItem(Item itemtofind) throws NoItemFoundException;
}
