package Tools.ConcreteTool.IronTool;

import Tools.ConcreteTool.Interface.ScissorsInt;

public class IronScissors implements ScissorsInt {
    
    private String toolType;
    private int status;
    private int price;
    
    public IronScissors(){
        this.toolType = "IronScissors";
        this.status = 100;
        this.price = 20;
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
