package Actors.Actions;

import java.util.HashMap;

public abstract class PlaceActions extends Actions{
    //TODO Replace Object with the right class
    protected Object place;

    public PlaceActions(String plantLand){
        super();
        this.place = plantLand;
        super.availableActions = new HashMap<>(){{
                                    put("plant","plant");
                                }};
    }

    public PlaceActions(Integer AnimalLand){
        super();
        this.place = AnimalLand;
        super.availableActions = new HashMap<>(){{
                                    put("plant","plant");
                                }};
    }

    public PlaceActions(Double Barn){
        super();
        this.place = Barn;
        super.availableActions = new HashMap<>(){{
                                    put("plant","plant");
                                }};
    }


    public PlaceActions(Object plantLand){
        super();
        this.place = plantLand;
        super.availableActions = new HashMap<>(){{
                                    put("plant","plant");
                                }};
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
