/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.concrete_tool.wood_tool;

import model.Constants;
import model.item.ItemType;
import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool;

/******************
 * WOODSICKLE CLASS
 *****************/
public class WoodSickle extends AbstractTool {
    
    public WoodSickle(){
        
        super.type = ItemType.Tools.SICKLE;
        ((ItemType.Tools)(super.type)).setMaterial(Material.WOOD);
        super.status = 100;
        super.price = Constants.WOOD_SICKLE_PRICE;
        super.number = 1;
    }
}
