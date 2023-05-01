/********************
 * IMPORT AND PACKAGE
 *******************/

package model.actors.person;

import java.util.HashSet;

import model.actors.actions.playerActions.LandlordActions;
import model.place.Places;

/********************************
 * LANDLORD PERSON IMPLEMENTATION
 *******************************/
public class Landlord extends PersonAbstract{
    
    /**
     * Fields
     */
    private int balance;

    /**
     * Methods
     */
    public Landlord(){
        super.personAction = new LandlordActions(this);
        super.place = null;
        this.role = Role.LANDLORD;
        this.accessiblePlaces = new HashSet<>(){{
            add(Places.BARN);
            add(Places.MARKET);
            }};
        this.balance = 100;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int amount){
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "Landlord";
    }
}
