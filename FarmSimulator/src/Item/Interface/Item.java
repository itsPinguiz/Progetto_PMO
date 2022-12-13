package Item.Interface;

public abstract class Item {
    protected String type;
    protected int status;
    protected int price;
    protected int number;

    public String getType(){
        return this.type;
    }
    public int getStatus(){
        return this.status;
    }
    public int getPrice(){
        return this.price;
    }
    public int getNumber(){
        return this.number;
    }
}
