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
/**
 * This class represents the IronSickle tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The IronSickle tool has specific attributes such as type, material, status, price, and number.
 */
public class IronSickle extends AbstractTool{
    /**
     * Constructs a new instance of an IronSickle tool.
     * The attributes of the tool are set upon creation.
     */
    public IronSickle(){
        
        super.type = ItemType.Tools.SICKLE;
        super.material = Material.IRON;
        super.status = 100;
        super.price = Constants.IRON_SICKLE_PRICE;
        super.number = 1;
    }
}
