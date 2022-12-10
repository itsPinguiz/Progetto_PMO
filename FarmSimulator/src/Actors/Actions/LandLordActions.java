package Actors.Actions;

import Actors.Person.Landlord;

public class LandLordActions extends PersonActions{

    public LandLordActions(Landlord landlord){
        super(landlord);
    }

    // METHODS FOR THE LANDLORD
    public void buy(int index){
        /*
         * Method to buy item
         * from the market
         */
        // remove from maket 
        // add it to the barn 
        // remove the money from the balance
    }

    public void sell(int index){
        /*
         * Method to sell item
         * to the market
         */
        // remove from barn 
        // add the money in the balance
    }
}