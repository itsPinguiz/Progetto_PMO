package Actors.Actions;

import java.util.HashSet;

import Actors.Person.*;
import Barn.Barn;
import Land.*;
import Market.Market;

public class PlaceActions extends GameActions{
    //TODO Replace Object with the right class
    protected Object place; // place where the action take place

    public PlaceActions(LandPlant plantLand){
        /*
         * Constructor for the plant land
         */
        super();
        this.place = plantLand;
        super.availableActions = new HashSet<>(){{
                                     add("waterAll");
                                     add("fertilizeAll");
                                     add("harvestAll");
                                     add("plowAll");
                                     }};
    }

    public PlaceActions(LandAnimal animalLand){
        /*
         * Constructor for the animal land
         */
        super();
        this.place = animalLand;
        super.availableActions = new HashSet<>(){{
                                     add("addAnimal");
                                     add("removeAnimal");
                                     add("getAllResources");
                                     }};
    }

    public PlaceActions(Barn Barn){
        /*
         * Constructor for the barn
         */
        super();
        this.place = Barn;
        super.availableActions = new HashSet<>(){{
                                     add("getItem");
                                     }};
    }

    public PlaceActions(Market market){
        /*
         * Constructor for the barn
         */
        super();
        this.place = market;
        super.availableActions = new HashSet<>(){{
                                     add("buy");
                                     add("sell");
                                     }};
    }


    public PlaceActions(Object techLand){
        /*
         * Constructor for the tech land
         */
        super();
        this.place = techLand;
        super.availableActions = new HashSet<>(){{
                                     add("plowAll");
                                     }};
    }

    public PlaceActions(Integer ChunkPlantLand){
        /*
         * Constructor for a specific chunk of land
         */
        super();
        this.place = ChunkPlantLand;
        super.availableActions = new HashSet<>(){{
                                     add("plant");
        }};
    }

    public PlaceActions(String ChunkAnimalLand){
        /*
         * Constructor for a specific chunk of land
         */
        super();
        this.place = ChunkAnimalLand;
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
        }
        else if (person instanceof Landlord && this.place instanceof Barn){ 
                // The LandLord cannot enter lands
                // TODO add market methods to available actions
        } else{
            // Leave the land you where in
            if (person.getPlace() != null)
            leave(person);
            // TODO update the place (grow plants and animals)
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
