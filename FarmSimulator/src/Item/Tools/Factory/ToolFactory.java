package Item.Tools.Factory;

import Item.Interface.Item;

/**
 * Abstract factory method
 */
public interface ToolFactory {
    Item createHoe();
    Item createScissors();
    Item createSickle();
    Item createWateringCan();
}
