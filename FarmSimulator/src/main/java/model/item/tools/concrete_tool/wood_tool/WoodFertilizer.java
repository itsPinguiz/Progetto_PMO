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
 public class WoodFertilizer extends AbstractTool {
     
     public WoodFertilizer(){
         super.type = ItemType.Tools.FERTILIZER;
         super.material = Material.WOOD;
         super.status = 100;
         super.price = Constants.WOOD_FERTILIZER_PRICE;
         super.number = 1;
     }
     
 }