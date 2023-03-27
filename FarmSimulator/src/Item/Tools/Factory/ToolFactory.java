/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.Factory;

import Item.Tools.Interface.AbstractTool;

/*************************
 * ABSTRACT FACTORY METHOD
 ************************/

public interface ToolFactory {
    AbstractTool createHoe();
    AbstractTool createScissors();
    AbstractTool createSickle();
    AbstractTool createWateringCan();
}
