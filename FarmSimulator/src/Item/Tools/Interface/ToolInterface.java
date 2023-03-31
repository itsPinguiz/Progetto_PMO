/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Tools.Interface;

import java.io.Serializable;

import Item.ItemType;

public interface ToolInterface extends Serializable {
    public double getStatus();
    public int getPrice();
    public int getNumber();
    public int getMaxNumber();
}
