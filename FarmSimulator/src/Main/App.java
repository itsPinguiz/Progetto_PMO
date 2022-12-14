<<<<<<< HEAD:FarmSimulator/src/Main/App.java
package Main;
=======
>>>>>>> 8cc7fa4e687f02e99d3d3d62da85cec8cb8bd381:FarmSimulator/src/App.java

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
<<<<<<< HEAD:FarmSimulator/src/Main/App.java
import java.util.concurrent.atomic.AtomicInteger;

=======
>>>>>>> 8cc7fa4e687f02e99d3d3d62da85cec8cb8bd381:FarmSimulator/src/App.java
import Progress.GameBackup;

public class App {
    public static AtomicInteger itemIndex; // clicked item

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
            while(true){
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
                        break;
                    } else if(n == 1) {
                        actualObjects.add(s); //test
                        backup.saveCurrent();
                        break;
                    }
                    
                } catch(IndexOutOfBoundsException e){
                    System.out.println("Too many tries, ending the program.");  
                    if (tries == 10) throw e;
                }
            }
        } 
    }

    public static void main(String[] args) throws Exception {
        String s = "Ora sono questo"; //test
        List<Serializable> actualObjects = new ArrayList<>(); // All game objects to save
        GameBackup backup = new GameBackup(actualObjects); // Backup manager
        
        App.menu(actualObjects,backup,s); // save menu
    }
}

