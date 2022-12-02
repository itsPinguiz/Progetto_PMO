import java.lang.reflect.Method;

import Actors.Actions.FarmerActions;
import Actors.Actions.PersonActions;
import Actors.Person.Farmer;

public class App {
    public static void main(String[] args) throws Exception {
        Farmer  f = new Farmer();
        PersonActions a = new FarmerActions(f);
        Method method;

        method = a.findAction("grabitem");
        method.invoke(null);
    }
}
