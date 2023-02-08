package Item.Interface;

import Item.ItemType;

public abstract class Item {
    protected ItemType type;
    protected int status;
    protected int price;
    protected int number;

    public ItemType getType(){
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
