/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item;

/***********************
 * INTERFACE OF ITEMTYPE
 **********************/
public interface ItemType {

	public enum Animals implements ItemType{
		CHICKEN,
	    COW,
	    GOAT,
	    PIG;
	}
	
	public enum Plants implements ItemType{
		CARROT,
	    ONION,
	    POTATO,
	    WEAT;
	}
	
	
	public enum productsType implements ItemType{
		MEAT,
		MILK,
		WOOL,
		EGGS;	
	}
	    
	public enum Tools implements ItemType{
		HOE,
		SCISSORS,
		SICKLE,
		FERTILIZER,
		/*
		 * TODO realizzare la classe fertilizer poiché adesso è uno strumento e non è più solo un prodotto.
		 */
		WATERINGCAN;
		
		public enum Material{
			WOOD,
			IRON;
		}
		
		private  Material material;
		
		public void setMaterial(Material newMaterial) {
			this.material = newMaterial ;
		}
		
		public Material getMaterial() {
			return this.material;
		}
	}

}

