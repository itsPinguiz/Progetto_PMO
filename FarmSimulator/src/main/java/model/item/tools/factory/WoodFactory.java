/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.factory;

import model.item.tools.AbstractTool;
import model.item.tools.concrete_tool.wood_tool.*;

/*******************
 * WOODFACTORY CLASS
 ******************/
public class WoodFactory implements ToolFactory {
    
    public AbstractTool createHoe(){
        return new WoodHoe();
    }
    public AbstractTool createScissors(){
        return new WoodScissors();
    }
    public AbstractTool createSickle(){
        return new WoodSickle();
    }
    public AbstractTool createWateringCan(){
        return new WoodWateringCan();
    }
}
