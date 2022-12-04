package Tools.ConcreteTool.WoodTool;

import Tools.ConcreteTool.Interface.Tool;

public class WoodSickle implements Tool {
    
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
    public void deleteItem(){
        this.toolType = "Empty";
        this.status = 0;
        this.price = 0;
    }
}
