/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.Factory;

import Item.Tools.ConcreteTool.IronTool.*;
import Item.Tools.Interface.AbstractTool;

/*******************
 * IRONFACTORY CLASS
 ******************/
public class IronFactory implements ToolFactory {
      
    public AbstractTool createHoe(){
        return new IronHoe();
    }
    public AbstractTool createScissors(){
        return new IronScissors();
    }
    public AbstractTool createSickle(){
        return new IronSickle();
    }
    public AbstractTool createWateringCan(){
        return new IronWateringCan();
    }
}
