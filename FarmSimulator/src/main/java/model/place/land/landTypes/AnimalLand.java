package model.place.land.landTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.actors.actions.ActionsManager.Action;
import model.actors.actions.placeActions.PlaceActions;
import model.place.Places;
import model.place.land.LandAbstract;
import model.place.land.chunks.AnimalChunk;

public class AnimalLand extends LandAbstract {

    //attributes 
    private ArrayList<AnimalChunk> animalChunks;
    
    //constuctor
    public AnimalLand(){
        super();
        this.type = Places.ANIMAL_LAND;
        this.animalChunks = new ArrayList<>(
            IntStream.range(0, 10)
                      .mapToObj(i -> new AnimalChunk(null, this))
                      .collect(Collectors.toList())
        );
        this.actions = new PlaceActions(this);
    }

    /**
     * Returns the animal chunks in the land.
     *
     * @return the animal chunks in the land
     */
    public ArrayList<AnimalChunk> getElements(){
        return this.animalChunks;
    }

    /**
     * returns the number of animals in the land
     * @return the number of animals in the land
     */
    @Override
    public int getNumElements() {
        
        int elements = (int)this.animalChunks.stream()
                                         .filter(i -> i.getAnimal() != null)
                                         .count();

        return elements;
    }

    //update the animals in the land
    @Override
    public void update() {
        int count = (int) this.animalChunks.stream()
                                           .filter(chunk -> chunk.getAnimal() != null)
                                           .peek(AnimalChunk::update)
                                           .filter(chunk -> chunk.getAnimal().areProductsAvailable())
                                           .count();

        if (count == 0 || this.getNumElements() == 0){
            this.getActions().updateActions(new HashSet<>(){{
                    add(Action.GET_ALL_RESOURCES);
                }}, false);
            } else {
            this.getActions().updateActions(new HashSet<>(){{
                    add(Action.GET_ALL_RESOURCES);
                }}, true);
            }
    }

}
