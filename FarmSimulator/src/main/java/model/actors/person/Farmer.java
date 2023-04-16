/************************************************************
 * IMPORT AND PACKAGE
 ************************************************************/
package model.actors.person;

import model.Constants;
import model.actors.actions.PlayerActions;
import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.inventory.Inventory;
import model.item.ItemCreator;
 
 /***********************************************************
  * FARMER PERSON IMPLEMENTATION
  ***********************************************************/
 public class Farmer extends PersonAbstract{
     
     /**
      * Fields
      */
     private Inventory inventory;
     private ItemCreator creator;
 
     /**
      * Constructor for the Farmer class.
      * 
      * Initializes the farmer's actions, place, inventory and item creator.
      */
     public Farmer() throws NoAnimalFoundException, NoProductFoundException{
         super.personAction = new PlayerActions(this);
         super.place = null;
         this.role = Role.FARMER;
         this.inventory = new Inventory(Constants.INVENTORY_MAX);
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
 
