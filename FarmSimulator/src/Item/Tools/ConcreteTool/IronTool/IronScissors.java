/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.ConcreteTool.IronTool;

import Item.ItemType;
import Item.ItemType.Tools.Material;
import Item.Tools.Interface.AbstractTool;

/********************
 * IRONSCISSORS CLASS
 *******************/
public class IronScissors extends AbstractTool {
    
    public IronScissors(){
        
        super.type = ItemType.Tools.SCISSORS;
        ((ItemType.Tools)(super.type)).setMaterial(Material.IRON);
        super.status = 100;
        super.price = 20;
        super.number = 1;
    }

}
