/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.concrete_tool.wood_tool;

import model.Constants;
import model.item.ItemType;
import model.item.ItemType.Tools.Material;
import model.item.tools.AbstractTool;

/********************
 * WOODSCISSORS CLASS
 *******************/
/**
 * This class represents the WoodScissors tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The WoodScissors tool has specific attributes such as type, material, status, price, and number.
 */
public class WoodScissors extends AbstractTool {
    
    /**
     * Constructs a new instance of an WoodScissors tool.
     * The attributes of the tool are set upon creation.
     */
    public WoodScissors(){
        
        super.type = ItemType.Tools.SCISSORS;
        super.material = Material.WOOD;
        super.status = 100;
        super.price = Constants.WOOD_SCISSORS_PRICE;
        super.number = 1;
    }
}
