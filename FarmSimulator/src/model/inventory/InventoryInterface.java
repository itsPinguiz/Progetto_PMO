package model.inventory;

import java.io.Serializable;

import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.item.Item;

public interface InventoryInterface extends Serializable {
    public int addItem(Item newItem) throws NoItemFoundException, InventoryIsFullException;
    public int removeItem(Item item) throws NoItemFoundException;
    public Item getItem(int numItemReq, Item itemRequest) throws CloneNotSupportedException, NoItemFoundException;
    public int searchItem(Item itemtofind, boolean accLessMax) throws NoItemFoundException;
}
