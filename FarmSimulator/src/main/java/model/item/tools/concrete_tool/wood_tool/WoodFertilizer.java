/********************
 * IMPORT AND PACKAGE
 *******************/

 package model.item.tools.concrete_tool.wood_tool;

 import model.Constants;
 import model.item.ItemType;
 import model.item.ItemType.Tools.Material;
 import model.item.tools.AbstractTool;
 
/***************
* IRONHOE CLASS
**************/
/**
 * This class represents the WoodFertilizer tool, which is a specific type of Tool within the system.
 * It extends the AbstractTool class, inheriting its fields and methods.
 * The WoodFertilizer tool has specific attributes such as type, material, status, price, and number.
 */
 public class WoodFertilizer extends AbstractTool {
     
    /**
     * Constructs a new instance of an WoodFertilizer tool.
     * The attributes of the tool are set upon creation.
     */
     public WoodFertilizer(){
         super.type = ItemType.Tools.FERTILIZER;
         super.material = Material.WOOD;
         super.status = 100;
         super.price = Constants.WOOD_FERTILIZER_PRICE;
         super.number = 1;
     }
     
 }