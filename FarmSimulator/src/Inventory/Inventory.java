package Inventory;

import java.util.ArrayList;
import java.util.Collections;
import Exceptions.CustomExceptions.InventoryIsFullException;
import Exceptions.CustomExceptions.NoItemFoundException;
import Item.Interface.Item;

public class Inventory implements InventoryInterface {
    
    private ArrayList<Item> inventory;
    private int maxSize;

    public Inventory(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        this.maxSize = length;
        this.inventory = new ArrayList<Item>(length);
    }

    public ArrayList<Item> getInventory(){
        Collections.sort(this.inventory);
        return this.inventory;
    }

    public int addItem(Item newTool) throws NoItemFoundException, InventoryIsFullException{
    	
    	int check = this.searchItem(newTool, true); 
    	
        // If the item is already in the inventory, add the number of the new item to the existing item
    	if(check != -1) {
            // If the new item has more quantity than the max number of the existing item, set the new item quantity to the max number of the existing item
    		if((this.inventory.get(check).getNumber() + newTool.getNumber()) > this.inventory.get(check).getMaxNumber()) {
    			newTool.setNumber(this.inventory.get(check).getMaxNumber() - this.inventory.get(check).getNumber());
    			this.inventory.get(check).setNumber(this.inventory.get(check).getMaxNumber());		
    		}
            // Else, add the new item quantity to the existing item quantity
			else {
				this.inventory.get(check).setNumber(this.inventory.get(check).getNumber() + newTool.getNumber());	
			}	
    	}
        // If the item is not in the inventory, add it to the inventory
    	else if(this.inventory.size() < maxSize){
                this.inventory.add(newTool);
                System.out.println("\n" + newTool.getType() +
                                   " has been added in your inventory.");
            }
        else{
                throw new InventoryIsFullException();
            }

        return check;
    }

    public int removeItem(Item itemToRemove) throws NoItemFoundException{
        int itemIndex = searchItem(itemToRemove, false);
    
        if (itemIndex == -1) {
            System.out.println("\n" + itemToRemove.getType() + " is not in your inventory.");
        } else {
            Item existingItem = inventory.get(itemIndex);
            if (existingItem.getNumber() == itemToRemove.getNumber()) {
                // If the item to remove has the same quantity as the existing item, remove it completely
                inventory.remove(itemIndex);
            } else if (existingItem.getNumber() > itemToRemove.getNumber()) {
                // If the item to remove has a smaller quantity than the existing item, subtract the quantity
                existingItem.setNumber(existingItem.getNumber() - itemToRemove.getNumber());
            } else {
                // If the item to remove has a larger quantity than the existing item, print an error message
                System.out.println("\nYou only have " + existingItem.getNumber() + " " + itemToRemove.getType() + " in your inventory.");
            }
        }
        return itemIndex;
    }
    
    public int searchItem(Item itemtofind, boolean accLessMax) throws NoItemFoundException{
    	
    	int found = -1;
    	
    	for (Item item : inventory) {
                if(item.getType() == itemtofind.getType())
                if(accLessMax) {
                    if(item.getNumber() < item.getMaxNumber()) {
                        found = inventory.indexOf(item);
                    }
                }
                else {
                    found = inventory.indexOf(item);
                }
		}
        
    	return found;
    }

    public Item getItem(int numItemReq, Item itemRequest) throws CloneNotSupportedException, NoItemFoundException{
    	
    	Item copyItem = null;

    	// If the number of item requested is greater than the number of item in the inventory
    	if(this.inventory.get(this.searchItem(itemRequest, false)).getNumber() - numItemReq <= 0 ) {
    		copyItem =  this.inventory.get(this.searchItem(itemRequest, false));
    	}
        // If the number of item requested is smaller than the number of item in the inventory
    	else {
            copyItem = (Item)(inventory.get(this.searchItem(itemRequest, false)).clone());
            copyItem.setNumber(numItemReq);
            inventory.get(this.searchItem(itemRequest,false)).setNumber(inventory.get(this.searchItem(itemRequest,false)).getNumber() - numItemReq);
        }
        
        return copyItem;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void setItemInventory(int i, Item item) {
        this.inventory.set(i, item);
    }

}
