/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.concrete_tool.iron_tool;

import model.Constants;
import model.item.ItemType;
import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool;

/******************
 * IRONSICKLE CLASS
 *****************/
public class IronSickle extends AbstractTool{
   
    public IronSickle(){
        
        super.type = ItemType.Tools.SICKLE;
        ((ItemType.Tools)(super.type)).setMaterial(Material.IRON);
        super.status = 100;
        super.price = Constants.IRON_SICKLE_PRICE.getValue();
        super.number = 1;
    }
}
