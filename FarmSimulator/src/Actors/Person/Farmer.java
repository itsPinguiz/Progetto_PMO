package Actors.Person;

/**
 * Farmer person implementation
 */
public class Farmer implements Person{
    
    /**
     * Fields
     */
    private Object actualPlace;

    /**
     * Methods
     */

    public Farmer( ){
        this.actualPlace = null;
    }

    public Object getPlace(){
        return actualPlace;
    }

    public void setPlace(Object newPlace){
        this.actualPlace = newPlace;
    } 

}
