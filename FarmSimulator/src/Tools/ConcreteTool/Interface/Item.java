package Tools.ConcreteTool.Interface;

public abstract class Item {
    protected String toolType;
    protected int status;
    protected int price;

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
