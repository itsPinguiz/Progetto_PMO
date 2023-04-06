/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.factory;

import java.io.Serializable;

import model.item.tools.AbstractTool;

/*************************
 * ABSTRACT FACTORY METHOD
 ************************/

public interface ToolFactory extends Serializable {
    AbstractTool createHoe();
    AbstractTool createScissors();
    AbstractTool createSickle();
    AbstractTool createWateringCan();
}
