package Land;

import java.util.ArrayList;

import Actors.Actions.PlaceActions;
import Animal.AnimalAbstract;
import Land.enu.LandType;

public class AnimalLand extends LandAbstract {
    /*Land that contains different animals*/

    //attributes 
    private ArrayList<AnimalAbstract> elements;

    //constuctor
    AnimalLand(){
        this.type = LandType.ANIMAL;
        this.actions = new PlaceActions(this);
        this.elements = new ArrayList<>();
    }

    //methods
    @Override
    public int getNumElements() {
        return elements.size();
    }    
}
