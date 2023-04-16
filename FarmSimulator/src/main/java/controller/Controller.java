package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Model;
import model.actors.actions.ActionsManager.Action;
import model.actors.person.PersonAbstract.Role;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.PlaceNotAvailableException;
import model.inventory.Inventory;
import model.place.Place;
import model.progress.GameBackup;
import view.View;

public class Controller {
    // attributes
    private View view;
    private Model model;
    private GameBackup backup;
    private Object selectedItem;
    private Inventory oldInventory;
    private Place oldPlace;

    // constructor
    public Controller(View view,Model model) {
        this.view = view;
        this.model = model;
        this.backup = new GameBackup(model);

        this.oldInventory = null;
        this.selectedItem = null;
        this.oldPlace = null;
    }

    // get model
    public Model getModel() {
        return model;
    }

    // BACKUP PANEL
    // get backup
    public GameBackup getBackup() {
        return backup;
    }

    // delete save 
    public void deleteSave(String save) {
        try {
            backup.deleteSave(save);
        } catch (IOException e) {
            this.exceptionPopup(e);
        }
    }

    // save game
    public String saveGame(){
        String saveName = null;

        try {
            saveName = backup.saveCurrent();
        } catch (IOException e) {
            this.exceptionPopup(e);
        }

        return saveName + ".txt";
    }

    // load game
    public void loadGame(String save){
        Controller controllerInstance = this; // riferimento all'istanza corrente di Controller
        try {
            // load chosen save
            model = backup.loadSave(save);
            model.getSelectedPerson().getActions().leave();
            view.getContentPane().removeAll();
            view.updateMVC(controllerInstance, model);

        } catch (Exception e1) {    
            view.exceptionPopup(e1.getCause().getMessage());
        }
    }

    // ROLE PANEL
    // swap the role and update the role actions panel
    public void changeRole(Role role){
        if (role == model.getPersons()[0].getRole()) {
            this.model.setSelectedPerson(this.model.getPersons()[0]);
          } else if (role == model.getPersons()[1].getRole()) {
            this.model.setSelectedPerson(this.model.getPersons()[1]);
          }	
    }

    // execute the action
    public void performAction(Action a,ArrayList<? extends Object> items){ 
        try {
            model.getSelectedPerson().getActions().executeAction(a,items);
        } catch (Exception e) {
            this.exceptionPopup(e);
        }
    }

    // MAP PANEL
    // enter a new place
    public Place enterNewPlace(Place p){
        Place oldPlace = model.getSelectedPerson().getPlace();
        try {
            model.getSelectedPerson().getActions().enter(p);
        } catch (PlaceNotAvailableException e) {
            this.exceptionPopup(e);
        }
        return oldPlace;
    }

    // leave the current place
    public Place leaveOldPlace(){
        Place oldPlace = model.getSelectedPerson().getPlace();
        model.getSelectedPerson().getActions().leave();
        return oldPlace;
    }

    public Place getOldPlace(){
        return oldPlace;
    }

    public Inventory getOldInventory(){
        return oldInventory;
    }

    public Object getSelectedItem(){
        return selectedItem;
    }

    public void setOldPlace(Place p){
        oldPlace = p;
    }

    public void setOldInventory(Inventory i){
        oldInventory = i;
    }

    public void setSelectedItem(Object i){
        selectedItem = i;
    }

    public void setView(View view) {
        this.view = view;
    }    

    // update model 
    public void updateModel(){
        try {
            model.update();
        } catch (InventoryIsFullException | NoItemFoundException | CloneNotSupportedException e) {
            this.exceptionPopup(e);
        }
    }

    // exception popup
    public void exceptionPopup(Exception exception){
        view.exceptionPopup(exception.getCause().getMessage());
    }

}
