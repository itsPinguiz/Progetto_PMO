/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.concrete_tool.wood_tool;

import model.Constants;
import model.item.ItemType;
import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool;

/***********************
 * WOODWATERINGCAN CLASS
 **********************/
public class WoodWateringCan extends AbstractTool {
    
    public WoodWateringCan(){
        
        super.type = ItemType.Tools.WATERINGCAN;
        ((ItemType.Tools)(super.type)).setMaterial(Material.WOOD);
        super.status = 100;
        super.price = Constants.WOOD_WATERINGCAN_PRICE.getValue();
        super.number = 1;
    }
}
