package Place.Land;

import java.util.ArrayList;
import Actors.Actions.PlaceActions;
import Exceptions.CustomExceptions.NoSpaceFoundException;
import Item.Animal.AnimalAbstract;
import Place.Places;

public class AnimalLand extends LandAbstract {
    /*Land that contains different animals*/

    private final int MAX_ANIMALS = 30; //max number of animals in the land
    //attributes 
    private ArrayList<AnimalAbstract> elements;

    //constuctor
    public AnimalLand(){
        this.type = Places.ANIMAL_LAND;
        this.actions = new PlaceActions(this);
        this.elements = new ArrayList<AnimalAbstract>();
    }
    
    //methods

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
        }
    }

    //add an animal to the land
    public void addAnimal(AnimalAbstract animal) throws NoSpaceFoundException{
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
