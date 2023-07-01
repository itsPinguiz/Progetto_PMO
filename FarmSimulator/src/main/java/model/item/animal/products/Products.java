/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.animal.products;

import model.Constants;
import model.exceptions.CustomExceptions.*;
import model.item.Item;
import model.item.ItemType;

/****************
 * PRODUCTS CLASS
 ***************/
/**
 * This class extends the Item class and represents different types of products.
 * The type of product, its maximum number, and its price are defined based on the given product type.
 */
public class Products extends Item{

	/**
     * Constructs a new Product with the given product type.
     *
     * @param currentType The type of the product.
     * @throws NoProductFoundException If the given product type is not recognized.
     */
	public Products(ItemType.productsType currentType) throws NoProductFoundException {
		super.status = 0;
		super.number = 0;
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
