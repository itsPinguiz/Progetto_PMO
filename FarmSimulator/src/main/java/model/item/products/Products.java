/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.products;

import model.Constants;
import model.exceptions.CustomExceptions.*;
import model.item.Item;
import model.item.ItemType;

/****************
 * PRODUCTS CLASS
 ***************/
public class Products extends Item{

	public Products(ItemType.productsType currentType) throws NoProductFoundException {
		super.status = 0;
		super.maxNumber = 64;
		super.type = currentType;
	
		switch (currentType) {
			case MEAT:
				super.price = Constants.MEAT_PRICE;
				break;
			
			case MILK:
				super.price = Constants.MILK_PRICE;
				break;
				
			case EGGS:
				super.price = Constants.EGGS_PRICE;
				break;
				
			case WOOL:
				super.price = Constants.WOOL_PRICE;
				break;
				
			default:
				throw new NoProductFoundException();	
			}
		}
}
