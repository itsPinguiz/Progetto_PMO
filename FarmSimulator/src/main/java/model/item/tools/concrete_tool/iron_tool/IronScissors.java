/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.concrete_tool.iron_tool;

import model.Constants;
import model.item.ItemType;
import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool;

/********************
 * IRONSCISSORS CLASS
 *******************/
/**
 * This class represents the IronScissors tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The IronScissors tool has specific attributes such as type, material, status, price, and number.
 */
public class IronScissors extends AbstractTool {
    /**
     * Constructs a new instance of an IronScissors tool.
     * The attributes of the tool are set upon creation.
     */
    public IronScissors(){
        
        super.type = ItemType.Tools.SCISSORS;
        super.material = Material.IRON;
        super.status = 100;
        super.price = Constants.IRON_SCISSORS_PRICE;
        super.number = 1;
    }

}
