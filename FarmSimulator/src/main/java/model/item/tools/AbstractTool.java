/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools;

import model.item.Item;
import model.item.ItemType.Tools.Material;

/**
 *  This abstract class AbstractTool extends Item and implements ToolInterface.
 *  It represents a general tool in the game and provides fields and methods related to a tool,
 *  such as tool's material and status.
 */
public abstract class AbstractTool extends Item implements ToolInterface {
    //fields
    private ToolStatus toolStatus;
    protected Material material;

    /**
     * Enum for tool status, can be BROKEN, NORMAL, GOOD or EXCELLENT.
     */
    public enum ToolStatus{
        BROKEN("Broken"), 
        NORMAL("Normal"),
        GOOD("Good"), 
        EXCELLENT("Excellent");

        private String name;

        /**
         * Constructs a new ToolStatus with the specified name.
         * 
         * @param name the name of the tool status
         */
        ToolStatus(String name){
            this.name = name;
        }

        /**
         * Returns the name of the tool status.
         * 
         * @return the name of the tool status
         */
        public String getName(){
            return this.name;
        }

        /**
         * Returns a string representation of the tool status.
         * 
         * @return a string representation of the tool status
         */
        @Override
        public String toString() {
            return getName();
        }
    }

    /**
     * Constructs a new AbstractTool with default values.
     * The default tool status is EXCELLENT, and the maximum number is set to 1.
     */
    protected AbstractTool(){
        super();
        this.toolStatus = ToolStatus.EXCELLENT;
        super.maxNumber = 1;
    }

    /**
     * Checks the status of the tool and updates the tool status accordingly.
     */
    private void checkToolStatus(){
        //method to check the status of the tool
        if(super.status <= 0){
            this.toolStatus = ToolStatus.BROKEN;
        }
        else if (super.status <= 25){
            this.toolStatus = ToolStatus.NORMAL;
        }
        else if (super.status <= 50){
            this.toolStatus = ToolStatus.GOOD;
        }
        else if (super.status <= 100){
            this.toolStatus = ToolStatus.EXCELLENT;
        }
    }

    /**
     * Decreases the tool's status by 1 and updates the tool status.
     * 
     * @return the updated tool status
     */
    public ToolStatus useTool() {
        //method to use the tool
    	super.status--;
        checkToolStatus();
        return(this.toolStatus);
    }

    /**
     * Returns the status of the tool.
     * 
     * @return the status of the tool
     */
    public ToolStatus getToolStatus(){
        //method to get the status of the tool
        return this.toolStatus;
    }

    /**
     * Returns the material of the tool.
     * 
     * @return the material of the tool
     */
    public Material getMaterial(){
        //method to get the material of the tool
        return this.material;
    }
}
