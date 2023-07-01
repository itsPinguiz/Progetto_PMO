/********************
 * IMPORT AND PACKAGE
 *******************/
package model.actors.person;

import java.util.HashSet;

import model.actors.actions.playerActions.LandlordActions;
import model.place.Places;

/**
 * Represents a Landlord which is an extension of PersonAbstract.
 * A Landlord has a balance and can perform various LandlordActions.
 * This class is responsible for managing the state and behaviour of a Landlord.
 */
public class Landlord extends PersonAbstract{

    /**
     * Balance of the Landlord.
     */
    private int balance;

    /**
     * Constructs a new Landlord with default values.
     * Initializes the landlord's actions, place, accessible places, and balance.
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

    /**
     * Returns the balance of the landlord.
     *
     * @return The balance of the landlord.
     */
    public int getBalance(){
        return this.balance;
    }

    /**
     * Updates the balance of the landlord by the provided amount.
     *
     * @param amount The amount to be added to the current balance of the landlord.
     */
    public void setBalance(int amount){
        this.balance += amount;
    }

    /**
     * Returns a string representation of the landlord.
     *
     * @return String representation of the landlord, i.e., "Landlord".
     */
    @Override
    public String toString() {
        return "Landlord";
    }
}

