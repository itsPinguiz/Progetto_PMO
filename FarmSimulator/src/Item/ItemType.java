package Item;

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
	
	
	public enum Products implements ItemType{
		MEAT,
		MILK,
		WOOL,
		FERTILIZER,
		EGGS;
	}
	    
	public enum Tools implements ItemType{
		HOE,
		SCISSORS,
		SICKLE,
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

