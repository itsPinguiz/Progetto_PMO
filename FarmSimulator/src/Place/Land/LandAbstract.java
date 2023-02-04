package Place.Land;

import Calendar.Calendar;
import Place.Place;

public abstract class LandAbstract extends Place implements LandInteface {
    static final float BASE_PRICE_LAND = 50; //initial price of a land 
    static final float SELL_PRICE = 25; // price for sold land
    protected boolean sellable; // decides whether a land can be sold or not
    protected Calendar calendar = Calendar.getInstance();

    public float getPrice() {
        /*
         *  Returns the price of the land to sell
         */
        return LandAbstract.SELL_PRICE;
    }

    public void update(){

    }
}
