/********************
 * IMPORT AND PACKAGE
 *******************/

package Actors.Person;

import java.io.Serializable;
import java.util.ArrayList;
import Actors.Actions.PlayerActions;
import Item.ItemCreator;
import Item.ItemType;
import Item.Interface.Item;

/******************************
 * FARMER PERSON IMPLEMENTATION
 *****************************/
public class Farmer extends PersonAbstract{
    
    /**
     * Fields
     */
    private final int MAX_INVENTORY_SIZE = 4;
    private ArrayList<Item> inventory;
    private ItemCreator creator;

    /**
     * Methods
     */
    public Farmer(){
        super.personAction = new PlayerActions(this);
        super.place = null;
        this.inventory = new ArrayList<Item>(MAX_INVENTORY_SIZE);
        this.creator = new ItemCreator();
        this.inventory = creator.getWoodSet();
    }

    public ArrayList<Item> getInventory(){
       return this.inventory;
    }

    public void removeItem(int i){
            System.out.println("\n" + this.inventory.get(i).getType() + " has been removed from your inventory.");
            this.inventory.remove(i);
    }

    public void addItem(Item newTool){
    	
    	int check = this.searchItem(newTool.getType()); 
    	
    	if(check != -1) {
    		
    		if((this.inventory.get(check).getNumber() + newTool.getNumber()) > this.inventory.get(check).getMaxNumber()) {
    			newTool.setNumber(this.inventory.get(check).getMaxNumber() - this.inventory.get(check).getNumber());
    			this.inventory.get(check).setNumber(this.inventory.get(check).getMaxNumber());
					
    		}
    		
			else {
				this.inventory.get(check).setNumber(this.inventory.get(check).getNumber() + newTool.getNumber());
				
			}	
    		
    		
    	}
    	
    	else if(this.inventory.size() < MAX_INVENTORY_SIZE){
                this.inventory.add(newTool);
                System.out.println("\n" + newTool.getType() +
                                   " has been added in your inventory.");
            }
            else{
                System.out.println("\nThere's not enough space in your inventory, drop something to make space.");
            }
    
    }

    public int searchItem(ItemType itemtofind){
    	
    	int found = -1;
    	
    	for (Item item : inventory) {
    		if(item.getType() == itemtofind)
    			found = inventory.indexOf(item);
		}
    	
    	return found;
    }
    
    public Item getItem(int numItemReq, Item itemRequest) {
    	
    	Item copyItem = null;
    	
    	if(this.inventory.get(this.searchItem(itemRequest.getType())).getNumber() - numItemReq <= 0 ) {
    		copyItem =  this.inventory.get(this.searchItem(itemRequest.getType()));
    	}
    	else {
    		try {
    			copyItem = (Item)(inventory.get(this.searchItem(itemRequest.getType())).clone());
    			copyItem.setNumber(numItemReq);
    			inventory.get(this.searchItem(itemRequest.getType())).setNumber(inventory.get(this.searchItem(itemRequest.getType())).getNumber() - numItemReq);
    			
			} catch (CloneNotSupportedException e) {
				System.err.println(e);
			}
		}
    	
    	return copyItem;
    	
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Farmer";
    }
    

}
