package Actors.Actions;

import java.util.HashSet;

import Place.Place;
import Place.Land.LandAbstract;

public class PlaceActions extends ActionsManager{
    protected Place place; // place where the action take place

    public PlaceActions(Place place){
        /*
         * Constructor for the plant land
         */
        super();
        this.place = place;

        // sets the default available actions for the place
        switch ((this.place).getType()) {
            case PLANT_LAND: // Plant Land
                super.availableActions = new HashSet<Action>(){{
                    add(Action.WATER_ALL);
                    add(Action.FERTILIZE_ALL);
                    add(Action.HARVEST_ALL);
                    add(Action.PLOW_ALL);
                    }};
                break;

            case ANIMAL_LAND: // Animal Land
                super.availableActions = new HashSet<Action>(){{
                    add(Action.ADD_ANIMAL);
                    add(Action.REMOVE_ANIMAL);
                    add(Action.GET_ALL_RESOURCES);
                    }};
                break;
            
            case BARN: // Barn
                super.availableActions = new HashSet<Action>(){{
                    add(Action.GET_ITEM);
                    add(Action.MOVE_ITEM);
                    }};
                break;

            case PLANT_CHUNK: // Plant Chunk
                super.availableActions = new HashSet<>(){{
                    add(Action.PLOW);
                    }};
                break;
                
            default:
                throw new IllegalArgumentException("This type of land does not exist.");
        }
        
    }
}
