package Tools.ConcreteTool.IronTool;

import Tools.ConcreteTool.Interface.SickleInt;

public class IronSickle implements SickleInt{
   
    private String toolType;
    private int status;
    private int price;
    
    public IronSickle(){
        this.toolType = "IronSickle";
        this.status = 100;
        this.price = 30;
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
