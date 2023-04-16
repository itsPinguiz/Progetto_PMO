/********************
 * IMPORT AND PACKAGE
 *******************/
package model.item.tools.concrete_tool.iron_tool;

import model.Constants;
import model.item.ItemType;
import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool;

/***********************
 * IRONWATERINGCAN CLASS 
 **********************/
public class IronWateringCan extends AbstractTool {
	
    public IronWateringCan(){
        
        super.type = ItemType.Tools.WATERINGCAN;
        super.material = Material.IRON;
        super.status = 100;
        super.price = Constants.IRON_WATERINGCAN_PRICE;
        super.number = 1;
    }
}
