package Exceptions;

public class CustomExceptions {
    public static class ActionNotAvailableException extends Exception {
        public ActionNotAvailableException(){
            super("The action you tried to execute was not possible.");
        }   
    }

    public static class LandIsNotPlowedException extends Exception {
        public LandIsNotPlowedException(){
            super("Cannot perform action since the land is not plowed.");
        }   
    }

    public static class NoToolFoundException extends Exception {
        public NoToolFoundException(){
            super("Cannot perform action since the right tool is not present in your inventory.");
        }   
    }

    public static class InventoryIsFullException extends Exception {
        public InventoryIsFullException(){
            super("Cannot perform action since the the inventory is too full.");
        }   
    }

    public static class PlaceNotAvailableException extends Exception {
        public PlaceNotAvailableException(){
            super("Cannot perform action since the role you are using cannot enter this place.");
        }   
    }

    public static class NoSeedFoundException extends Exception {
        public NoSeedFoundException(){
            super("Cannot perform action since you have no seed selected in your inventory.");
        }   
    }
}
