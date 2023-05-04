package app;

import javax.swing.SwingUtilities;

import controller.Controller;
import model.Model;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import view.View;

/**
    The project consists in the implementation of a farm simulator.

    Main functions:

    The user will be able to take on the role of owner and farmer.
    The owner will be able to buy and sell portions of land, as well as the necessary resources.
    The farmer will have to work the portion of land to allow the growth of plants or the breeding of animals.
    Each portion of land can host animals or plants.
    A repository for storing tools, resources, and animals.
    A shop from which to buy seeds, resources, animals or land.
    Variation of the seasons that influence the weather.
*/

public class Main {
    /**
     * Main class
     * Creates MVC pattern
     * @param args
     */
    @SuppressWarnings("null")
    public static void main(String[] args) {
        Model model;
        Controller controller = null;
        try {
            model = new Model();
            model.initializeGameBackup();
            controller = new Controller(null, model);
            final Controller tmpController = controller;
            SwingUtilities.invokeLater(() -> {
                View view = new View(tmpController);
                tmpController.setView(view); 
                view.updateMVC(tmpController, model);
                view.setVisible(true);
            });
        } catch (NoItemFoundException | 
                 InventoryIsFullException | 
                 NoProductFoundException | 
                 NoAnimalFoundException | 
                 CloneNotSupportedException e) {
            controller.exceptionPopup(e);
        } 
    }
}