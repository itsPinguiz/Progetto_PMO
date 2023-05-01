package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Model;
import model.actors.actions.ActionsManager.Action;
import model.actors.person.Person;
import model.actors.person.PersonAbstract.Role;
import model.calendar.Calendar;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.exceptions.CustomExceptions.NoItemFoundException;
import model.exceptions.CustomExceptions.PlaceNotAvailableException;
import model.exceptions.CustomExceptions.SaveIsCorruptedException;
import model.inventory.Inventory;
import model.place.Place;
import model.place.barn.Barn;
import model.place.land.LandAbstract;
import model.progress.GameBackup;
import view.View;

public class Controller {
    // attributes
    private View view;
    private Model model;
    
    // constructor
    public Controller(View view,Model model) {
        this.view = view;
        this.model = model;
    }

    // get model
    public Model getModel() {
        return model;
    }

    // update model 
    public void updateModel(){
        try {
            model.update();
        } catch (InventoryIsFullException | NoItemFoundException | CloneNotSupportedException  | PlaceNotAvailableException e) {
            this.exceptionPopup(e);
        }
    }

    // exception popup
    public void exceptionPopup(Exception exception){
        if (exception.getCause() == null && exception.getMessage() != null)
            view.exceptionPopup(exception.getMessage());
        else if (exception.getCause() == null && exception.getMessage() == null)
            exception.printStackTrace();
        else
            view.exceptionPopup(exception.getCause().getMessage());
    }

    // BACKUP PANEL
    // get backup
    public GameBackup getBackup() {
        return model.getBackup();
    }

    // delete save 
    public void deleteSave(String save) {
        try {
            model.getBackup().deleteSave(save);
        } catch (IOException e) {
            this.exceptionPopup(e);
        }
    }

    // save game
    public String saveGame(){
        String saveName = null;

        try {
            saveName = model.getBackup().saveCurrent();
        } catch (IOException e) {
            this.exceptionPopup(e);
        }

        return saveName + ".txt";
    }

    // load game
    public void loadGame(String save){
        Controller controllerInstance = this; // riferimento all'istanza corrente di Controller
        // load chosen save
        try {
            model = model.getBackup().loadSave(save);
        } catch (ClassNotFoundException | IOException  | SaveIsCorruptedException e) {
            exceptionPopup(e);
        }
        model.getSelectedPerson().getActions().leave();
        view.getContentPane().removeAll();
        view.updateMVC(controllerInstance, model);
    }

    // ROLE PANEL
    // swap the role and update the role actions panel
    public void changeRole(Role role){
        this.model.setSelectedPerson(this.model.getPlayer().get(role));
    }

    // execute the action
    public <V extends Person> void performAction(Action a) {
        try {
            model.sendAction(a);
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
        return model.getOldPlace();
    }

    public Inventory getOldInventory(){
        return model.getOldInventory();
    }

    public Object getSelectedItem(){
        return model.getSelectedItem();
    }

    public void setOldPlace(Place p){
        model.setOldPlace(p);;
    }

    public void setOldInventory(Inventory i){
        model.setOldInventory(i);
    }

    public void setSelectedItem(Object i){
        model.setSelectedItem(i);
    }

    public void setView(View view) {
        this.view = view;
    }    

    

    public Person getSelectedPerson(){
        return model.getSelectedPerson();
    }

    public Person getPlayer(Role role){
        return model.getPlayer().get(role);
    }

    public Calendar getCalendar(){
        return model.getCalendar();
    }

    public Barn getBarn(){
        return model.getBarn();
    }

    public ArrayList<LandAbstract> getLands(){
        return model.getLands();
    }
}
