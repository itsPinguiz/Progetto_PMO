/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Animal;

import java.io.Serializable;
import java.util.ArrayList;

/******************
 * ANIMAL INTERFACE
 *****************/
public interface AnimalInterface extends Serializable {
    public int getHunger();
    public ArrayList<Item.Products.Products> getProducts(); //void momentaneo
}