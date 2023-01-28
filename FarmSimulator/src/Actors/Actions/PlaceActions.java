package Actors.Actions;

import java.util.HashSet;
import java.util.Set;

import Actors.Person.*;
import Place.Place;
import Place.Barn.Barn;
import Place.Barn.Market.Market;
import Place.Land.*;

public class PlaceActions extends GameActions{
    //TODO Replace Object with the right class
    protected Place place; // place where the action take place

    public PlaceActions(PlantLand plantLand){
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

    public PlaceActions(AnimalLand animalLand){
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

    public PlaceActions(PlantChunk ChunkPlantLand){
        /*
         * Constructor for a specific chunk of land
         */
        super();
        this.place = ChunkPlantLand;
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
        // add the actions of the new place
        person.getActions().addActions(new HashSet<>(person.getPlace().getActions().getActions()));
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
