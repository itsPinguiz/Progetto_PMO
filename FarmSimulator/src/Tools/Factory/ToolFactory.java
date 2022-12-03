package Tools.Factory;

import Tools.ConcreteTool.Interface.HoeInt;
import Tools.ConcreteTool.Interface.ScissorsInt;
import Tools.ConcreteTool.Interface.SickleInt;
import Tools.ConcreteTool.Interface.WateringCanInt;

/**
 * Abstract factory method
 */
public interface ToolFactory {

    HoeInt createHoe();
    ScissorsInt createScissors();
    SickleInt createSickle();
    WateringCanInt createWateringCan();
}
