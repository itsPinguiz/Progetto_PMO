/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.ConcreteTool.IronTool;

import Item.ItemType;
import Item.ItemType.Tools.Material;
import Item.Tools.Interface.AbstractTool;

/***********************
 * IRONWATERINGCAN CLASS 
 **********************/
public class IronWateringCan extends AbstractTool {
	
    public IronWateringCan(){
        
        super.type = ItemType.Tools.WATERINGCAN;
        ((ItemType.Tools)(super.type)).setMaterial(Material.IRON);
        super.status = 100;
        super.price = 10;
        super.number = 1;
    }
}
