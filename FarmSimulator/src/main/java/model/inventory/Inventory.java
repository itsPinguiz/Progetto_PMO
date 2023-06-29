package model.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NotEnoughItemsException;
import model.item.Item;

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

    public ArrayList<Item> getItemInventory(){
        Collections.sort(this.inventory);
        return this.inventory;
    }

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

    
    public Optional<Integer> searchItem(Item itemtofind, boolean accLessMax) throws NoItemFoundException{
    	
    	return inventory.stream()
        .filter(item -> item.getType() == itemtofind.getType())
        .filter(item -> accLessMax ? item.getNumber() < item.getMaxNumber() : true)
        .map(inventory::indexOf)
        .findFirst();
    }

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

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void setItemInventory(int i, Item item) {
        this.inventory.set(i, item);
    }

}
