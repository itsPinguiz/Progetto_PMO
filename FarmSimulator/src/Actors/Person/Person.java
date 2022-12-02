package Actors.Person;

/**
 * Common interface for all people
 */
public interface Person{
    void   doAction(String s);
    Object getPlace();
    void   setPlace(Object d);
}