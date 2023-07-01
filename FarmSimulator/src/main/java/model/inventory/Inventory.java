package model.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NotEnoughItemsException;
import model.item.Item;

/**
 * Represents an inventory, or a collection of items.
 * The inventory can hold a maximum number of items. It provides methods to add and remove items, get the current list
 * of items, and perform other operations related to the inventory management.
 */
public class Inventory implements InventoryInterface {
    
    private ArrayList<Item> inventory;
    private int maxSize;

    /**
     * Creates a new inventory.
     * 
     * @param length the maximum number of items the inventory can hold.
     * @throws IllegalArgumentException if the specified length is not positive.
     */
    public Inventory(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        this.maxSize = length;
        this.inventory = new ArrayList<Item>(length);
    }

    /**
     * Gets the list of items in the inventory.
     * 
     * @return the sorted list of items in the inventory.
     */
    public ArrayList<Item> getItemInventory(){
        Collections.sort(this.inventory);
        return this.inventory;
    }

     /**
     * Adds an item to the inventory.
     * 
     * @param item the item to be added.
     * @throws NoItemFoundException if the item cannot be found.
     * @throws InventoryIsFullException if the inventory is full.
     * @throws CloneNotSupportedException if the item cannot be cloned.
     */
    public int addItem(Item item) throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException{
    	
    	Optional<Integer> checkOpt = this.searchItem(item, true);

        if (checkOpt.isPresent()) {
            int check = checkOpt.get();
            if((this.inventory.get(check).getNumber() + item.getNumber()) > this.inventory.get(check).getMaxNumber()) {
                int remaining = item.getNumber();
                while (remaining > 0) {
                    if (this.inventory.get(check).getNumber() < this.inventory.get(check).getMaxNumber()){
                        int difference = this.inventory.get(check).getMaxNumber() - this.inventory.get(check).getNumber();
                        this.inventory.get(check).setNumber(this.inventory.get(check).getMaxNumber());
                        remaining -= difference;
                    } else{
                        Item newStack = (Item)item.clone();
                        int numToAdd = Math.min(newStack.getMaxNumber(), remaining);
                        newStack.setNumber(numToAdd);
                        this.inventory.add(newStack);
                        remaining -= numToAdd;
                    }
                }
            } else {
                this.inventory.get(check).setNumber(this.inventory.get(check).getNumber() + item.getNumber());
            }
        } else if(this.inventory.size() < maxSize) {
            if (item.getNumber() > item.getMaxNumber()){
                int remaining = item.getNumber();
                while (remaining > 0) {
                    if (remaining > item.getMaxNumber()){
                        Item newStack = (Item)item.clone();
                        newStack.setNumber(item.getMaxNumber());
                        this.inventory.add(newStack);
                        remaining -= item.getMaxNumber();
                    } else{
                        Item newStack = (Item)item.clone();
                        newStack.setNumber(remaining);
                        this.inventory.add(newStack);
                        remaining = 0;
                    }
                }
            } else {
                this.inventory.add(item);
            }
        } else {
            throw new InventoryIsFullException();
        }
        return checkOpt.orElse(-1);
    }

    /**
     * Removes an item from the inventory.
     * 
     * @param itemToRemove the item to be removed.
     * @param numItemReq the quantity of the item to remove.
     * @throws NoItemFoundException if the item cannot be found.
     * @throws NotEnoughItemsException if there are not enough items to remove.
     */
    public void removeItem(Item itemToRemove, int numItemReq) throws NoItemFoundException, NotEnoughItemsException{

        if(this.inventory.contains(itemToRemove)){

            if (itemToRemove.getNumber() == numItemReq) {
                // If the item to remove has the same quantity as the existing item, remove it completely
                inventory.remove(itemToRemove);
            } 
            else if (itemToRemove.getNumber() > numItemReq){
                // If the item to remove has a smaller quantity than the existing item, subtract the quantity
                itemToRemove.setNumber(itemToRemove.getNumber() - numItemReq);
            } 
            else {
                throw new NotEnoughItemsException(itemToRemove.getNumber(), numItemReq);
            }
        }
        else{
            throw new NoItemFoundException();
        }
    }

    /**
     * Searches for an item in the inventory.
     * 
     * @param itemtofind the item to find.
     * @param accLessMax a flag indicating whether to accept items with a quantity less than the maximum.
     * @throws NoItemFoundException if the item cannot be found.
     */
    public Optional<Integer> searchItem(Item itemtofind, boolean accLessMax) throws NoItemFoundException{
    	
    	return inventory.stream()
        .filter(item -> item.getType() == itemtofind.getType())
        .filter(item -> accLessMax ? item.getNumber() < item.getMaxNumber() : true)
        .map(inventory::indexOf)
        .findFirst();
    }

    /**
     * Retrieves an item from the inventory.
     * 
     * @param numItemReq the quantity of the item to retrieve.
     * @param itemRequest the item to retrieve.
     * @throws CloneNotSupportedException if the item cannot be cloned.
     * @throws NoItemFoundException if the item cannot be found.
     */
    public Item getItem(int numItemReq, Item itemRequest) throws CloneNotSupportedException, NoItemFoundException{
    	
    	Item copyItem = null;

        if(this.inventory.contains(itemRequest)){
            // If the number of item requested is greater than the number of item in the inventory
            if(itemRequest.getNumber() - numItemReq <= 0 ) {
                copyItem =  itemRequest;
                this.inventory.remove(itemRequest);
            }
            // If the number of item requested is smaller than the number of item in the inventory
            else {
                copyItem = (Item)itemRequest.clone();
                copyItem.setNumber(numItemReq);
                itemRequest.setNumber((itemRequest).getNumber() - numItemReq);
            }
        }
        else{
            throw new NoItemFoundException();
        }
        
        return copyItem;
    }

    /**
     * Sets the inventory to the specified list of items.
     * 
     * @param inventory the new list of items.
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Replaces the item at the specified position in the inventory with the specified item.
     * 
     * @param i the index of the item to replace.
     * @param item the item to place in the inventory.
     */
    public void setItemInventory(int i, Item item) {
        this.inventory.set(i, item);
    }

}
