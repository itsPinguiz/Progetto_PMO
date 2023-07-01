/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools.factory;

import model.item.tools.AbstractTool;
import model.item.tools.concrete_tool.wood_tool.*;

/*******************
 * WOODFACTORY CLASS
 ******************/
/**
 * The WoodFactory class implements the ToolFactory interface and provides a concrete implementation for creating wood tool items.
 * This class is part of the Abstract Factory pattern, and it provides a way to encapsulate a group of individual factories that have a common theme, in this case, wood tools.
 */
public class WoodFactory implements ToolFactory {
    
    /**
     * Constructs a new WoodHoe.
     * @return a new instance of AbstractTool which is a WoodHoe.
     */
    public AbstractTool createHoe(){
        return new WoodHoe();
    }

    /**
     * Constructs a new WoodScissors.
     * @return a new instance of AbstractTool which is a WoodScissors.
     */
    public AbstractTool createScissors(){
        return new WoodScissors();
    }

    /**
     * Constructs a new WoodSickle.
     * @return a new instance of AbstractTool which is a WoodSickle.
     */
    public AbstractTool createSickle(){
        return new WoodSickle();
    }

    /**
     * Constructs a new WoodWateringCan.
     * @return a new instance of AbstractTool which is a WoodWateringCan.
     */
    public AbstractTool createWateringCan(){
        return new WoodWateringCan();
    }

    /**
     * Constructs a new WoodFertilizer.
     * @return a new instance of AbstractTool which is a WoodFertilizer.
     */
    public AbstractTool createFertilizer(){
        return new WoodFertilizer();
    }
}
