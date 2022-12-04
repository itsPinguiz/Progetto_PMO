package Tools.ConcreteTool.WoodTool;

import Tools.ConcreteTool.Interface.Tool;

public class WoodHoe implements Tool{
    
    private String toolType;
    private int status;
    private int price;
    
    public WoodHoe(){
        this.toolType = "WoodHoe";
        this.status = 100;
        this.price = 12;
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
