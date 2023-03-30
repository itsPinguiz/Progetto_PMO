/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.Factory;

import java.io.Serializable;

import Item.Tools.Interface.AbstractTool;

/*************************
 * ABSTRACT FACTORY METHOD
 ************************/

public interface ToolFactory extends Serializable {
    AbstractTool createHoe();
    AbstractTool createScissors();
    AbstractTool createSickle();
    AbstractTool createWateringCan();
}
