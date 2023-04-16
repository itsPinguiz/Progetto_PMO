/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.concrete_tool.wood_tool;

import model.Constants;
import model.item.ItemType;
import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool;

/***************
 * WOODHOE CLASS
 **************/
public class WoodHoe extends AbstractTool{
    
    public WoodHoe(){
        
        super.type = ItemType.Tools.HOE;
        super.material = Material.WOOD;
        super.status = 100;
        super.price = Constants.WOOD_HOE_PRICE;
        super.number = 1;
    }
}
