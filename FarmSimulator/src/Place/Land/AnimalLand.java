package Place.Land;

import java.util.ArrayList;

import Actors.Actions.PlaceActions;
import Animal.AnimalAbstract;
import Place.Places;

public class AnimalLand extends LandAbstract {
    /*Land that contains different animals*/

    //attributes 
    private ArrayList<AnimalAbstract> elements;

    //constuctor
    public AnimalLand(){
        this.type = Places.ANIMAL_LAND;
        this.actions = new PlaceActions(this);
        this.elements = new ArrayList<>();
    }
    //TODO: add methods to add and remove animals
    //TODO: set max animals
    //methods
    @Override
    public int getNumElements() {
        return elements.size();
    }    

    public ArrayList<AnimalAbstract> getElements(){
        return this.elements;
    }
}
