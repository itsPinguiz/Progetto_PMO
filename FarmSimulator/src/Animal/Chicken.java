/********************
 * IMPORT AND PACKAGE
 *******************/

package Animal;

import Item.ItemType;

/***************
 * CHICKEN CLASS
 **************/
public class Chicken extends AnimalAbstract{
    //add age and modify hunger and life chaining during time get.Day-age of animal
    //constructor
	
    Chicken(){
       super();
       super.type = ItemType.Animals.CHICKEN;
       super.hunger = 0;
       super.nProduct = 0;
       super.typeProduct.add(ItemType.Products.MEAT);
       super.typeProduct.add(ItemType.Products.EGGS);
    }
    
}
