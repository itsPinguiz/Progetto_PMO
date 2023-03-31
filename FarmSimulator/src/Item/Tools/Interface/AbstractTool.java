/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.Interface;

import Item.Interface.Item;

public abstract class AbstractTool extends Item implements ToolInterface {

    private ToolStatus toolStatus;

    protected AbstractTool(){
        super();
        this.toolStatus = ToolStatus.EXCELLENT;
    }

    public enum ToolStatus{
        BROKEN, 
        NORMAL, 
        GOOD, 
        EXCELLENT
    }

    public void useItem() {
    	super.status--;
        checkToolStatus();
    }

    private void checkToolStatus(){
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

    public ToolStatus getToolStatus(){
        return this.toolStatus;
    }

    public double getStatus(){
        return super.status;
    }

    public int getPrice(){
        return super.price;
    }

    public int getNumber(){
        return super.number;
    }

    public int getMaxNumber(){
        return super.maxNumber;
    }

    



}
