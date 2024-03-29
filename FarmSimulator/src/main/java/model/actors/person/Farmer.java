/************************************************************
 * IMPORT AND PACKAGE
 ************************************************************/
package model.actors.person;

import java.util.HashSet;

import model.Constants;
import model.actors.actions.playerActions.FarmerActions;
import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.inventory.Inventory;
import model.item.ItemCreator;
import model.place.Places;
 
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
         super.personAction = new FarmerActions(this);
         super.place = null;
         this.role = Role.FARMER;
         this.accessiblePlaces = new HashSet<>(){{
            add(Places.BARN);
            add(Places.ANIMAL_LAND);
            add(Places.PLANT_LAND);
            add(Places.PLANT_CHUNK);
            add(Places.ANIMAL_CHUNK);
            }};
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
 
