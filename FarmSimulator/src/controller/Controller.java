package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import model.Model;
import model.actors.actions.ActionsManager.Action;
import model.exceptions.CustomExceptions.ActionNotAvailableException;
import model.exceptions.CustomExceptions.PlaceNotAvailableException;
import model.place.Place;
import model.progress.GameBackup;
import view.View;

public class Controller {
    // attributes
    private View view;
    private Model model;
    private GameBackup backup;

    // constructor
    public Controller(View view,Model model) {
        this.view = view;
        this.model = model;
        this.backup = new GameBackup(model);
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
    public void deleteSave(String save) throws IOException, InterruptedException, InvocationTargetException, NoSuchMethodException, SecurityException{
        backup.deleteSave(save);
    }

    // save game
    public String saveGame() throws IOException{
        String saveName = null;

        saveName = backup.saveCurrent();

        return saveName + ".txt";
    }

    // load game
    public void loadGame(String save){
        Controller controllerInstance = this; // riferimento all'istanza corrente di Controller
        try {
            // load chosen save
            model = backup.loadSave(save);
            model.getSelectedPerson().getActions().leave();
            view.updateMVC(controllerInstance, model);
        } catch (Exception e1) {    
            e1.printStackTrace();
        }
    }

    // ROLE PANEL
    // swap the role and update the role actions panel
    public void changeRole(String role){
        if (role == model.getPersons()[0].toString()) {
            this.model.setSelectedPerson(this.model.getPersons()[0]);
          } else if (role == model.getPersons()[1].toString()) {
            this.model.setSelectedPerson(this.model.getPersons()[1]);
          }	
    }

    // execute the action
    public void performAction(Action a,ArrayList<? extends Object> items) throws IllegalAccessException,
                                               IllegalArgumentException,
                                               InvocationTargetException,
                                               NoSuchMethodException,
                                               SecurityException,
                                               PlaceNotAvailableException,
                                               ActionNotAvailableException{ 
        model.getSelectedPerson().getActions().executeAction(a,items);
    }

    // MAP PANEL
    // enter a new place
    public Place enterNewPlace(Place p) throws PlaceNotAvailableException{
        Place oldPlace = model.getSelectedPerson().getPlace();
        model.getSelectedPerson().getActions().enter(p);
        return oldPlace;
    }

    // leave the current place
    public void leaveOldPlace(){
        model.getSelectedPerson().getActions().leave();
    }

    public void setView(View view) {
        this.view = view;
    }    

    // update model 
    public void updateModel(){
        model.update();
    }

}

