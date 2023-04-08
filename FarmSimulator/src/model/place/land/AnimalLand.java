package model.place.land;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.actors.actions.PlaceActions;
import model.place.Places;

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
                elements =+ 1;
            }
        }
        return elements;
    }

    //update the animals in the land
    @Override
    public void update() {
        this.animalChunks.forEach(animalChunks -> {animalChunks.update();});
    }

}
