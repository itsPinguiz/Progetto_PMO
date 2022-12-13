package Item.Tools.Factory;

import Item.Tools.Interface.AbstractTool;

/**
 * Abstract factory method
 */

public interface ToolFactory {
    AbstractTool createHoe();
    AbstractTool createScissors();
    AbstractTool createSickle();
    AbstractTool createWateringCan();
}
