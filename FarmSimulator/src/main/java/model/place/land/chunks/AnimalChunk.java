package model.place.land.chunks;

import java.util.HashSet;

import model.actors.actions.PlaceActions;
import model.actors.actions.ActionsManager.Action;
import model.item.animal.AnimalAbstract;
import model.place.Places;
import model.place.land.AnimalLand;
import model.place.land.LandAbstract;

public class AnimalChunk extends LandAbstract {
    // small part of an animal land that contains one animal

    // attributes
    private AnimalAbstract animal;
    private AnimalLand land;
    
    // constructor
    public AnimalChunk(AnimalAbstract animal, AnimalLand land){
        super();
        super.type = Places.ANIMAL_CHUNK;
        this.animal = animal;
        this.land = land;
        this.actions = new PlaceActions(this);
    }

    // method that returns the land
    public AnimalLand getLand(){
        return this.land;
    }

    // method that set the animal
    public void setAnimal(AnimalAbstract animal){
        this.animal = animal;
    }

    // method that returns an animal
    public AnimalAbstract getAnimal(){
        return this.animal;
    }

    // method to remove an animal
    public void removeAnimal(){
        this.animal = null;
    }

    @Override
    public void update() {
        if(!animal.isAlive()){
            getActions().resetActions();
        }
        if(animal != null){
            this.animal.update();
            this.getActions().updateActions( new HashSet<>(){{
                                             add(Action.GET_RESOURCES);
                                             }}, this.getAnimal().areProductsAvailable()?true:false);            
        } 
    }
}
