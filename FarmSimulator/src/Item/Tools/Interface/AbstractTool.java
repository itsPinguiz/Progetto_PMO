/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.Interface;

import Item.Interface.Item;

public abstract class AbstractTool extends Item implements ToolInterface {
    //fields
    private ToolStatus toolStatus;

    public enum ToolStatus{
        BROKEN, 
        NORMAL, 
        GOOD, 
        EXCELLENT
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
}
