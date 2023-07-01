package model.item;

public interface ItemInterface {
    /**
     * Returns the type of the item.
     *
     * @return the type of the item
     */
    public ItemType getType();
    
    /**
     * Returns the status of the item.
     *
     * @return the status of the item
     */
    public double getStatus();

    /**
     * Returns the price of the item.
     *
     * @return the price of the item
     */
    public int getPrice();

    /**
     * Returns the number of the item.
     *
     * @return the number of the item
     */
    public int getNumber();

    /**
     * Returns the max number of the item.
     *
     * @return the max number of the item
     */
    public int getMaxNumber();

    /**
     * Sets the number of the item.
     *
     * @param newNumber the new number of the item
     */
    public void setNumber(int newNumber);

    /**
     * Clones the item.
     *
     * @return a cloned object of the item
     * @throws CloneNotSupportedException if cloning is not supported
     */
    public Object clone() throws CloneNotSupportedException;
}
