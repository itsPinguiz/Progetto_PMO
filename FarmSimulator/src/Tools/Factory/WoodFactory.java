package Tools.Factory;

import Tools.ConcreteTool.Interface.*;
import Tools.ConcreteTool.WoodTool.*;

public class WoodFactory implements ToolFactory {
    
    public Item createHoe(){
        return new WoodHoe();
    }
    public Item createScissors(){
        return new WoodScissors();
    }
    public Item createSickle(){
        return new WoodSickle();
    }
    public Item createWateringCan(){
        return new WoodWateringCan();
    }
}
