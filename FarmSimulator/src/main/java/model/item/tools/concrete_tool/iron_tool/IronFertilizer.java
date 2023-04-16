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
 public class IronFertilizer extends AbstractTool {
     
     public IronFertilizer(){
         super.type = ItemType.Tools.FERTILIZER;
         super.material = Material.IRON;
         super.status = 100;
         super.price = Constants.IRON_FERTILIZER_PRICE;
         super.number = 1;
     }
     
 }
