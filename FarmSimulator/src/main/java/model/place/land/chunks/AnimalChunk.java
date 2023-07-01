package model.place.land.chunks;

import java.util.HashSet;

import model.actors.actions.ActionsManager.Action;
import model.actors.actions.placeActions.PlaceActions;
import model.item.animal.AnimalAbstract;
import model.place.Places;
import model.place.land.landTypes.AnimalLand;

public class AnimalChunk extends Chunk {
    // small part of an animal land that contains one animal

    // attributes
    private AnimalAbstract animal;
    private AnimalLand land;
    
    /**
     * Constructs a new AnimalChunk object with the specified animal and land.
     *
     * @param animal the animal in the chunk
     * @param land   the land associated with the chunk
     */
    public AnimalChunk(AnimalAbstract animal, AnimalLand land){
        super();
        super.type = Places.ANIMAL_CHUNK;
        this.animal = animal;
        this.land = land;
        this.actions = new PlaceActions(this);
    }

    /**
     * Returns the land associated with the chunk.
     *
     * @return the land associated with the chunk
     */
    public AnimalLand getLand(){
        return this.land;
    }

    /**
     * Sets the animal in the chunk.
     *
     * @param animal the animal to set
     */
    public void setAnimal(AnimalAbstract animal){
        this.animal = animal;
    }

    /**
     * Returns the animal in the chunk.
     *
     * @return the animal in the chunk
     */
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

    @Override
    public void resetActions(){
        this.getActions().resetActions();
        this.getActions().updateActions( new HashSet<>(){{
                                            add(Action.ADD_ANIMAL);
                                            }}, true);
    }
}
