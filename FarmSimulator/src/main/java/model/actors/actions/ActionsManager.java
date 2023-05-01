package model.actors.actions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.item.ItemType;
import model.place.land.LandAbstract;

public abstract class ActionsManager implements Actions {
    // attributes
    protected Set<Action> availableActions;

    // constructor
    protected ActionsManager() {
        this.availableActions = new HashSet<>();
    }

    public enum Action {
        // All possible actions
        PLANT("Plant", item -> item instanceof ItemType.Plants,
                            land -> false),
        WATER("Water", item -> item == ItemType.Tools.WATERINGCAN,
                            land -> false),
        FERTILIZE("Fertilize", item -> item == ItemType.Tools.FERTILIZER,
                            land -> false),
        HARVEST("Harvest", item -> item == ItemType.Tools.SICKLE || item == null,
                                land -> false, true),
        PLOW("Plow", item -> item == ItemType.Tools.HOE,
                          land -> false),
        PLANT_ALL("Plant All", item -> item instanceof ItemType.Plants,
                                    land -> false),
        WATER_ALL("Water All", item -> item == ItemType.Tools.WATERINGCAN,
                                    land -> false),
        FERTILIZE_ALL("Fertilize All", item -> item == ItemType.Tools.FERTILIZER,
                                            land -> false),
        HARVEST_ALL("Harvest All", item -> item == ItemType.Tools.SICKLE || item == null,
                                        land -> false, true),
        PLOW_ALL("Plow All", item -> item == ItemType.Tools.HOE,
                                  land -> false),
        ADD_ANIMAL("Add Animal", item -> item instanceof ItemType.Animals,
                                      land -> false),
        REMOVE_ANIMAL("Remove Animal", item -> true,
                                            land -> false),
        FEED_ANIMAL("Feed Animal", item -> item instanceof ItemType.Plants,
                                        land -> false),
        GIVE_WATER("Give Water", item -> true,
                                      land -> false),
        GET_RESOURCES("Get Resources", item -> item == ItemType.Tools.SCISSORS || item == null,
                                            land -> false),
        FEED_ALL_ANIMALS("Feed All Animals", item -> item instanceof ItemType.Plants,
                                                  land -> false),
        GIVE_WATER_ALL("Give Water All", item -> true, 
                                              land -> false),
        GET_ALL_RESOURCES("Get All Resources", item -> true, 
                                                    land -> false),
        MOVE_ITEM("Move Item", item -> item instanceof ItemType,
                                            land -> false),
        BUY_ITEM("Buy Item", item -> item instanceof ItemType, 
                                  land -> land instanceof LandAbstract),
        SELL_ITEM("Sell Item", item -> item instanceof ItemType,
                                    land -> land instanceof LandAbstract);

        // attributes
        private final String name;
        private final ItemTypeValidator itemValidator;
        private final LandAbstractValidator landValidator;
        private final boolean optional;

        //constructor
        Action(String name, ItemTypeValidator itemValidator, LandAbstractValidator landValidator, boolean optional) {
            this.name = name;
            this.itemValidator = itemValidator;
            this.landValidator = landValidator;
            this.optional = optional;
        }

        Action(String name, ItemTypeValidator itemValidator, LandAbstractValidator landValidator) {
            this(name, itemValidator, landValidator, false);
        }

        public String getName() {
            /*
             * Method that returns the name of the action 
             */
            return name;
        }

        public boolean isItemValid(ItemType item, LandAbstract land) {
            /*
             * Method that returns true if the item is valid for the action 
             */
            return itemValidator.validateItemType(item) || landValidator.validateLandAbstract(land);
        }

        public boolean isOptional() {
            /*
             * Method that returns true if having an item is optional for the action
             */
            return optional;
        }

        @Override
        public String toString() {
            /*
             * Method that returns the name of the action
             */
            return getName();
        }
    }

    public void updateActions(Set<Action> actions, boolean add) {
        /*
         * Method that updates the available actions
         */
        Set<Action> toUpdate = add ? actions : Collections.emptySet();
        Set<Action> toRemove = add ? Collections.emptySet() : actions;
    
        this.availableActions.addAll(toUpdate);
        this.availableActions.removeAll(toRemove);
    }

    public Set<Action> getAvailableActions() {
        /*
         * Method that returns the available actions
         */
        return this.availableActions;
    }

    public void resetActions() {
        /*
         * Method that resets the available actions
         */
        this.availableActions.clear();
    }

    public interface ItemTypeValidator {
        /*
         * Method that returns true if the item is valid for the action
         */
        boolean validateItemType(ItemType item);
    }

    public interface LandAbstractValidator {
        /*
         * Method that returns true if the land is valid for the action
         */
        boolean validateLandAbstract(LandAbstract land);
    }
    
    public interface DualItemValidator extends ItemTypeValidator, LandAbstractValidator {
        /*
         * Method that returns true if the item is valid for the action
         */
    }
}    