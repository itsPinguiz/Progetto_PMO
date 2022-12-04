package Tools.Factory;

import Tools.ConcreteTool.Interface.*;
import Tools.ConcreteTool.IronTool.*;

public class IronFactory implements ToolFactory {
      
    public Tool createHoe(){
        return new IronHoe();
    }
    public Tool createScissors(){
        return new IronScissors();
    }
    public Tool createSickle(){
        return new IronSickle();
    }
    public Tool createWateringCan(){
        return new IronWateringCan();
    }
}
