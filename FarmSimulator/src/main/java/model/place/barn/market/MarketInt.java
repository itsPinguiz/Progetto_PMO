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
    public void updateItemShop() throws InventoryIsFullException, NoItemFoundException;
    public Item buyItem(Item boughtItem, int balance) throws NoEnoughMoneyException, NoItemFoundException, CloneNotSupportedException;

}
