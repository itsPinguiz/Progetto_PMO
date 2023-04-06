package app;

import javax.swing.SwingUtilities;

import controller.Controller;
import model.Model;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import view.View;

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
