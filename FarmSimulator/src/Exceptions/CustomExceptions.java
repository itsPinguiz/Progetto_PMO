package Exceptions;

public class CustomExceptions {
    public static class TooManyTries extends Exception {
        public TooManyTries(){
            super("Too many tries for the action.");
        }   
    }

    public static class LandIsNotPlowed extends Exception {
        public LandIsNotPlowed(){
            super("Cannot perform action since the land is not plowed.");
        }   
    }
}
