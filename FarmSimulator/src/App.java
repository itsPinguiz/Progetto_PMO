import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import Actors.Actions.PersonActions;
import Progress.GameBackup;

public class App {
    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    public static void main(String[] args) throws Exception {
        String s = "Ora sono questo";
        List<Serializable> actualObjects = new ArrayList<>();
        GameBackup backup = new GameBackup(actualObjects);

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
                        
                        
                        s = (String)actualObjects.get(0);
                        System.out.println(s);
                        break;
                    } else if(n == 1) {
                        actualObjects.add(s);
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
}
