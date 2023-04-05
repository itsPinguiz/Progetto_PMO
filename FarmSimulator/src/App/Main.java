package App;

import javax.swing.SwingUtilities;
import Exceptions.CustomExceptions.InventoryIsFullException;
import Exceptions.CustomExceptions.NoItemFoundException;
import GUI.Controller;
import GUI.Model;
import GUI.View;

public class Main {
    // Design pattern: MVC and Observer
    public static void main(String[] args) {
        try {
            Model model = new Model();
            Controller controller = new Controller(null, model);
    
            SwingUtilities.invokeLater(() -> {
                View view = new View(model,controller);
                controller.setView(view); // Aggiungi questa linea
                view.setVisible(true);
            });
        } catch (InventoryIsFullException | NoItemFoundException e) {
            e.printStackTrace();
        }
    }
}
