package Place.Land;

import java.util.ArrayList;
import Calendar.Calendar;
import Place.Place;

public abstract class LandAbstract extends Place implements LandInteface {
    public static final int BASE_PRICE_LAND = 50; //initial price of a land 
    public static final int SELL_PRICE = 25; // price for sold land
    protected boolean sellable; // decides whether a land can be sold or not
    protected Calendar calendar = Calendar.getInstance();

    public float getPrice() {
        /*
         *  Returns the price of the land to sell
         */
        return LandAbstract.SELL_PRICE;
    }

    public void update(){
        /*
         *  Updates the entities inside the land
         */
        System.out.println("Land was not updated");
    }

    public ArrayList<? extends Object> getElements(){
        /*
         * Returns the elements present in the land
         */
        return null;
    }

    public int getNumElements(){
        /*
         * Returns the number of elements present in the land
         */
        return 0;
    }

    public boolean isSellable(){
        /*
         * Returns whether the land is sellable or not
         */
        return this.sellable;
    }
}
