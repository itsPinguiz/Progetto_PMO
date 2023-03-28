package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Actors.Actions.ActionsManager;
import Actors.Actions.PlayerActions;
import Actors.Actions.ActionsManager.Action;
import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Item.ItemType.Plants;
import Place.Place;
import Place.Barn.Barn;
import Place.Barn.Market.Market;
import Place.Land.AnimalLand;
import Place.Land.LandAbstract;
import Place.Land.PlantChunk;

public class CLI {
    private Game game;
    private boolean isRunning;

    public CLI(){
        this.game = new Game();
        this.isRunning = true;
    }

    

    // input from user
    public void userInput(){
        System.out.println("##### Welcome to the Farm Simulator #####");
        while (this.isRunning){
            System.out.println("$ ");
            String command = System.console().readLine();
            this.executeCommand(command);
            //clear screen 
            // System.out.print("\033[H\033[2J");
        }
    }

    // interactions with items in places the player is in
    public boolean interact(){
        int indexOfChosenAction = 0;
        PlayerActions actions = this.game.getSelectedPerson().getActions();
        List<ActionsManager.Action> actionsList = new ArrayList<>();

        actionsList.addAll(actions.getActions());

        System.out.println("0. Esc \n 1. Select Item ");
        String command = System.console().readLine();
        
        switch (command) { 
            case "1":
                System.out.println("$ Type item number: ");
                Game.GameData.secondIndex = Integer.parseInt(System.console().readLine()); 
                break;
            default:
                return false;
        }

        this.printActions();
        System.out.println("$ Action Number: ");
        System.out.println("-1. " + "Exit");
        indexOfChosenAction = Integer.parseInt(System.console().readLine());
        
        actions.executeAction(actionsList.get(indexOfChosenAction));
        System.out.println("Action executed");
        // TODO Consider actions that require more than one input
        return true;   
    }

    public void printActions(){
        int i = 0; 
        System.out.println("Available interactions: ");
        for (Action a : this.game.getSelectedPerson().getActions().getActions()){
            System.out.println(i + ". " + a);
            i++;
        }
    }

    // execute the command
    public void executeCommand(String command){
        if (command.equals("CR")){
            this.game.changePerson();
        }
        if (command.equals("exit")){
            this.isRunning = false;
        }
        if (command.equals("esc")){
            this.printWorld();
        }else {
            System.out.println("Command not found");
        }
    }

    // print the external world 
    public void printWorld(){
        // merge two arraylists in one
        ArrayList<Place> places = Game.GameData.map.get(0);
        places.addAll(Game.GameData.map.get(1));

        // print all the places with the right separation
        System.out.println("0. Barn");
        for (Place place : Game.GameData.map.get(1)){
            int index = 1;
            while (index < 5 && Game.GameData.map.get(1).get(index) != null){
                System.out.println(index + ". " + ((place instanceof AnimalLand)? "AnimalLand" :"PlantLand"));
                index++;
            }
            while (Game.GameData.map.get(1).get(index) != null){
                System.out.println("       " + index + ". " + ((place instanceof AnimalLand)? "AnimalLand" :"PlantLand"));
                index++;
            }
        }
    }

    //print inside the place
    public void printInsidePlace(){
        // print the place
        Place actualPlace = this.game.getSelectedPerson().getPlace();
        System.out.println("esc. Exit");
        if (actualPlace instanceof Barn){
            // print market and inventory TODO
        } else if (actualPlace instanceof LandAbstract){
            LandAbstract l = (LandAbstract) actualPlace;
            // prints the elements
            for (Object element : l.getElements()){
                int index = 0;

                if (index > 5){
                    System.out.print("\n");
                }
                if (element instanceof PlantChunk){
                    PlantChunk p = (PlantChunk) element;
                    System.out.print(index + ".  " +((Plants)(p.getPlant().getType())).toString()+ "\t");
                }
            }
        }
    }

    // prints the selected role
    public void printSelectedRole(){
        if (this.game.getSelectedPerson() instanceof Farmer)
            System.out.println("Role: Farmer");
        if (this.game.getSelectedPerson() instanceof Landlord)
            System.out.println("Role: Landlord");
    }
}
