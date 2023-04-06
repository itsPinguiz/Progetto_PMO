/********************
 * IMPORT AND PACKAGE
 *******************/

package model.place.barn.market;

import java.io.Serializable;

import model.item.Item;

/******************
 * MARKET INTERFACE
 *****************/
public interface MarketInt extends Serializable{
    public void updateItemShop();
    public Item buyItem(Item boughtItem, int balance) throws Exception;
}
