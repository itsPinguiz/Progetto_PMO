package Item.Tools.Factory;

import Item.Tools.ConcreteTool.WoodTool.*;
import Item.Tools.Interface.AbstractTool;

public class WoodFactory implements ToolFactory {
    
    public AbstractTool createHoe(){
        return new WoodHoe();
    }
    public AbstractTool createScissors(){
        return new WoodScissors();
    }
    public AbstractTool createSickle(){
        return new WoodSickle();
    }
    public AbstractTool createWateringCan(){
        return new WoodWateringCan();
    }
}
