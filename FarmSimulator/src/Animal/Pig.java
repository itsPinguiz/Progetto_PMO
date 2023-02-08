package Animal;

import Item.ItemType;

public class Pig extends AnimalAbstract {

	Pig(){
	       super();
	       super.type = ItemType.Animals.PIG;
	       super.hunger = 0;
	       super.nProduct = 0;
	       super.typeProduct.add(ItemType.Products.MEAT);
	    }
    
}
