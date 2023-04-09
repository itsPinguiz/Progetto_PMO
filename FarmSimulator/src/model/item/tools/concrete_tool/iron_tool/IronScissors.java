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
public class IronScissors extends AbstractTool {
    
    public IronScissors(){
        
        super.type = ItemType.Tools.SCISSORS;
        ((ItemType.Tools)(super.type)).setMaterial(Material.IRON);
        super.status = 100;
        super.price = Constants.IRON_SCISSORS_PRICE.getValue();
        super.number = 1;
    }

}
