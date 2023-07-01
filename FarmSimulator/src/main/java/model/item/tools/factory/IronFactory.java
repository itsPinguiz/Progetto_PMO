/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.factory;

import model.item.tools.AbstractTool;
import model.item.tools.concrete_tool.iron_tool.*;

/*******************
 * IRONFACTORY CLASS
 ******************/
/**
 * This class represents the IronFactory, a concrete implementation of the ToolFactory interface.
 * It is used to create instances of different types of iron tools: Hoe, Scissors, Sickle, WateringCan, and Fertilizer.
 */
public class IronFactory implements ToolFactory {
    
    /**
     * Constructs a new IronHoe.
     * @return a new instance of IronHoe.
     */
    public AbstractTool createHoe(){
        return new IronHoe();
    }

    /**
     * Constructs a new IronScissors.
     * @return a new instance of IronScissors.
     */
    public AbstractTool createScissors(){
        return new IronScissors();
    }
    
    /**
     * Constructs a new IronSickle.
     * @return a new instance of IronSickle.
     */
    public AbstractTool createSickle(){
        return new IronSickle();
    }

    /**
     * Constructs a new IronWateringCan.
     * @return a new instance of IronWateringCan.
     */
    public AbstractTool createWateringCan(){
        return new IronWateringCan();
    }

    /**
     * Constructs a new IronFertilizer.
     * @return a new instance of IronFertilizer.
     */
    public AbstractTool createFertilizer(){
        return new IronFertilizer();
    }
}
