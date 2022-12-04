package Tools.ConcreteTool.WoodTool;

import Tools.ConcreteTool.Interface.Tool;

public class WoodWateringCan implements Tool {
    private String toolType;
    private int status;
    private int price;
    
    public WoodWateringCan(){
        this.toolType = "WoodWateringCan";
        this.status = 100;
        this.price = 5;
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
