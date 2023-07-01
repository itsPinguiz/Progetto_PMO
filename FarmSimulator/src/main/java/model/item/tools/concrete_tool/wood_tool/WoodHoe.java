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
/**
 * This class represents the WoodHoe tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The WoodHoe tool has specific attributes such as type, material, status, price, and number.
 */
public class WoodHoe extends AbstractTool{
    
    /**
     * Constructs a new instance of an WoodHoe tool.
     * The attributes of the tool are set upon creation.
     */
    public WoodHoe(){
        
        super.type = ItemType.Tools.HOE;
        super.material = Material.WOOD;
        super.status = 100;
        super.price = Constants.WOOD_HOE_PRICE;
        super.number = 1;
    }
}
