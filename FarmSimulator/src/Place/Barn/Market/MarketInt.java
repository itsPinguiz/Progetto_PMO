/********************
 * IMPORT AND PACKAGE
 *******************/

package Place.Barn.Market;

import java.io.Serializable;

import Item.Interface.Item;

/******************
 * MARKET INTERFACE
 *****************/
public interface MarketInt extends Serializable{
    public void updateItemShop();
    public Item buyItem(Item boughtItem, int balance) throws Exception;
}
