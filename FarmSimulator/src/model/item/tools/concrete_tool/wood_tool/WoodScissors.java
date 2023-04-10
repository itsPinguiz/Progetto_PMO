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
public class WoodScissors extends AbstractTool {
    
    public WoodScissors(){
        
        super.type = ItemType.Tools.SCISSORS;
        ((ItemType.Tools)(super.type)).setMaterial(Material.WOOD);
        super.status = 100;
        super.price = Constants.WOOD_SCISSORS_PRICE.getValue();
        super.number = 1;
    }
}