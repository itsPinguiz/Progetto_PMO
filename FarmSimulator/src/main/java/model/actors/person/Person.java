/********************
* IMPORT AND PACKAGE
*******************/

package model.actors.person;

import java.io.Serializable;
import java.util.HashSet;

import model.actors.actions.playerActions.PlayerActions;
import model.actors.person.PersonAbstract.Role;
import model.place.Place;
import model.place.Places;

/**
* Represents a common interface for all people.
* This interface includes methods for retrieving and setting player actions, 
* accessing places, getting current place, setting current place, and getting role.
* This interface extends Serializable which means it can be serialized.
*/
public interface Person extends Serializable{

   /**
    * Gets the player actions of this person.
    *
    * @return the player actions of this person.
    */
   PlayerActions<? extends Person> getActions();

   /**
    * Gets the accessible places for this person.
    *
    * @return the accessible places for this person.
    */
   public HashSet<Places> getAccessiblePlaces();

   /**
    * Gets the current place of this person.
    *
    * @return the current place of this person.
    */
   Place getPlace();

   /**
    * Sets the current place of this person.
    *
    * @param actualPlace the new place of this person.
    */
   void setPlace(Place actualPlace);

   /**
    * Gets the role of this person.
    *
    * @return the role of this person.
    */
   Role getRole();
}

