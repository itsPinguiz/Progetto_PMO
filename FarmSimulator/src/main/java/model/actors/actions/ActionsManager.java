package model.actors.actions;

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
        PLANT("Plant", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item instanceof ItemType.Plants;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        WATER("Water", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item == ItemType.Tools.WATERINGCAN;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        FERTILIZE("Fertilize", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item == ItemType.Tools.FERTILIZER;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        HARVEST("Harvest", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item == ItemType.Tools.SICKLE || item == null;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }, true),
        PLOW("Plow", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item == ItemType.Tools.HOE;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        PLANT_ALL("Plant All", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item instanceof ItemType.Plants;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        WATER_ALL("Water All", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item == ItemType.Tools.WATERINGCAN;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        FERTILIZE_ALL("Fertilize All", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item == ItemType.Tools.FERTILIZER;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        HARVEST_ALL("Harvest All", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item == ItemType.Tools.SICKLE || item == null;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }, true),
        PLOW_ALL("Plow All", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item == ItemType.Tools.HOE;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        ADD_ANIMAL("Add Animal", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item instanceof ItemType.Animals;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        REMOVE_ANIMAL("Remove Animal", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item instanceof ItemType.Animals;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        FEED_ANIMAL("Feed Animal", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item instanceof ItemType.Plants;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        GIVE_WATER("Give Water", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return true;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        GET_RESOURCES("Get Resources", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item == ItemType.Tools.SCISSORS || item == null;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        FEED_ALL_ANIMALS("Feed All Animals", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item instanceof ItemType.Plants;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        GIVE_WATER_ALL("Give Water All", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return true;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        GET_ALL_RESOURCES("Get All Resources", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return true;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        MOVE_ITEM("Move Item", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item instanceof ItemType;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return false;
            }
        }),
        BUY_ITEM("Buy Item", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item instanceof ItemType;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return land instanceof LandAbstract;
            }
        }),
        SELL_ITEM("Sell Item", new DualItemValidator() {
            @Override
            public boolean validateItemType(ItemType item) {
                return item instanceof ItemType;
            }
    
            @Override
            public boolean validateLandAbstract(LandAbstract land) {
                return land instanceof LandAbstract;
            }
        });

        private final String name;
        private final DualItemValidator itemValidator;
        private final boolean optional;

        Action(String name, DualItemValidator itemValidator, boolean optional) {
            this.name = name;
            this.itemValidator = itemValidator;
            this.optional = optional;
        }

        Action(String name, DualItemValidator itemValidator) {
            this(name, itemValidator, false);
        }

        public String getName() {
            return name;
        }

        public boolean isItemValid(ItemType item, LandAbstract land) {
            return itemValidator.validateItemType(item) || itemValidator.validateLandAbstract(land);
        }

        public boolean isOptional() {
            return optional;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public void updateActions(Set<Action> actions, boolean add) {
        // Adds or removes actions from available actions
        if (add) {
            this.availableActions.addAll(actions);
        } else {
            this.availableActions.removeAll(actions);
        }
    }

    public Set<Action> getActions() {
        // Method that returns all the possible actions
        return this.availableActions;
    }

    public void resetActions() {
        // Resets the available actions
        this.availableActions.clear();
    }

    public interface ItemTypeValidator {
        boolean validateItemType(ItemType item);
    }

    public interface LandAbstractValidator {
        boolean validateLandAbstract(LandAbstract land);
    }
    
    public interface DualItemValidator extends ItemTypeValidator, LandAbstractValidator {
    }
}    