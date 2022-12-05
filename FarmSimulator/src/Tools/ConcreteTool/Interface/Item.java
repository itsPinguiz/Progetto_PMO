package Tools.ConcreteTool.Interface;

public abstract class Item {
    protected String type;
    protected int status;
    protected int price;

    public String getType(){
        return this.type;
    }
    public int getStatus(){
        return this.status;
    }
    public int getPrice(){
        return this.price;
    }
}
