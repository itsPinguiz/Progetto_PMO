package model.place.land;

import java.util.ArrayList;

import model.actors.actions.PlaceActions;
import model.exceptions.CustomExceptions.InventoryIsFullException;
import model.item.animal.AnimalAbstract;
import model.place.Places;

public class AnimalLand extends LandAbstract {

    private final int MAX_ANIMALS = 30; //max number of animals in the land
    //attributes 
    private ArrayList<AnimalAbstract> elements;

    //constuctor
    public AnimalLand(){
        this.type = Places.ANIMAL_LAND;
        this.actions = new PlaceActions(this);
        this.elements = new ArrayList<AnimalAbstract>();
    }

    //returns the animals in the land
    public ArrayList<AnimalAbstract> getElements(){
        return this.elements;
    }

    //returns the number of animals in the land
    @Override
    public int getNumElements() {
        return elements.size();
    }

    //update the animals in the land
    @Override
    public void update() {
        for (AnimalAbstract animal : this.elements){
            animal.update();
            //if the animal is dead, remove it from the land
            if (animal.getHunger() >= 100 || animal.getStatus() <= 0){
                this.removeAnimal(animal);
            }
        }
    }

    //add an animal to the land
    public void addAnimal(AnimalAbstract animal) throws InventoryIsFullException{
        if (this.isFull() == false){
            this.elements.add(animal);
        }
    }

    //check if the land is full
    private boolean isFull(){
        if (this.elements.size() == MAX_ANIMALS){
            return true;
        }
        else
            return false;
    }

    //remove an animal from the land
    public void removeAnimal(AnimalAbstract animal){
        this.elements.remove(animal);
    }

    //get an animal from the land
    public AnimalAbstract getAnimal(int index){
        return this.elements.get(index);
    }

}
