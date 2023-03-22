/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Products;

import Item.ItemType;
import Item.Interface.Item;
import Exceptions.CustomExceptions.*;

/****************
 * PRODUCTS CLASS
 ***************/
public class Products extends Item{

	public Products(ItemType.Products currentType) {
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
