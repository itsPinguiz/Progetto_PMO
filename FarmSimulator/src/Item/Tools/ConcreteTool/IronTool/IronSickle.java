/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.ConcreteTool.IronTool;

import Item.ItemType;
import Item.ItemType.Tools.Material;
import Item.Tools.Interface.AbstractTool;

/******************
 * IRONSICKLE CLASS
 *****************/
public class IronSickle extends AbstractTool{
   
    public IronSickle(){
        
        super.type = ItemType.Tools.SICKLE;
        ((ItemType.Tools)(super.type)).setMaterial(Material.IRON);
        super.status = 100;
        super.price = 30;
        super.number = 1;
    }
}
