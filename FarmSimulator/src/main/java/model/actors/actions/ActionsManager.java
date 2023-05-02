package model.actors.actions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.item.ItemType;
import model.place.land.LandAbstract;

/**
 * Class that manages all the actions that can be performed by the player
 */
public abstract class ActionsManager implements Actions {
    /**
     * Attributes
     */
    protected Set<Action> availableActions;

    /**
     * Constructor
     */
    protected ActionsManager() {
        this.availableActions = new HashSet<>();
    }

    /**
     * Enum that contains all the possible actions
     */
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

        /**
         * Attributes
         */
        private final String name;
        private final ItemTypeValidator itemValidator;
        private final LandAbstractValidator landValidator;
        private final boolean optional;

        /**
         * Constructor witj optional parameter
         * @param name name of the action
         * @param itemValidator validator for the item
         * @param landValidator validator for the land
         * @param optional true if having an item is optional for the action
         */
        Action(String name, ItemTypeValidator itemValidator, LandAbstractValidator landValidator, boolean optional) {
            this.name = name;
            this.itemValidator = itemValidator;
            this.landValidator = landValidator;
            this.optional = optional;
        }

        /**
         * Constructor
         * @param name name of the action
         * @param itemValidator validator for the item
         * @param landValidator validator for the land
         */
        Action(String name, ItemTypeValidator itemValidator, LandAbstractValidator landValidator) {
            this(name, itemValidator, landValidator, false);
        }

        /**
         * Method that returns the name of the action
         * @return name of the action
         */
        public String getName() {
            return name;
        }

        /**
         * Method that returns true if the item is valid for the action
         * @param item item to check
         * @param land land to check
         * @return true if the item is valid for the action
         */
        public boolean isItemValid(ItemType item, LandAbstract land) {
            return itemValidator.validateItemType(item) || landValidator.validateLandAbstract(land);
        }

        /**
         * Method that returns true if having an item is optional for the action
         * @return true if having an item is optional for the action
         */
        public boolean isOptional() {
            return optional;
        }

        /**
         * Method that returns the name of the action
         * @return name of the action
         */
        @Override
        public String toString() {
            /*
             * Method that returns the name of the action
             */
            return getName();
        }
    }

    /**
     * Method that updates the available actions
     * @param actions actions to add or remove
     * @param add true if the actions have to be added, false if they have to be removed
     */
    public void updateActions(Set<Action> actions, boolean add) {
        /*
         * Method that updates the available actions
         */
        Set<Action> toUpdate = add ? actions : Collections.emptySet();
        Set<Action> toRemove = add ? Collections.emptySet() : actions;
    
        this.availableActions.addAll(toUpdate);
        this.availableActions.removeAll(toRemove);
    }

    /**
     * Functional Interface that validates an item
     */
    @FunctionalInterface
    public interface ItemTypeValidator {
        /**
         * Method that returns true if the item is valid for the action
         * @param item item to check
         * @return true if the item is valid for the action
         */
        boolean validateItemType(ItemType item);
    }

    /**
     * Functional Interface that validates a land
     */
    @FunctionalInterface
    public interface LandAbstractValidator {
        /**
         * Method that returns true if the land is valid for the action
         * @param land land to check
         * @return true if the land is valid for the action
         */
        boolean validateLandAbstract(LandAbstract land);
    }
    
    /**
     * Interface that validates an item and a land
     * used to validate actions that require both an item and a land
     */
    public interface DualItemValidator extends ItemTypeValidator, LandAbstractValidator {
    }

    /**
     * Method that returns the available actions
     * @return available actions
     */
    public Set<Action> getAvailableActions() {
        return this.availableActions;
    }

    /**
     * Method that resets the available actions
     */
    public void resetActions() {
        this.availableActions.clear();
    }
}    