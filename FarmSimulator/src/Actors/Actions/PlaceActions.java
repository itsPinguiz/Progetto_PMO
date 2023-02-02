package Actors.Actions;

import java.util.HashSet;

import Actors.Person.*;
import Place.Place;
import Place.Barn.Barn;
import Place.Land.*;

public class PlaceActions extends ActionsManager{
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
                                     add("moveItem");
                                     }};
    }

    public PlaceActions(PlantChunk ChunkPlantLand){
        /*
         * Constructor for a specific chunk of land
         */
        super();
        this.place = ChunkPlantLand;
        super.availableActions = new HashSet<>(){{
                                     add("plow");
        }};
    }
}
