/********************
 * IMPORT AND PACKAGE
 *******************/

package model.actors.person;

import model.actors.actions.PlayerActions;

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
        super.personAction = new PlayerActions(this);
        super.place = null;
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
        // TODO Auto-generated method stub
        return "Landlord";
    }
}
