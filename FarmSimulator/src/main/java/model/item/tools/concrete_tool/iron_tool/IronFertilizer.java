/********************
 * IMPORT AND PACKAGE
 *******************/

 package model.item.tools.concrete_tool.iron_tool;

 import model.Constants;
 import model.item.ItemType;
 import model.item.ItemType.Tools.Material;
 import model.item.tools.AbstractTool;
 
 /***************
  * IRONHOE CLASS
  **************/
/**
 * This class represents the IronFertilizer tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The IronFertilizer tool has specific attributes such as type, material, status, price, and number.
 */
 public class IronFertilizer extends AbstractTool {
     
    /**
     * Constructs a new instance of an IronFertilizer tool.
     * The attributes of the tool are set upon creation.
     */
     public IronFertilizer(){
         super.type = ItemType.Tools.FERTILIZER;
         super.material = Material.IRON;
         super.status = 100;
         super.price = Constants.IRON_FERTILIZER_PRICE;
         super.number = 1;
     }
     
 }
