package Land;

import java.util.ArrayList;

import Actors.Actions.PlaceActions;
import Animal.AnimalAbstract;

public class LandAnimal extends LandAbstract {
     
    
    //attributes of land animal
    private ArrayList<AnimalAbstract> elements;
    private PlaceActions actionLandAnimal;

    //constuctor
    LandAnimal(){
        this.price = BASE_PRICE_LAND;
        this.type = "Land of animal";
        this.actionLandAnimal = new PlaceActions(this);
        this.elements = new ArrayList<>();
    }

    private void setPrice(){
        /*
         * change price proportonally for how many animal are in a land
        */
        //to do
    }

    public PlaceActions getPlaceActions(){
        return this.actionLandAnimal;
    }

    @Override
    public String getLandType() {
        return this.type;
    }

    @Override
    public int getNumElements() {
        return elements.size();
    }

    @Override
    public float getPrice() {
        this.setPrice();
        return this.price;
    }
    
}
