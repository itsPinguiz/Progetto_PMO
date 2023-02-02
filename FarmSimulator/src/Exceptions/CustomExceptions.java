package Exceptions;

public class CustomExceptions {
    public static class ActionNotAvailable extends Exception {
        public ActionNotAvailable(){
            super("The action you tried to execute was not possible.");
        }   
    }

    public static class LandIsNotPlowed extends Exception {
        public LandIsNotPlowed(){
            super("Cannot perform action since the land is not plowed.");
        }   
    }
}
