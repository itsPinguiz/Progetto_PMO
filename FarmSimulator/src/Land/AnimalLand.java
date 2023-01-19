package Land;

import java.util.ArrayList;

import Actors.Actions.PlaceActions;
import Animal.AnimalAbstract;
import Land.enums.LandType;

public class AnimalLand extends LandAbstract {
    /*Land that contains different animals*/

    //attributes 
    private ArrayList<AnimalAbstract> elements;
    private PlaceActions actionLandAnimal;

    //constuctor
    AnimalLand(){
        this.type = LandType.ANIMAL;
        this.actionLandAnimal = new PlaceActions(this);
        this.elements = new ArrayList<>();
    }

    //methods
    public PlaceActions getPlaceActions(){
        return this.actionLandAnimal;
    }

    @Override
    public LandType getLandType() {
        return this.type;
    }

    @Override
    public int getNumElements() {
        return elements.size();
    }

    @Override
    public float getPrice() {
        return LandAbstract.SELL_PRICE;
    }
    
}
