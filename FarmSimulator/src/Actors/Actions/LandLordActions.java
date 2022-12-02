package Actors.Actions;

import Actors.Person.Landlord;

public class LandLordActions extends PersonActions{

    public LandLordActions(Landlord landlord){
        super(landlord);
    }

    // METHODS FOR THE LANDLORD
    public void buy(){
        /*
         * Method to buy item
         * from the market
         */
        //remove from maket and 
        // add it to the barn and 
        // remove the money from the balance
    }

    public void sell(){
        /*
         * Method to sell item
         * to the market
         */
        //remove from barn and 
        // add the money in the balance
    }
}