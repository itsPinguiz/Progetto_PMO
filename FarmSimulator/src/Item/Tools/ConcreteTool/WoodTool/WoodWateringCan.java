package Item.Tools.ConcreteTool.WoodTool;

import Item.ItemType;
import Item.ItemType.Tools.Material;
import Item.Tools.Interface.AbstractTool;

public class WoodWateringCan extends AbstractTool {
    
    public WoodWateringCan(){
        
        super.type = ItemType.Tools.WATERINGCAN;
        ((ItemType.Tools)(super.type)).setMaterial(Material.WOOD);
        super.status = 100;
        super.price = 5;
        super.number = 1;
    }
}
