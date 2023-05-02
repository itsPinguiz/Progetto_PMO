package model.actors.actions.placeActions;

import java.util.HashSet;

import model.actors.actions.ActionsManager;
import model.place.Place;

/**
 * Class that represents the actions of a place
 */
public class PlaceActions extends ActionsManager{
    /**
     * Attributes
     */
    protected Place place; 

    /**
     * Constructor
     * Sets the default available actions for the place
     * @param place place to set the actions
     */
    public PlaceActions(Place place){
        super();
        this.place = place;

        // sets the default available actions for the place
        switch ((this.place).getType()) {
            case PLANT_LAND -> super.availableActions = new HashSet<Action>(){{add(Action.PLOW_ALL);}};
            case ANIMAL_LAND -> super.availableActions = new HashSet<Action>();
            case ANIMAL_CHUNK -> super.availableActions = new HashSet<Action>(){{add(Action.ADD_ANIMAL);}};
            case BARN -> super.availableActions = new HashSet<Action>(){{add(Action.MOVE_ITEM);}};
            case MARKET -> super.availableActions = new HashSet<Action>(){{add(Action.BUY_ITEM);add(Action.SELL_ITEM);}};
            case PLANT_CHUNK -> super.availableActions = new HashSet<>(){{add(Action.PLOW);}};
            default -> throw new IllegalArgumentException("This type of land does not exist.");
        }
    }
}
