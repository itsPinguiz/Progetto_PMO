package Tools.Factory;

import Tools.ConcreteTool.Interface.*;
import Tools.ConcreteTool.IronTool.*;

public class IronFactory implements ToolFactory {
      
    public Item createHoe(){
        return new IronHoe();
    }
    public Item createScissors(){
        return new IronScissors();
    }
    public Item createSickle(){
        return new IronSickle();
    }
    public Item createWateringCan(){
        return new IronWateringCan();
    }
}
