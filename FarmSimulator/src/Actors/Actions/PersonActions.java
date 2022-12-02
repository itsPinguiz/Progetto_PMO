package Actors.Actions;

<<<<<<< HEAD
=======
import java.lang.reflect.Method;
>>>>>>> 388d23a (Stefano's upgrade)
import Actors.Person.Person;

public class PersonActions extends Actions{
<<<<<<< HEAD
<<<<<<< HEAD
    private final Person person;
=======
    private final Object person;
    //private final Person place;
>>>>>>> 29c8d7d (Changed UML)
=======
    private final Object person;
    //private final Person place;
=======
    private final Person person;
>>>>>>> fee66ca (Replaced Objects and Hashmap to Hashset)
>>>>>>> 388d23a (Stefano's upgrade)

    protected PersonActions(Person person){
        super();
        this.person = person;
    }

    public void executeAction(String s) {
        /*
         * Method to find a method to execute
         */
<<<<<<< HEAD
        // find the method and execute it
        try {
           this.getClass().getMethod(s).invoke(null);
           System.out.println(s + " executed.");
        } catch (Exception e) {
            System.out.println("Wasn't able to execute action due to " + e);
        } 
    }
=======
        Method method = null;
        //this.place = person.getplace
<<<<<<< HEAD
>>>>>>> 29c8d7d (Changed UML)
=======

        try {
            method = this.getClass().getMethod(s);
            } catch (SecurityException e) { 
            System.out.println("Wasn't able to execute action.");
            }
            catch (NoSuchMethodException e) { 
                System.out.println("Action '" + s + "' is not present in the current class." + this.getClass().toString());
            }
                catch (NullPointerException  e) { 
                System.out.println("Action is null.");
            }

        return method;
    }
>>>>>>> 388d23a (Stefano's upgrade)

    public void grabItem(){
        /*
         * Method to grab item from
         * the barn
         */
    }

    public void dropItem(){
        /*
         * Method to drop item in
         * the barn
         */
    }
}
