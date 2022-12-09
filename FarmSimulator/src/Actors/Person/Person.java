package Actors.Person;

import Land.LandAbstract;

/**
 * Common interface for all people
 */
public interface Person{
    Object getPlace();
    void   setPlace(Object actualPlace);
}