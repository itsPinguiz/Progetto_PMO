package Actors.Actions;

import Actors.Person.Farmer;

public class FarmerActions extends PersonActions{
    
    public FarmerActions(Farmer farmer){
        super(farmer);
    }

    // METHODS FOR THE FARMER

    public void grabItem(){
        /*
         * Method to grab item from
         * the barn
         */
    }

    public void dropItem(){
        /*
         * Method to drop item on
         * the floor and lose it
         */
       ((Farmer)super.person).removeItem(PersonActions.itemIndex.get());
    }

    public void moveItem(){
        /*
         * Method to move the item
         * to the barn
         */
    }
    
    public void plant(){
        /*
         * Method to plant a plant
         */
    }

    public void water(){
        /*
         * Method to water a plant
         */

    }

    public void fertilize(){
        /*
         * Method to fertilize a plant
         */

    }

    public void harvest(){
        /*
         * Method to harvest a plant
         */

    }

    
}
