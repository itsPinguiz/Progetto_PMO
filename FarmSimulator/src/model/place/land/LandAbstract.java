package model.place.land;

import java.util.ArrayList;

import model.Constants;
import model.calendar.Calendar;
import model.place.Place;

public abstract class LandAbstract extends Place implements LandInteface {
    protected boolean sellable; // decides whether a land can be sold or not
    protected Calendar calendar = Calendar.getInstance();

    public int getPrice() {
        /*
         *  Returns the price of the land to sell
         */
        return Constants.LAND_SELL_PRICE;
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
