package Animal;

import Animal.enu.AnimalType;
import Calendar.Calendar;
import Item.ItemType;

public class Chicken extends AnimalAbstract{
    //add age and modifie hunger and life chaining during time get.Day-age of animal
    //contructor
    Chicken(){
       super.life = 100;
       super.hunger = 0;
       super.nProduct = 0;
       super.name = ItemType.CHICKEN;
       super.typeProduct.add(ItemType.MEAT);
       super.typeProduct.add(ItemType.EGGS);
    }
    
}
