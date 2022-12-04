package Tools.Factory;

import Tools.ConcreteTool.Interface.Tool;

/**
 * Abstract factory method
 */
public interface ToolFactory {

    Tool createHoe();
    Tool createScissors();
    Tool createSickle();
    Tool createWateringCan();
}
