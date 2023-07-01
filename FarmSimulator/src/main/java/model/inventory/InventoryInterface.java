package model.inventory;

import java.io.Serializable;
import java.util.Optional;

import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NotEnoughItemsException;
import model.item.Item;

/**
 * This interface outlines the expected functionalities of an inventory.
 * It extends the Serializable interface, enabling objects that implement it to be converted into a byte stream and vice versa.
 */
public interface InventoryInterface extends Serializable {
    /**
     * Adds a new item to the inventory.
     *
     * @param newItem The new item to be added to the inventory.
     * @throws NoItemFoundException If the item is not found in the inventory.
     * @throws InventoryIsFullException If the inventory is full and can't accept new items.
     * @throws CloneNotSupportedException If the item cannot be cloned.
     * @return The index of the added item in the inventory.
     */
    public int addItem(Item newItem) throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException;

    /**
     * Removes a specific number of a certain item from the inventory.
     *
     * @param item The item to be removed.
     * @param numItemReq The quantity of the item to be removed.
     * @throws NoItemFoundException If the item is not found in the inventory.
     * @throws NotEnoughItemsException If the quantity of the item in the inventory is less than the required quantity to be removed.
     */
    public void removeItem(Item item, int numItemReq) throws NoItemFoundException, NotEnoughItemsException;

    /**
     * Retrieves a specific number of a certain item from the inventory.
     *
     * @param numItemReq The quantity of the item to be retrieved.
     * @param itemRequest The item to be retrieved.
     * @throws CloneNotSupportedException If the item cannot be cloned.
     * @throws NoItemFoundException If the item is not found in the inventory.
     * @return The item retrieved from the inventory.
     */
    public Item getItem(int numItemReq, Item itemRequest) throws CloneNotSupportedException, NoItemFoundException;

    /**
     * Searches for an item in the inventory.
     *
     * @param itemtofind The item to find.
     * @param accLessMax A flag indicating whether to accept items with a quantity less than the maximum.
     * @throws NoItemFoundException If the item is not found in the inventory.
     * @return An Optional containing the index of the found item in the inventory. If the item is not found, the Optional is empty.
     */
    public Optional<Integer> searchItem(Item itemtofind, boolean accLessMax) throws NoItemFoundException;
}
