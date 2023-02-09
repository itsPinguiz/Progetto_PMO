package Item.Interface;

import Item.ItemType;

public abstract class Item implements Cloneable{
	
    protected ItemType type;
    protected double status;
    protected int price;
    protected int number;

    public ItemType getType(){
        return this.type;
    }
    public double getStatus(){
        return this.status;
    }
    public int getPrice(){
        return this.price;
    }
    public int getNumber(){
        return this.number;
    }
    
    public void useItem() {
    	this.status--;
    }
    
    public void setNumber(int newNumber) {
    	this.number = newNumber;
    }
    
    public Object clone() throws CloneNotSupportedException{
    	return super.clone();
    }
    
}
