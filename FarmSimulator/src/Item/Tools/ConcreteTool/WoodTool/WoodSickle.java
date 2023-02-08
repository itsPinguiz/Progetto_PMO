package Item.Tools.ConcreteTool.WoodTool;

import Item.ItemType;
import Item.ItemType.Tools.Material;
import Item.Tools.Interface.AbstractTool;

public class WoodSickle extends AbstractTool {
    
    public WoodSickle(){
        
        super.type = ItemType.Tools.SICKLE;
        ((ItemType.Tools)(super.type)).setMaterial(Material.WOOD);
        super.status = 100;
        super.price = 15;
        super.number = 1;
    }
}
