package Item.Interface;

import Item.ItemType;

public interface ItemInterface {

    public ItemType getType();
    public double getStatus();
    public int getPrice();
    public int getNumber();
    public int getMaxNumber();
    public void setNumber(int newNumber);
    public Object clone() throws CloneNotSupportedException;
}
