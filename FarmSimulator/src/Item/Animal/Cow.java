/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Animal;

import Item.ItemType;

/***********
 * COW CLASS
 **********/
public class Cow extends AnimalAbstract {

	Cow(){
	       super();
	       super.type = ItemType.Animals.COW;
	       super.typeProduct.add(ItemType.Products.MEAT);
	       super.typeProduct.add(ItemType.Products.MILK);
		   super.price = 200;
		   super.status = 100;
		   super.number = 1;
		   super.maxNumber = 5;
	    }
    
}
