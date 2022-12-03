package Tools.Factory;

import Tools.ConcreteTool.Interface.*;
import Tools.ConcreteTool.IronTool.*;

public class IronFactory implements ToolFactory {
      
    public HoeInt createHoe(){
        return new IronHoe();
    }
    public ScissorsInt createScissors(){
        return new IronScissors();
    }
    public SickleInt createSickle(){
        return new IronSickle();
    }
    public WateringCanInt createWateringCan(){
        return new IronWateringCan();
    }
}
