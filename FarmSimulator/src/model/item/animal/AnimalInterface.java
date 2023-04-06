/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.animal;

import java.io.Serializable;
import java.util.ArrayList;

/******************
 * ANIMAL INTERFACE
 *****************/
public interface AnimalInterface extends Serializable {
    public int getHunger();
    public ArrayList<model.item.products.Products> getProducts(); //void momentaneo
}