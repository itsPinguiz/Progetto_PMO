package App;

import Exceptions.CustomExceptions.ActionNotAvailableException;
import GUI.Controller;
import GUI.Model;
import GUI.View;

public class Main {
    // Design pattern: MVC and Observer
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(view,model);

        try {
            view.addController(controller);
        } catch (ActionNotAvailableException e) {
            e.printStackTrace();
        }

        view.setVisible(true);
    }
}
