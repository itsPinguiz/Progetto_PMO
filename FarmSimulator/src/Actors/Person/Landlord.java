package Actors.Person;

/**
 * Landlord person implementation
 */
public class Landlord implements Person {
    
    /**
     * Fields
     */
    private Object actualPlace;

    /**
     * Methods
     */
    public Landlord(){
        this.actualPlace = null;
    }
    
    public Object getPlace(){
        return actualPlace;
    }

    public void setPlace(Object newPlace){
        this.actualPlace = newPlace;
    }   
}
