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
import model.progress.GameBackupManager;
import view.View;

/**
 * Controller class of the MVC pattern, manages the interaction between the view and the model
 */
public class Controller {
    /**
     * Attributes
     */
    private View view;
    private Model model;
    
    /**
     * Constructor
     * @param view
     * @param model
     */
    public Controller(View view,Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Returns the model
     * @return Model 
     */
    public Model getModel() {
        return model;
    }

    /**
     * Sets the View
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }    

    /**
     * Updates the model and all its elements
     */
    public void updateModel(){
        try {
            model.update();
        } catch (InventoryIsFullException | NoItemFoundException | CloneNotSupportedException  | PlaceNotAvailableException e) {
            this.exceptionPopup(e);
        }
    }

    /**
     *  Method that calls the view to create a popup with the exception message
     */
    public void exceptionPopup(Exception exception){
        if (exception.getCause() == null && exception.getMessage() != null)
            view.exceptionPopup(exception.getMessage());
        else if (exception.getCause() == null && exception.getMessage() == null)
            exception.printStackTrace();
        else
            view.exceptionPopup(exception.getCause().getMessage());
    }

    // BACKUP PANEL
    
    /**
     * Returns the backup manager
     * @return GameBackupManager
     */
    public GameBackupManager getBackup() {
        return model.getBackup();
    }

    /**
     * Deletes the save with the given name
     * @param save name of the save to delete
     */
    public void deleteSave(String save) {
        try {
            model.getBackup().deleteSave(save);
        } catch (IOException e) {
            this.exceptionPopup(e);
        }
    }

    /**
     * Saves the current game and returns the name of the save
     * @return String name of the save
     */
    public String saveGame(){
        String saveName = null;
        try {
            saveName = model.getBackup().saveCurrent();
        } catch (IOException e) {
            this.exceptionPopup(e);
        }
        return saveName + ".txt";
    }

    /**
     * Loads the game with the given name
     * @param save name of the save to load
     */
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
    
    /**
     * Changes the selected role to the given one
     * @param role role to change to
     */
    public void changeRole(Role role){
        this.model.setSelectedPerson(this.model.getRoles().get(role));
    }

    /**
     * Calls the model to execute the given action
     * @param <V> Item or LandAbstract
     * @param action action to execute
     */
    public <V extends Person> void performAction(Action action) {
        try {
            model.sendAction(action);
        } catch (Exception e) {
            this.exceptionPopup(e);
        }
    }
    
    // MAP PANEL
    /**
     * Changes the place of the selected person to the given one
     * @param newPlace place to change to
     * @return Place old place
     */
    public Place enterNewPlace(Place newPlace){
        Place oldPlace = model.getSelectedPerson().getPlace();
        try {
            model.getSelectedPerson().getActions().enter(newPlace);
        } catch (PlaceNotAvailableException e) {
            this.exceptionPopup(e);
        }
        return oldPlace;
    }

    /**
     * Leaves the current place of the selected person
     * @return Place old place
     */
    public Place leaveOldPlace(){
        Place oldPlace = model.getSelectedPerson().getPlace();
        model.getSelectedPerson().getActions().leave();
        return oldPlace;
    }

    /**
     * Returns the old place
     * @return Place old place
     */
    public Place getOldPlace(){
        return model.getOldPlace();
    }

    /**
     * Returns the old inventory
     * @return Inventory old inventory
     */ 
    public Inventory getOldInventory(){
        return model.getOldInventory();
    }

    /**
     * Returns the selected item
     * @return Object selected item
     */
    public Object getSelectedItem(){
        return model.getSelectedItem();
    }

    /**
     * Sets the old place
     * @param newOldPlace old place to set
     */
    public void setOldPlace(Place newOldPlace){
        model.setOldPlace(newOldPlace);;
    }

    /**
     * Sets the old inventory
     * @param newOldInventory old inventory to set
     */
    public void setOldInventory(Inventory newOldInventory){
        model.setOldInventory(newOldInventory);
    }

    /**
     * Sets the selected item
     * @param newSelectedItem item to set
     */
    public void setSelectedItem(Object newSelectedItem){
        model.setSelectedItem(newSelectedItem);
    }

    /**
     * Returns the selected person
     * @return Person selected person
     */
    public Person getSelectedPerson(){
        return model.getSelectedPerson();
    }

    /**
     * Returns the player with the given role
     * @param role
     * @return Person player
     */
    public Person getPlayer(Role role){
        return model.getRoles().get(role);
    }

    /**
     * Returns the Calendar instance
     * @return Calendar
     */
    public Calendar getCalendar(){
        return Calendar.getInstance();
    }

    /**
     * Returns the Barn instance
     * @return Barn
     */
    public Barn getBarn(){
        return model.getBarn();
    }

    /**
     * Returns the lands
     * @return ArrayList<LandAbstract> lands
     */
    public ArrayList<LandAbstract> getLands(){
        return model.getLands();
    }
}
