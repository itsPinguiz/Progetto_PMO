/********************
 * IMPORT AND PACKAGE
 *******************/

package model.place.barn.market;

import java.io.Serializable;

import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoEnoughMoneyException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.item.Item;

/******************
 * MARKET INTERFACE
 *****************/
public interface MarketInt extends Serializable{
    /**
     * Updates the item shop.
     *
     * @throws InventoryIsFullException     if the inventory is full
     * @throws NoItemFoundException         if no item is found
     * @throws CloneNotSupportedException if cloning is not supported
     */
    public void updateItemShop() throws InventoryIsFullException, NoItemFoundException, CloneNotSupportedException;

    /**
     * Buys an item from the shop.
     *
     * @param boughtItem the item to buy
     * @param balance    the balance available for the purchase
     * @return the bought item
     * @throws NoEnoughMoneyException      if there is not enough money for the purchase
     * @throws NoItemFoundException        if no item is found
     * @throws CloneNotSupportedException if cloning is not supported
     */
    public Item buyItem(Item boughtItem, int balance) throws NoEnoughMoneyException, NoItemFoundException, CloneNotSupportedException;

}
