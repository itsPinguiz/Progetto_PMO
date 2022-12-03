package Tools.ConcreteTool.WoodTool;

import Tools.ConcreteTool.Interface.SickleInt;

public class WoodSickle implements SickleInt {
    
    private String toolType;
    private int status;
    private int price;
    
    public WoodSickle(){
        this.toolType = "WoodSickle";
        this.status = 100;
        this.price = 15;
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
