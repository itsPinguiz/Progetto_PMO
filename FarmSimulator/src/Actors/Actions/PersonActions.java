package Actors.Actions;

<<<<<<< HEAD
import Actors.Person.Person;

public class PersonActions extends Actions{
    private final Person person;
=======
import java.lang.reflect.Method;
import Actors.Person.Person;

public class PersonActions extends Actions{
    private final Object person;
    //private final Person place;
>>>>>>> LuisTest

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
=======
        Method method = null;
        //this.place = person.getplace

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
>>>>>>> LuisTest
    }

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
