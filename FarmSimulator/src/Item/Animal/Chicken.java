/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Animal;

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
       super.typeProduct.add(ItemType.Products.MEAT);
       super.typeProduct.add(ItemType.Products.EGGS);
       super.price = 20;
	   super.status = 100;
	   super.number = 1;
	   super.maxNumber = 30;
    }

    public void updateHunger(){
        
    }
    
}
