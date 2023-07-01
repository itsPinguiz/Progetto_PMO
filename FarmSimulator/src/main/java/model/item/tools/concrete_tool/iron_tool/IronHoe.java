/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.concrete_tool.iron_tool;

import model.Constants;
import model.item.ItemType;
import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool;

/***************
 * IRONHOE CLASS
 **************/
/**
 * This class represents the IronHoe tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The IronHoe tool has specific attributes such as type, material, status, price, and number.
 */
public class IronHoe extends AbstractTool {
    /**
     * Constructs a new instance of an IronHoe tool.
     * The attributes of the tool are set upon creation.
     */
    public IronHoe(){
        super.type = ItemType.Tools.HOE;
        super.material = Material.IRON;
        super.status = 100;
        super.price = Constants.IRON_HOE_PRICE;
        super.number = 1;
    }
    
}
