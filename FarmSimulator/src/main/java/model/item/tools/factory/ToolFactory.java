/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.factory;

import java.io.Serializable;

import model.item.tools.AbstractTool;

/*************************
 * ABSTRACT FACTORY METHOD
 ************************/

 /**
 * ToolFactory is an interface representing the Abstract Factory pattern for creating different types of tool items.
 * This pattern allows for the creation of different tools without specifying the exact classes of the objects that will be created.
 */
public interface ToolFactory extends Serializable {
    /**
     * Constructs a new Hoe.
     * @return a new instance of AbstractTool which is a Hoe.
     */
    AbstractTool createHoe();
    /**
     * Constructs a new Scissors.
     * @return a new instance of AbstractTool which is a Scissors.
     */
    AbstractTool createScissors();
    
    /**
     * Constructs a new Sickle.
     * @return a new instance of AbstractTool which is a Sickle.
     */
    AbstractTool createSickle();

    /**
     * Constructs a new WateringCan.
     * @return a new instance of AbstractTool which is a WateringCan.
     */
    AbstractTool createWateringCan();

    /**
     * Constructs a new WateringCan.
     * @return a new instance of AbstractTool which is a WateringCan.
     */
    AbstractTool createFertilizer();
}
