package Item.Tools.ConcreteTool.IronTool;

import Item.ItemType;
import Item.ItemType.Tools.Material;
import Item.Tools.Interface.AbstractTool;

public class IronHoe extends AbstractTool {
    
    public IronHoe(){
    	
        super.type = ItemType.Tools.HOE;
        ((ItemType.Tools)(super.type)).setMaterial(Material.IRON);
        super.status = 100;
        super.price = 24;
        super.number = 1;
    }
    
}
