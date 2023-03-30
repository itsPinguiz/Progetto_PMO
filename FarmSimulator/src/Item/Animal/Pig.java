/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Animal;

import Item.ItemType;

/***********
 * PIG CLASS
 **********/
public class Pig extends AnimalAbstract {

	Pig(){
	       super();
	       super.type = ItemType.Animals.PIG;
	       super.typeProduct.add(ItemType.Products.MEAT);
		   super.price = 60;
		   super.status = 100;
		   super.number = 1;
		   super.maxNumber = 10;
	    }
    
}
