package app;

import javax.swing.SwingUtilities;

import controller.Controller;
import model.Model;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import view.View;

public class Main {
    // Design pattern: MVC and Observer
    @SuppressWarnings("null")
    public static void main(String[] args) {
        Model model;
        Controller controller = null;
        try {
            model = new Model();
            controller = new Controller(null, model);
            final Controller tmpController = controller;
            SwingUtilities.invokeLater(() -> {
                View view = new View(model,tmpController);
                tmpController.setView(view); // Aggiungi questa linea
                view.setVisible(true);
        });
        } catch (NoItemFoundException | InventoryIsFullException e) {
            controller.exceptionPopup(e);
        }
        
    }
}
