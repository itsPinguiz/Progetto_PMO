package Actors.Actions;

public abstract class PlaceActions extends Actions{
    //TODO Replace Object with the right class
    protected Object place;

    protected PlaceActions(Object place){
        super();
        this.place = place;
    }

    public void enter(Object place,Object person) {
        /*
         * Method to change actions when
         * an actors enters somewhere
         */
        // TODO Leave the last place if you were in one 
        // Enter the new place
    }

    public void leave(Object person) {
        /*
         * Method to change actions when
         * an actors leaves from somewhere
         */
        // TODO Leave the place you were in
        
    }
}
