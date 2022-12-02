package Actors.Actions;

import java.lang.reflect.Method;

public class PersonActions extends Actions{
    private final Object person;

    protected PersonActions(Object person){
        super();
        this.person = person;
    }

    public Method findAction(String s) {
        /*
         * Method to find a method to execute
         */
        Method method = null;

        if (super.availableActions.containsKey(s)){
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
        } else{
            System.out.println("Action '" + s + "' is not available in the current place.");
        }
        return method;
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
