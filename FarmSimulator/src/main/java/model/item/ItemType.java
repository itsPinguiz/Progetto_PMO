/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item;

import model.Constants;

/***********************
 * INTERFACE OF ITEMTYPE
 **********************/
public interface ItemType {

	public enum Animals implements ItemType{
		CHICKEN("Chicken"),
	    COW("Cow"),
	    GOAT("Goat"),
	    PIG("Pig");

		private String name;

        Animals(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        @Override
        public String toString() {
            return getName();
        }
	}
	
	public enum Plants implements ItemType{
		CARROT("Carrot"),	
	    ONION("Onion"),
	    POTATO("Potato"),	
	    WHEAT("Wheat");

		private String name;

        Plants(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        @Override
        public String toString() {
            return getName();
        }
	}
	
	
	public enum productsType implements ItemType{
		MEAT("Meat"),
		MILK("Milk"),
		WOOL("Wool"),
		EGGS("Eggs");	

		private String name;

        productsType(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        @Override
        public String toString() {
            return getName();
        }
	}
	    
	public enum Tools implements ItemType{
		HOE("Hoe"),
		SCISSORS("Scissors"),
		SICKLE("Sickle"),
		FERTILIZER("Fertilizer"),
		WATERINGCAN("Watering Can");
		
		public enum Material{
			WOOD("Wood",Constants.WOOD_MODIFIER),
			IRON("Iron",Constants.IRON_MODIFIER);

			private String name;
			private int modifier;

			Material(String name, int modifier){
				this.name = name;
				this.modifier = modifier;
			}
	
			public String getName(){
				return this.name;
			}

			public int getModifier(){
				return this.modifier;
			}
	
			@Override
			public String toString() {
				return getName();
			}
		}
		
		
		private String name;

		Tools(String name){
			this.name = name;
		}

		public String getName(){
			return this.name;
		}

		@Override
		public String toString() {
			return getName();
		}

		
	}

}

