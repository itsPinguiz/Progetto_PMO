package model.place.land;

import model.actors.actions.PlaceActions;
import model.item.animal.AnimalAbstract;
import model.place.Places;

public class AnimalChunk extends LandAbstract {
    // small part of an animal land that contains one animal

    // attributes
    private AnimalAbstract animal;
    private AnimalLand land;
    
    // constructor
    AnimalChunk(AnimalAbstract animal, AnimalLand land){
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

    @Override
    public void update() {
        this.animal.update();
        //if the animal is dead, remove it from the land
        if (animal.getHunger() >= 100 || animal.getStatus() <= 0){
            this.animal= null;
        }
    }
}
