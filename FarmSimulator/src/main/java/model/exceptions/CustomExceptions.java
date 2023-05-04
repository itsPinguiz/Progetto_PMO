package model.exceptions;

import java.util.HashSet;
import java.util.Set;

import model.actors.actions.ActionsManager.Action;
import model.inventory.Inventory;
import model.item.Item;
import model.item.ItemType;
import model.item.plants.Plant;
import model.place.Place;
import model.place.Places;
import model.place.land.LandAbstract;

/**
 * Class that contains all the custom exceptions used in the game
 */
public class CustomExceptions {
    /*
     *  Player Exceptions
     */
    
    public static class ActionNotAvailableException extends Exception {
        /**
         * Exception thrown when the action is not available
         * @param action the action that was not available
         * @param availableActions the available actions
         */
        public ActionNotAvailableException(Action action,Set<Action> availableActions){
            super("\nThe action you tried to execute was not possible. \n Action: " +
                  action.toString() + " \n Available actions: " + availableActions.toString());
        }   

        @Override
        public String toString() {
            return "Action is not available";
        }
    }
    
    public static class PlaceNotAvailableException extends Exception {
        /**
         * Exception thrown when the place the action is trying to enter is not available for him
         * @param enteringPlace the place the action is trying to enter
         * @param availablePlaces the available places
         */
        public PlaceNotAvailableException(Place enteringPlace,HashSet<Places> availablePlaces){
            super("\nCannot perform action since the role you are using cannot enter " + enteringPlace.toString() +
                   ". \n Available places: " + availablePlaces.toString());
        }   

        @Override
        public String toString() {
            return "Place is not available";
        }
    }

    /*
     *  Farmer Exceptions
     */

    public static class NoToolFoundException extends Exception {
        public NoToolFoundException(ItemType wrongTool, ItemType rightTool){
            super("\nCannot perform action since the right tool is not present in your inventory:\n Used tool: "
            + wrongTool.toString() + " \n Expected: " + rightTool.toString());
        }   
    }

    public static class InventoryIsFullException extends Exception {
        public InventoryIsFullException(){
            super("\nCannot perform action since the the inventory is too full.");
        }   
    }

    /*
     * Plant Exceptions
     */

    public static class PlantIsNotHarvestableException extends Exception {
        /**
         * Exception thrown when trying to harvest but plant is not harvestable
         * @param plant to tell the user what's the plant's life stage
         */
        public PlantIsNotHarvestableException(Plant plant){
            super("\nCannot perform action since the plant is not harvestable yet.\n"+
                  "Plant's life stage: " + plant.getLifeStage().toString());
        }   

        @Override
        public String toString() {
            return "Plant is not harvestable";
        }
    }

    public static class MaxFertilizationLevelReachedException extends Exception {
        /**
         * Exception thrown when trying to plant and no seed is found
         */
        public MaxFertilizationLevelReachedException(){
            super("\nCannot perform action since the chunk is already at max fertilization level.");
        }   
    }
    
    public static class NoSeedFoundException extends Exception {
        /**
         * Exception thrown when trying to plant and no seed is found
         * @param inventory to tell the user what he has in the inventory
         */
        public NoSeedFoundException(Inventory inventory){
            super("\nCannot perform action since you have no seed selected in your inventory.\n" +
                  "Your inventory" + inventory.getInventory().toString());
        }   

        @Override
        public String toString() {
            return "No seed was found";
        }
    }

    public static class LandIsAlreadyPlowedException extends Exception {
        /**
         * Exception thrown when the land is already plowed
         */
        public LandIsAlreadyPlowedException(){
            super("\nCannot perform action since the land is already plowed.");
        }   

        @Override
        public String toString() {
            return "Land is already plowed";
        }
    }

    public static class LandIsNotPlowedException extends Exception {
        /**
         * Exception thrown when trying to plant a land that is not plowed
         */
        public LandIsNotPlowedException(){
            super("\nCannot perform action since the land is not plowed.");
        }   

        @Override
        public String toString() {
            return "Land is not plowed";
        }
    }

    /*
     * Market Exceptions
     */

    public static class NoEnoughMoneyException extends Exception {
        public NoEnoughMoneyException(){
            super("You don't have enough money to buy this item.");
        }   
    }

    public static class CannotBuyItemException extends Exception {
        public CannotBuyItemException(Object item){
            super("\nCannot perform action since "+ ((item instanceof LandAbstract)? ((LandAbstract)item).getType().toString()
                                                                                   : ((Item)item).getType().toString() )+ 
                                                                                   " is not in the market.");
        }   
    }

    public static class NoItemFoundException extends Exception {
        public NoItemFoundException(){
            super("\nCannot perform action since this item doesn't exist.");
        }   
    }

    public static class CannotSellItemException extends Exception {
        public CannotSellItemException(Object item){
            super("\nCannot perform action since "+ ((item instanceof LandAbstract)? ((LandAbstract)item).getType().toString()
                                                                                   : ((Item)item).getType().toString() )+ 
                                                                                   " is not in the barn.");
        }
    }

    public static class NoSellableLandException extends Exception{
        public NoSellableLandException(){
            super("\nCannot perform action since this land is not sellable.");
        }
    }

    /*
     * Animal Exceptions
     */

    public static class NoAnimalFoundException extends Exception {
        public NoAnimalFoundException(){
            super("\nCannot perform action since this animal doesn't exist.");
        }   
    }

    public static class NoFoodFoundException extends Exception {
        public NoFoodFoundException(){
            super("\nCannot perform action since you have no food selected in your inventory.");
        }   
    }

    public static class MaxWaterLevelReachedException extends Exception {
        public MaxWaterLevelReachedException(){
            super("\nCannot perform action since the water level is already at maximum.");
        }   
    }

    public static class MinimumHungerException extends Exception {
        public MinimumHungerException(){
            super("\nCannot perform action since the hunger level is already at minimum.");
        }
    }

    public static class NotEnoughItemsException extends Exception {
        public NotEnoughItemsException(int amount, int neededAmount){
            super("\nCannot perform action since you don't have enough items. \n You have: " + amount + " \n Needed: " + neededAmount);
        }
    }

    public static class NoProductFoundException extends Exception {
        public NoProductFoundException(){
            super("\nCannot perform action since this product doesn't exist.");
        }   
    }

    /*
     *  Game Backup Exceptions
     */
    
    public static class SaveIsCorruptedException extends Exception {
        /**
         * Exception thrown when the save the player is trying to load is corrupted
         * @param saveName the name of the save the player is trying to load
         */
        public SaveIsCorruptedException(String saveName){
            super("\nCannot load save " + saveName + " since it is corrupted.");
        }

        @Override
        public String toString() {
            return "Save is corrupted";
        }
    }
}
