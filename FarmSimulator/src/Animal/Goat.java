/********************
 * IMPORT AND PACKAGE
 *******************/

package Animal;

import Item.ItemType;

/************
 * GOAT CLASS
 ***********/
public class Goat extends AnimalAbstract {

	Goat(){
	       super();
	       super.type = ItemType.Animals.GOAT;
	       super.hunger = 0;
	       super.nProduct = 0;
	       super.typeProduct.add(ItemType.Products.MILK);
	       super.typeProduct.add(ItemType.Products.WOOL);
	       super.typeProduct.add(ItemType.Products.MEAT);    
	    }
    
}
