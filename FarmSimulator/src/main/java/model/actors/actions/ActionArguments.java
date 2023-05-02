package model.actors.actions;

/**
 * Class that is used to pass arguments to the actions
 */
public class ActionArguments<T1, T2, T3> {
    /**
     * Attributes
     */
    private  T1 arg1;
    private  T2 arg2;
    private  T3 arg3;

    /**
     * Constructor
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param arg3 Third argument
     */
    public ActionArguments(T1 arg1, T2 arg2, T3 arg3) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    /**
     * Returns the first argument
     * @return
     */
    public T1 getArg1() {
        return arg1;
    }

    /**
     * Returns the second argument
     * @return
     */
    public T2 getArg2() {
        return arg2;
    }

    /**
     * Returns the third argument
     * @return
     */
    public T3 getArg3() {
        return arg3;
    }

    /**
     * Sets the first argument
     * @param arg1
     */
    public void setArg1(T1 arg1) {
        this.arg1 = arg1;
    }
    
    /**
     * Sets the second argument
     * @param arg2
     */
    public void setArg2(T2 arg2) {
        this.arg2 = arg2;
    }

    /**
     * Sets the third argument
     * @param arg3
     */
    public void setArg3(T3 arg3) {
        this.arg3 = arg3;
    }
}