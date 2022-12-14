package Land;

import java.util.ArrayList;

import Actors.Actions.PlaceActions;
import Plants.PlantAbstract;

public class LandPlant extends LandAbstract {
    //attibutes of landplant
    ArrayList<PlantAbstract> elements;
    PlaceActions actionLandPlant;

    LandPlant(){
        this.price = BASE_PRICE_LAND;
        this.type = "Land of animal";
        this.actionLandPlant = new PlaceActions(this);
        this.elements = new ArrayList<>();
    }

    private void setPrice(){
        //to do 
    }

    public PlaceActions getPlaceActions(){
        return this.actionLandPlant;
    } 
    public void setPlaceAction(){
        //to do
    }

    @Override
    public String getLandType() {
        return this.type;
    }

    @Override
    public int getNumElements() {
        return this.elements.size();
    }

    @Override
    public float getPrice() {
        this.setPrice();
        return this.price;
    }
    
}
