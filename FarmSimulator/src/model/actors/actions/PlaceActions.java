package model.actors.actions;

import java.util.HashSet;

import model.place.Place;

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
                    }};
                break;
            
            case BARN: // Barn
                super.availableActions = new HashSet<Action>(){{
                    add(Action.MOVE_ITEM);
                    }};
                break;

            case MARKET: // Market
                super.availableActions = new HashSet<Action>(){{
                    add(Action.BUY_ITEM);
                    add(Action.SELL_ITEM);
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
