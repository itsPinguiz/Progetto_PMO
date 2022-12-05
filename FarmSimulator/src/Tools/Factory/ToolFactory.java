package Tools.Factory;

import Tools.ConcreteTool.Interface.Item;

/**
 * Abstract factory method
 */
public interface ToolFactory {
    Item createHoe();
    Item createScissors();
    Item createSickle();
    Item createWateringCan();
}
