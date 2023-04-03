package Inventory;

import java.util.ArrayList;

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
        return this.inventory;
    }

    public int addItem(Item newTool) throws NoItemFoundException{
    	
    	int check = this.searchItem(newTool); 
    	
    	if(check != -1) {
    		if((this.inventory.get(check).getNumber() + newTool.getNumber()) > this.inventory.get(check).getMaxNumber()) {
    			newTool.setNumber(this.inventory.get(check).getMaxNumber() - this.inventory.get(check).getNumber());
    			this.inventory.get(check).setNumber(this.inventory.get(check).getMaxNumber());
					
    		}
			else {
				this.inventory.get(check).setNumber(this.inventory.get(check).getNumber() + newTool.getNumber());	
			}	
    	}
    	else if(this.inventory.size() < maxSize){
                this.inventory.add(newTool);
                System.out.println("\n" + newTool.getType() +
                                   " has been added in your inventory.");
            }
            else{
                System.out.println("\nThere's not enough space in your inventory, drop something to make space.");
            }

        return check;
    }

    public int removeItem(Item itemToRemove) throws NoItemFoundException{
        int itemIndex = searchItem(itemToRemove);
    
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
    

    public int searchItem(Item itemtofind) throws NoItemFoundException{
    	
    	int found = -1;
    	
    	for (Item item : inventory) {
    		if(item.getType() == itemtofind)
    			found = inventory.indexOf(item);
		}
    	return found;
    }

    public Item getItem(int numItemReq, Item itemRequest) throws CloneNotSupportedException, NoItemFoundException{
    	
    	Item copyItem = null;
    	// If the number of item requested is greater than the number of item in the inventory
    	if(this.inventory.get(this.searchItem(itemRequest)).getNumber() - numItemReq <= 0 ) {
    		copyItem =  this.inventory.get(this.searchItem(itemRequest));
    	}
        // If the number of item requested is smaller than the number of item in the inventory
    	else {
            copyItem = (Item)(inventory.get(this.searchItem(itemRequest)).clone());
            copyItem.setNumber(numItemReq);
            inventory.get(this.searchItem(itemRequest)).setNumber(inventory.get(this.searchItem(itemRequest)).getNumber() - numItemReq);
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
