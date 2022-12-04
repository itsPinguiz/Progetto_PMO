package Tools.ConcreteTool.IronTool;

import Tools.ConcreteTool.Interface.Tool;

public class IronScissors implements Tool {
    
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
    public void deleteItem(){
        this.toolType = "Empty";
        this.status = 0;
        this.price = 0;
    }
}
