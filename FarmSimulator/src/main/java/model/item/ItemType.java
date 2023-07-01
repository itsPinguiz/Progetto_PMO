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

		/**
         * Constructs a new Animals type with the specified name.
         *
         * @param name the name of the animal type
         */
        Animals(String name){
            this.name = name;
        }

		/**
         * Returns the name of the animal type.
         *
         * @return the name of the animal type
         */
        public String getName(){
            return this.name;
        }

		/**
         * Returns a string representation of the animal type.
         *
         * @return a string representation of the animal type
         */
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

		/**
         * Constructs a new productsType with the specified name.
         *
         * @param name the name of the product type
         */
        productsType(String name){
            this.name = name;
        }

		/**
         * Returns the name of the product type.
         *
         * @return the name of the product type
         */
        public String getName(){
            return this.name;
        }

		/**
         * Returns a string representation of the product type.
         *
         * @return a string representation of the product type
         */
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

			/**
             * Constructs a new Material with the specified name and modifier.
             *
             * @param name     the name of the material
             * @param modifier the modifier of the material
             */
			Material(String name, int modifier){
				this.name = name;
				this.modifier = modifier;
			}
			
			/**
             * Returns the name of the material.
             *
             * @return the name of the material
             */
			public String getName(){
				return this.name;
			}

			/**
             * Returns the modifier of the material.
             *
             * @return the modifier of the material
             */
			public int getModifier(){
				return this.modifier;
			}
			
			/**
             * Returns a string representation of the material.
             *
             * @return a string representation of the material
             */
			@Override
			public String toString() {
				return getName();
			}
		}
		
		
		private String name;

		/**
         * Constructs a new Tools type with the specified name.
         *
         * @param name the name of the tool type
         */
		Tools(String name){
			this.name = name;
		}

		/**
         * Returns the name of the tool type.
         *
         * @return the name of the tool type
         */
		public String getName(){
			return this.name;
		}

		/**
         * Returns a string representation of the tool type.
         *
         * @return a string representation of the tool type
         */
		@Override
		public String toString() {
			return getName();
		}

		
	}

}

