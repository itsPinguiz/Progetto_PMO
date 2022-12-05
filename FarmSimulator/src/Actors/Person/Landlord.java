package Actors.Person;

/**
 * Landlord person implementation
 */
public class Landlord implements Person {
    
    /**
     * Fields
     */
    private Object actualPlace;
    private int    balance;

    /**
     * Methods
     */
    public Landlord(){
        this.actualPlace = null;
        this.balance = 100;
    }
    
    public Object getPlace(){
        return actualPlace;
    }

    public void setPlace(Object newPlace){
        this.actualPlace = newPlace;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int amount){
        this.balance += amount;
    }

}
