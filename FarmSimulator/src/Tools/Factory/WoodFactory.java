package Tools.Factory;

import Tools.ConcreteTool.Interface.*;
import Tools.ConcreteTool.WoodTool.*;

public class WoodFactory implements ToolFactory {
    
    public Tool createHoe(){
        return new WoodHoe();
    }
    public Tool createScissors(){
        return new WoodScissors();
    }
    public Tool createSickle(){
        return new WoodSickle();
    }
    public Tool createWateringCan(){
        return new WoodWateringCan();
    }
}
