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
/**
 * This class represents the WoodSickle tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The WoodSickle tool has specific attributes such as type, material, status, price, and number.
 */
public class WoodSickle extends AbstractTool {
    
    /**
     * Constructs a new instance of an WoodSickle tool.
     * The attributes of the tool are set upon creation.
     */
    public WoodSickle(){
        
        super.type = ItemType.Tools.SICKLE;
        super.material = Material.WOOD;
        super.status = 100;
        super.price = Constants.WOOD_SICKLE_PRICE;
        super.number = 1;
    }
}
