package model.place;

import java.util.ArrayList;

import model.exceptions.CustomExceptions.*;
import model.place.barn.Barn;
import model.place.land.LandAbstract;
import model.place.land.landTypes.AnimalLand;
import model.place.land.landTypes.PlantLand;

/**
 * Class that represents the game map
 */
public class GameMap {
    /**
     * Attributes
     */
    private ArrayList<LandAbstract> lands;
    private Barn barn;

    /**
     * Constructor
     * Initializes the lands (2 PlantLand and 2 AnimalLand) and the barn
     * @throws NoItemFoundException Barn Exception
     * @throws InventoryIsFullException Barn and Market Exception
     * @throws CloneNotSupportedException
     * @throws NoAnimalFoundException AnimalLand Exception
     * @throws NoProductFoundException AnimalLand Exception
     */
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
    
    /**
     * Returns the lands
     * @return  Lands
     */
    public ArrayList<LandAbstract> getLands(){
        return this.lands;
    }

    /**
     * Returns the barn
     * @return  Barn
     */
    public Barn getBarn(){
        return this.barn;
    }
}
