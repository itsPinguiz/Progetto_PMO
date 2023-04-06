/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.products;

import model.exceptions.CustomExceptions.*;
import model.item.Item;
import model.item.ItemType;

/****************
 * PRODUCTS CLASS
 ***************/
public class Products extends Item{

	public Products(ItemType.productsType currentType) {
		super.status = 0;
		super.maxNumber = 64;
		super.type = currentType;
		
		try {
		switch (currentType) {
			case MEAT:
				super.price = 20;
				break;
			
			case MILK:
				super.price = 4;
				break;
				
			case EGGS:
				super.price = 2;
				break;
				
			case WOOL:
				super.price = 15;
				break;
				
			default:
				throw new NoProductFoundException();	
			}
		}
		
		catch (NoProductFoundException e) {
				System.out.println(e);
			}
		}	
}
