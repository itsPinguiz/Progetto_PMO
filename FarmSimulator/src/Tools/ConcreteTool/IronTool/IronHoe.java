package Tools.ConcreteTool.IronTool;

import Tools.ConcreteTool.Interface.HoeInt;

public class IronHoe implements HoeInt {
    
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
