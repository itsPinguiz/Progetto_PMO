package Item.Tools.ConcreteTool.WoodTool;

import Item.ItemType;
import Item.ItemType.Tools.Material;
import Item.Tools.Interface.AbstractTool;

public class WoodScissors extends AbstractTool {
    
    public WoodScissors(){
        
        super.type = ItemType.Tools.SCISSORS;
        ((ItemType.Tools)(super.type)).setMaterial(Material.WOOD);
        super.status = 100;
        super.price = 10;
        super.number = 1;
    }
}
