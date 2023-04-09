package model.exceptions;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import model.actors.actions.ActionsManager.Action;
import model.item.ItemType;
import model.place.Place;
import model.place.Places;

public class CustomExceptions {
    public static class ActionNotAvailableException extends Exception {
        public ActionNotAvailableException(Action action,Set<Action> availableActions){
            super("The action you tried to execute was not possible. \n Action: " +
                  action.toString() + " \n Available actions: " + availableActions.toString());
        }   
    }

    public static class LandIsNotPlowedException extends Exception {
        public LandIsNotPlowedException(){
            super("Cannot perform action since the land is not plowed.");
        }   
    }

    public static class LandIsAlreadyPlowedException extends Exception {
        public LandIsAlreadyPlowedException(){
            super("Cannot perform action since the land is already plowed.");
        }   
    }

    public static class NoToolFoundException extends Exception {
        public NoToolFoundException(ItemType wrongTool, ItemType rightTool){
            JOptionPane.showMessageDialog(null,"Cannot perform action since the right tool is not present in your inventory:\n Used tool: "
            + wrongTool.toString() + " \n Expected: " + rightTool.toString());
        }   
    }

    public static class InventoryIsFullException extends Exception {
        public InventoryIsFullException(){
            super("Cannot perform action since the the inventory is too full.");
        }   
    }

    public static class PlaceNotAvailableException extends Exception {
        public PlaceNotAvailableException(Place enteringPlace,HashSet<Places> availablePlaces){
            super("Cannot perform action since the role you are using cannot enter " + enteringPlace.toString() +
                   ". \n Available places: " + availablePlaces.toString());
        }   
    }

    public static class NoSeedFoundException extends Exception {
        public NoSeedFoundException(){
            super("Cannot perform action since you have no seed selected in your inventory.");
        }   
    }
    
    public static class NoProductFoundException extends Exception {
        public NoProductFoundException(){
            super("Cannot perform action since this product doesn't exist.");
        }   
    }

    public static class NoAnimalFoundException extends Exception {
        public NoAnimalFoundException(){
            super("Cannot perform action since this animal doesn't exist.");
        }   
    }

    public static class NoEnoughMoneyException extends Exception {
        public NoEnoughMoneyException(){
            JOptionPane.showMessageDialog(null, "You don't have enough money to buy this item.");
        }   
    }

    public static class CannotBuyItemException extends Exception {
        public CannotBuyItemException(){
            super("Cannot perform action since the item is not in the market.");
        }   
    }

    public static class NoItemFoundException extends Exception {
        public NoItemFoundException(){
            super("Cannot perform action since this item doesn't exist.");
        }   
    }

    public static class NoSellableLandException extends Exception{
        public NoSellableLandException(){
            super("Cannot perform action since this land is not sellable.");
        }
    }

    public static class NoFoodFoundException extends Exception {
        public NoFoodFoundException(){
            super("Cannot perform action since you have no food selected in your inventory.");
        }   
    }

    public static class MaxWaterLevelReachedException extends Exception {
        public MaxWaterLevelReachedException(){
            super("Cannot perform action since the water level is already at maximum.");
        }   
    }
}
