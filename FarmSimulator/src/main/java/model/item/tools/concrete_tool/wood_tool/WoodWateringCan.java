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
/**
 * This class represents the WoodWateringCan tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The WoodWateringCan tool has specific attributes such as type, material, status, price, and number.
 */
public class WoodWateringCan extends AbstractTool {
    
    /**
     * Constructs a new instance of an WoodWateringCan tool.
     * The attributes of the tool are set upon creation.
     */
    public WoodWateringCan(){
        
        super.type = ItemType.Tools.WATERINGCAN;
        super.material = Material.WOOD;
        super.status = 100;
        super.price = Constants.WOOD_WATERINGCAN_PRICE;
        super.number = 1;
    }
}
