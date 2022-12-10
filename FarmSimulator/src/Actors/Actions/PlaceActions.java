package Actors.Actions;

import java.util.HashSet;

import Actors.Person.*;
import Land.*;

public abstract class PlaceActions extends GameActions{
    //TODO Replace Object with the right class
    protected Object place;

    public PlaceActions(LandPlant plantLand){
        super();
        this.place = plantLand;
        super.availableActions = new HashSet<>(){{
                                    add("plant");
                                }};
    }

    public PlaceActions(LandAnimal animalLand){
        super();
        this.place = animalLand;
        super.availableActions = new HashSet<>(){{
                                    add("getResources");
                                    add("addAnimal");
                                }};
    }

    public PlaceActions(Double Barn){
        super();
        this.place = Barn;
        super.availableActions = new HashSet<>(){{
                                    add("getItem");
                                }};
    }


    public PlaceActions(Object techLand){
        super();
        this.place = techLand;
        super.availableActions = new HashSet<>(){{
                                    add("plant");
                                }};
    }

    public void enter(Person person) {
        /*
         * Method to change actions when
         * an actors enters somewhere
         */
        if (person instanceof Landlord && this.place instanceof LandAbstract){ 
            // The LandLord cannot enter lands
            System.out.println("The Landlord cannot enter the lands");
        }else{
            // Leave the land you where in
            if (person.getPlace() != null)
            leave(person);
            // Enter the new place
            person.setPlace(this.place);
        }   
        /* TODO 
        person.getActions().addAction(person.getPlace().getActions().getActions())
        */
    }

    public void leave(Person person) {
        /*
         * Method to change actions when
         * an actors leaves from somewhere
         */
        // subtract current place actions to 
        // person availableActions
        //TODO leave the place
        //person.getActions().removeAction(person.getPlace().getActions().getActions())
        person.setPlace(null);
    }
}
