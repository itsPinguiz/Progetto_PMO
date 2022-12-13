package Item.Tools.ConcreteTool.WoodTool;

import Item.Tools.Interface.AbstractTool;

public class WoodScissors extends AbstractTool {
    
    public WoodScissors(){
        super.type = "WoodScissors";
        super.status = 100;
        super.price = 10;
        super.number = 1;
    }
}
