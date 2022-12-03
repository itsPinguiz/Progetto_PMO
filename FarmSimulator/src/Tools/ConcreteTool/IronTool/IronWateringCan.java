package Tools.ConcreteTool.IronTool;

import Tools.ConcreteTool.Interface.WateringCanInt;

public class IronWateringCan implements WateringCanInt {
    
    private String toolType;
    private int status;
    private int price;
    
    public IronWateringCan(){
        this.toolType = "IronWateringCan";
        this.status = 100;
        this.price = 10;
    }
    
    public String getType(){
        return this.toolType;
    }
    public int getStatus(){
        return this.status;
    }
    public int getPrice(){
        return this.price;
    }
}
