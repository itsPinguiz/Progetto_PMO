/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.tools;

import model.item.Item;
import model.item.ItemType.Tools.Material;

public abstract class AbstractTool extends Item implements ToolInterface {
    //fields
    private ToolStatus toolStatus;
    protected Material material;

    public enum ToolStatus{
        BROKEN("Broken"), 
        NORMAL("Normal"),
        GOOD("Good"), 
        EXCELLENT("Excellent");

        private String name;

        ToolStatus(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    //constructor
    protected AbstractTool(){
        super();
        this.toolStatus = ToolStatus.EXCELLENT;
        super.maxNumber = 1;
    }

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

    public ToolStatus useTool() {
        //method to use the tool
    	super.status--;
        checkToolStatus();
        return(this.toolStatus);
    }

    public ToolStatus getToolStatus(){
        //method to get the status of the tool
        return this.toolStatus;
    }

    public Material getMaterial(){
        //method to get the material of the tool
        return this.material;
    }
}
