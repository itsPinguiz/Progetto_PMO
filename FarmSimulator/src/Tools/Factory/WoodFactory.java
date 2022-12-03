package Tools.Factory;

import Tools.ConcreteTool.Interface.*;
import Tools.ConcreteTool.WoodTool.*;

public class WoodFactory implements ToolFactory {
    
    public HoeInt createHoe(){
        return new WoodHoe();
    }
    public ScissorsInt createScissors(){
        return new WoodScissors();
    }
    public SickleInt createSickle(){
        return new WoodSickle();
    }
    public WateringCanInt createWateringCan(){
        return new WoodWateringCan();
    }
}
