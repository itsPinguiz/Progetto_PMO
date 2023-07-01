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
/**
 * This class represents the IronWateringCan tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The IronWateringCan tool has specific attributes such as type, material, status, price, and number.
 */
public class IronWateringCan extends AbstractTool {
	/**
     * Constructs a new instance of an IronWateringCan tool.
     * The attributes of the tool are set upon creation.
     */
    public IronWateringCan(){
        
        super.type = ItemType.Tools.WATERINGCAN;
        super.material = Material.IRON;
        super.status = 100;
        super.price = Constants.IRON_WATERINGCAN_PRICE;
        super.number = 1;
    }
}
