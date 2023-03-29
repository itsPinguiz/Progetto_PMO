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
        try (Scanner scanner = new Scanner(System.in)){
            int tries = 0;
            Boolean done = false;
            while(!done){
                try{
                    
                    clrscr();
                    tries++;
                    System.out.printf("Select the options: \n[1] Create New Save\n[2] Load Old Save\n> ");  
                    int n = scanner.nextInt();
                    if (n == 2){
                        clrscr();
                        System.out.println("Select which save you want to load\n");  
                        backup.printSavesList();
                        System.out.printf("> ");
                        
                        actualObjects = List.copyOf(backup.loadSave(scanner.nextInt()));
                    
                        s = (String)actualObjects.get(0); //test
                        System.out.println(s); //test
                        done = true;
                        break;
                    } else if(n == 1) {
                        actualObjects.add(s); //test
                        backup.saveCurrent();
                        done = true;
                        break;
                    }
                    
                } catch(IndexOutOfBoundsException e){
                    System.out.println("Too many tries, ending the program.");  
                    if (tries == 10) throw e;
                }
            }
            scanner.close();
        } 
        
        /*
         * Menu to play the game
         */
    }
    /*public static void main(String[] args) {
        CLI inter = new CLI();
        inter.userInput();
     }*/
}

