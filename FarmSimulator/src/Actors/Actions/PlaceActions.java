package Actors.Actions;

import java.util.HashSet;

import Actors.Person.*;
import Land.*;

public abstract class PlaceActions extends Actions{
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
        if (person instanceof Landlord && this.place instanceof Object){ //TODO Replace Object with Land
            // The LandLord cannot enter lands
            System.out.println("The Landlord cannot enter the lands");
        }else{
            // Leave the land you where in
            if (person.getPlace() != null)
            leave(person);
            // Enter the new place
            person.setPlace(this.place);
        }   
        // add list of new methods to the avaiableActions
        /* TODO
        Set<String> tempActions = Set.copyOf(person.actions.avaiableActions);
        person.setPlace(person.getPlace().union(tempActions))
        */
    }

    public void leave(Person person) {
        /*
         * Method to change actions when
         * an actors leaves from somewhere
         */
        // subtract current place actions to 
        // person availableActions
        /* TODO
        Set<String> tempActions = Set.copyOf(person.actions.avaiableActions);
        person.setPlace(person.getPlace().difference(tempActions))
        */
        //leave the place
        person.setPlace(null);
    }
}
