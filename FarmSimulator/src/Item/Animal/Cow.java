/********************
 * IMPORT AND PACKAGE
 *******************/

package Animal;

import Item.ItemType;

/***********
 * COW CLASS
 **********/
public class Cow extends AnimalAbstract {

	Cow(){
	       super();
	       super.type = ItemType.Animals.COW;
	       super.hunger = 0;
	       super.nProduct = 0;
	       super.typeProduct.add(ItemType.Products.MEAT);
	       super.typeProduct.add(ItemType.Products.MILK);
	    }
    
}