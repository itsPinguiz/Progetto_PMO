package Tools.ConcreteTool.IronTool;

import Tools.ConcreteTool.Interface.Tool;

public class IronHoe implements Tool {
    
    private String toolType;
    private int status;
    private int price;
    
    public IronHoe(){
        this.toolType = "IronHoe";
        this.status = 100;
        this.price = 24;
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
