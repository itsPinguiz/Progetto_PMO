/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.ConcreteTool.WoodTool;

import Item.ItemType;
import Item.ItemType.Tools.Material;
import Item.Tools.Interface.AbstractTool;

/***************
 * WOODHOE CLASS
 **************/
public class WoodHoe extends AbstractTool{
    
    public WoodHoe(){
        
        super.type = ItemType.Tools.HOE;
        ((ItemType.Tools)(super.type)).setMaterial(Material.WOOD);
        super.status = 100;
        super.price = 12;
        super.number = 1;
    }
}
