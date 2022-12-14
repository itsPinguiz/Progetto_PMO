package Item.Tools.ConcreteTool.WoodTool;

import Item.Tools.Interface.AbstractTool;

public class WoodHoe extends AbstractTool{
    
    public WoodHoe(){
        super.type = "WoodHoe";
        super.status = 100;
        super.price = 12;
        super.number = 1;
    }
}
