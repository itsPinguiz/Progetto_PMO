package model.actors.actions;

import java.util.HashSet;
import java.util.Set;
// DESIGN PATTERN MEDIATOR

import model.item.ItemType;

public abstract class ActionsManager implements Actions{
    // attributes
    protected Set<Action> availableActions;

    // constructor
    protected ActionsManager(){
        this.availableActions = new HashSet<>();
    }

    public enum Action {
        /*
         * All possible actions
         */
        PLANT("Plant",item -> item instanceof ItemType.Plants),
        WATER("Water",item -> item == ItemType.Tools.WATERINGCAN),
        FERTILIZE("Fertilize",item -> item == ItemType.Tools.FERTILIZER),
        HARVEST("Harvest", item -> item == ItemType.Tools.SICKLE || item == null, true),
        PLOW("Plow",item -> item == ItemType.Tools.HOE),
        WATER_ALL("Water All",item -> item == ItemType.Tools.WATERINGCAN),
        FERTILIZE_ALL("Fertilize All",item -> item == ItemType.Tools.FERTILIZER),
        HARVEST_ALL("Harvest All",item -> item == ItemType.Tools.SICKLE || item == null, true),
        PLOW_ALL("Plow All",item -> item == ItemType.Tools.HOE),
        ADD_ANIMAL("Add Animal",item -> item instanceof ItemType.Animals),
        REMOVE_ANIMAL("Remove Animal",item -> item instanceof ItemType.Animals),
        FEED_ANIMAL("Feed Animal",item -> item instanceof ItemType.Plants),
        GIVE_WATER("Give Water",item -> true),
        GET_ALL_RESOURCES("Get All Resources",item -> true),
        GET_RESOURCES("Get Resources",item -> item instanceof ItemType.Animals),
        MOVE_ITEM("Move Item",item -> item instanceof ItemType),
        BUY_ITEM("Buy Item",item -> item instanceof ItemType),
        SELL_ITEM("Sell Item",item -> item instanceof ItemType);


        private final String name;
        private final ItemValidator itemValidator;
        private final boolean optional;

        Action(String name, ItemValidator itemValidator, boolean optional) {
            this.name = name;
            this.itemValidator = itemValidator;
            this.optional = optional;
        }

        Action(String name, ItemValidator itemValidator) {
            this(name, itemValidator, false);
        }

        public String getName() {
            return name;
        }

        public boolean isItemValid(ItemType item) {
            return itemValidator.validate(item);
        }

        public boolean isOptional() {
            return optional;
        }
    
        @FunctionalInterface
        public interface ItemValidator {
            boolean validate(ItemType item);
        }
    
        @Override
        public String toString() {
            return getName();
        }
    }

    public void updateActions(Set<Action> actions, boolean add) {
        /*
         * Adds or removes actions from available actions
         */
        if (add) {
            this.availableActions.addAll(actions);
        } else {
            this.availableActions.removeAll(actions);
        }
    }

    public Set<Action> getActions(){
        /*
         * Method that returns all
         * the possible actions
         */
        return this.availableActions;
    }  

    public void resetActions(){
        /*
         * Resets the available actions
         */
        this.availableActions = null;
    }

    
}
