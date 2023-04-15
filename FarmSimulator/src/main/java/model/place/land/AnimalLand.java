package model.place.land;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.actors.actions.PlaceActions;
import model.actors.actions.ActionsManager.Action;
import model.place.Places;
import model.place.land.chunks.AnimalChunk;

public class AnimalLand extends LandAbstract {

    //attributes 
    private ArrayList<AnimalChunk> animalChunks;
    
    //constuctor
    public AnimalLand(){
        this.type = Places.ANIMAL_LAND;
        this.animalChunks = new ArrayList<>(
            IntStream.range(0, 10).
                      mapToObj(i -> new AnimalChunk(null, this)).
                      collect(Collectors.toList())
        );
        this.actions = new PlaceActions(this);
    }

    //returns the animals in the land
    public ArrayList<AnimalChunk> getElements(){
        return this.animalChunks;
    }

    //returns the number of animals in the land
    @Override
    public int getNumElements() {
        /*
         * Returns the number of land present
         * in the land
         */
        int elements = 0;

        for (AnimalChunk p: this.animalChunks){
            if (p.getAnimal() != null){
                elements += 1;
            }
        }
        return elements;
    }

    //update the animals in the land
    @Override
    public void update() {
        int count = 0;
        for (AnimalChunk chunk: this.animalChunks){
            if (chunk.getAnimal() == null){
                break;
            }
            chunk.update();
            
            if (chunk.getAnimal().areProductsAvailable()){
                count++;
            }
        }
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
