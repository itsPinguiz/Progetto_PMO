package App;

import GUI.Controller;
import GUI.Model;
import GUI.View;

public class Main {
    // Design pattern: MVC and Observer
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(view,model);

        view.addController(controller);

        view.setVisible(true);
    }
}
