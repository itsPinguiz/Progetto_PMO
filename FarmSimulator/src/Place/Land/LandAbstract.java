package Place.Land;

import Actors.Actions.PlaceActions;
import Calendar.Calendar;
import Place.Place;
import Place.Land.enu.Places;

public abstract class LandAbstract extends Place implements LandInteface {
    static final float BASE_PRICE_LAND = 50; //initial price of a land 
    static final float SELL_PRICE = 25; // price for sold land
    protected boolean sellable; // decides whether a land can be sold or not
    protected Places type; // the type of the land
    protected Calendar calendar = Calendar.getInstance();

    public PlaceActions getActions(){
        /*
         *  Returns the actions of the land
         */
        return super.actions;
    }

    public Places getLandType() {
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
