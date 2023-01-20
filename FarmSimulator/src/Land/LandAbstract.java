package Land;

import Actors.Actions.PlaceActions;
import Land.enums.LandType;

public abstract class LandAbstract implements LandInteface {
    static final float BASE_PRICE_LAND = 50; //initial price of a land 
    static final float SELL_PRICE = 25; // price for sold land
    protected boolean sellable; // decides whether a land can be sold or not
    protected LandType type; // the type of the land
    protected PlaceActions actions; // actions for the specific land

    protected PlaceActions getActions(){
        /*
         *  Returns the actions of the land
         */
        return this.actions;
    }

    public LandType getLandType() {
        /*
         * Returns the type of the
         * land  
         */
        return this.type;
    }

    public float getPrice() {
        /*
         *  Returns the price of the land to sell
         */
        return LandAbstract.SELL_PRICE;
    }
}
