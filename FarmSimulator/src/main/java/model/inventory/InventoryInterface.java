package model.inventory;

import java.io.Serializable;

import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NotEnoughItemsException;
import model.item.Item;

public interface InventoryInterface extends Serializable {
    public int addItem(Item newItem) throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException;
    public void removeItem(Item item, int numItemReq) throws NoItemFoundException, NotEnoughItemsException;
    public Item getItem(int numItemReq, Item itemRequest) throws CloneNotSupportedException, NoItemFoundException;
    public int searchItem(Item itemtofind, boolean accLessMax) throws NoItemFoundException;
}
