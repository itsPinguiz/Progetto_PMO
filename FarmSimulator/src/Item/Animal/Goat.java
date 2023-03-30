/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Animal;

import Item.ItemType;

/************
 * GOAT CLASS
 ***********/
public class Goat extends AnimalAbstract {

	Goat(){
	       super();
	       super.type = ItemType.Animals.GOAT;
	       super.typeProduct.add(ItemType.Products.MILK);
	       super.typeProduct.add(ItemType.Products.WOOL);
	       super.typeProduct.add(ItemType.Products.MEAT);
		   super.price = 100;
		   super.status = 100;
		   super.number = 1;
		   super.maxNumber = 7;    
	    }
    
}
