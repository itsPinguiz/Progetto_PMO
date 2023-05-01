package model.place;

import java.util.ArrayList;

import model.exceptions.CustomExceptions.*;
import model.place.barn.Barn;
import model.place.land.AnimalLand;
import model.place.land.LandAbstract;
import model.place.land.PlantLand;

public class GameMap {
    private ArrayList<LandAbstract> lands;
    private Barn barn;

    public GameMap() throws NoItemFoundException, 
                            InventoryIsFullException, 
                            CloneNotSupportedException, 
                            NoAnimalFoundException,
                            NoProductFoundException{
        this.lands = new ArrayList<>(){
            {
                add(new PlantLand());
                add(new PlantLand());
                add(new AnimalLand());
                add(new AnimalLand());
            }
        };
        this.barn = new Barn();
        this.barn.getMarket().addBarn(this.barn);
    }
    
    public ArrayList<LandAbstract> getLands(){
        return this.lands;
    }

    public Barn getBarn(){
        return this.barn;
    }
}
