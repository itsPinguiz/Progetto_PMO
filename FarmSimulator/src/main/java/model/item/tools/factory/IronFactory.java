/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.factory;

import model.item.tools.AbstractTool;
import model.item.tools.concrete_tool.iron_tool.*;

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
    public AbstractTool createFertilizer(){
        return new IronFertilizer();
    }
}
