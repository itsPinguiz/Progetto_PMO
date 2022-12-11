package Item.Tools.Factory;

import Item.Interface.*;
import Item.Tools.ConcreteTool.IronTool.*;

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
