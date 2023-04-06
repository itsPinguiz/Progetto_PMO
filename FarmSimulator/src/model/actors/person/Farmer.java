/************************************************************
 * IMPORT AND PACKAGE
 ************************************************************/

 package model.actors.person;

 import model.actors.actions.PlayerActions;
import model.inventory.Inventory;
import model.item.ItemCreator;
 
 /***********************************************************
  * FARMER PERSON IMPLEMENTATION
  ***********************************************************/
 public class Farmer extends PersonAbstract{
     
     /**
      * Fields
      */
     private final int MAX_INVENTORY_SIZE = 10;
     private Inventory inventory;
     private ItemCreator creator;
 
     /**
      * Constructor for the Farmer class.
      * 
      * Initializes the farmer's actions, place, inventory and item creator.
      */
     public Farmer(){
         super.personAction = new PlayerActions(this);
         super.place = null;
         this.inventory = new Inventory(MAX_INVENTORY_SIZE);
         this.creator = new ItemCreator();
         this.inventory.setInventory(creator.getWoodSet());
     }
 
     /**
      * Returns a string representation of the farmer.
      * 
      * @return the string "Farmer".
      */
     @Override
     public String toString() {
         return "Farmer";
     }
 
     /**
      * Returns the inventory of the farmer.
      * 
      * @return the inventory of the farmer.
      */
     public Inventory getInventory() {
         return this.inventory;
     }
 }
 
