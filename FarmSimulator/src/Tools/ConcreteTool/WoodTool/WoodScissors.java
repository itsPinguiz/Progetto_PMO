package Tools.ConcreteTool.WoodTool;

import Tools.ConcreteTool.Interface.ScissorsInt;

public class WoodScissors implements ScissorsInt {
    
    private String toolType;
    private int status;
    private int price;
    
    public WoodScissors(){
        this.toolType = "WoodScissors";
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
