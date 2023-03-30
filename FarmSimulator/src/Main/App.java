package Main;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import Progress.GameBackup;

public class App {
    // TODO Will be replaced once the swing interface is made

    public static void clrscr(){
        /*
         * Clears Terminal
        */
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    
    public static void menu(List<Serializable> actualObjects,GameBackup backup,String s ) throws Exception{
        /*
         * Menu to let user decide if to load an old save
         * or make a new one
         */
        
        /*
         * Menu to play the game
         */
    }
    /*public static void main(String[] args) {
        CLI inter = new CLI();
        inter.userInput();
     }*/
}

