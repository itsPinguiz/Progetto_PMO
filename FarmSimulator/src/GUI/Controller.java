package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import Actors.Actions.ActionsManager.Action;
import Exceptions.CustomExceptions.ActionNotAvailableException;
import Exceptions.CustomExceptions.PlaceNotAvailableException;
import Place.Place;
import Progress.GameBackup;

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

        backup.saveCurrent();

        return saveName;
    }

    public ActionListener createLoadCurrentGameActionListener(String save){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // load chosen save
                    backup.loadSave(save.substring(0, save.length() - 3));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        };
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
    public void performAction(Action a) throws IllegalAccessException,
                                               IllegalArgumentException,
                                               InvocationTargetException,
                                               NoSuchMethodException,
                                               SecurityException,
                                               PlaceNotAvailableException,
                                               ActionNotAvailableException{
        System.out.println("Action " + a.toString() + " performed"); // TODO: remove this line
        model.getSelectedPerson().getActions().executeAction(a,model.getSelectedPerson().getPlace());
    }

    // MAP PANEL
    // enter a new place
    public void enterNewPlace(Place p) throws PlaceNotAvailableException{
        model.getSelectedPerson().getActions().enter(p);
    }

    // leave the current place
    public void leaveOldPlace(){
        model.getSelectedPerson().getActions().leave();
    }    
}

