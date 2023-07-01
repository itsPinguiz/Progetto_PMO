/********************
 * IMPORT AND PACKAGE
 *******************/

 package model.actors.person;

 import java.util.HashSet;
 
 import model.actors.actions.playerActions.PlayerActions;
 import model.place.Place;
 import model.place.Places;
 
 /**
  * Represents an abstract class for a person.
  * This class provides a common structure for all types of people,
  * including their current place, possible actions, accessible places, and role.
  * It is intended to be subclassed by other specific person classes.
  */
 public abstract class PersonAbstract implements Person {
     
     /**
      * Current place of the person.
      */
     protected Place place;
 
     /**
      * Available actions for the person.
      */
     protected PlayerActions<? extends Person> personAction;
 
     /**
      * Accessible places for the person.
      */
     protected HashSet<Places> accessiblePlaces;
 
     /**
      * Role of the person.
      */
     protected Role role;
 
     /**
      * Enum representing the role of the person.
      */
     public enum Role{
         FARMER("Farmer"),
         LANDLORD("Landlord");
 
         /**
          * Name of the role.
          */
         String name;
 
         /**
          * Constructs a new Role with a specific name.
          *
          * @param name Name of the role.
          */
         Role(String name)
         {
             this.name = name;
         }
 
         /**
          * Gets the name of the role.
          *
          * @return Name of the role.
          */
         public String getName() {
             return name;
         }
 
         /**
          * Returns a string representation of the role.
          *
          * @return String representation of the role.
          */
         @Override
         public String toString() {
             return getName();
         }
     }
 
     /**
      * Gets the current place of the person.
      *
      * @return The current place of the person.
      */
     public Place getPlace(){
         return this.place;
     }
 
     /**
      * Sets the current place of the person.
      *
      * @param actualPlace The new place of the person.
      */
     public void setPlace(Place actualPlace){
         this.place = actualPlace;
     }
 
     /**
      * Gets the actions of the person.
      *
      * @return The actions of the person.
      */
     public PlayerActions<? extends Person> getActions() {
         return this.personAction;
     }
     
 
     /**
      * Gets the accessible places for the person.
      *
      * @return The accessible places for the person.
      */
     public HashSet<Places> getAccessiblePlaces(){
         return this.accessiblePlaces;
     }
 
     /**
      * Gets the role of the person.
      *
      * @return The role of the person.
      */
     public Role getRole(){
         return this.role;
     }
 }
 
