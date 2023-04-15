package model.item.animal;

import java.io.Serializable;

import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.item.ItemType.Animals;

public class AnimalFactory implements Serializable{
    public AnimalAbstract createCow() throws NoAnimalFoundException,NoProductFoundException{
        return new ConcreteAnimal(Animals.COW);
    }
    public AnimalAbstract createPig() throws NoAnimalFoundException,NoProductFoundException{
        return new ConcreteAnimal(Animals.PIG);
    }
    public AnimalAbstract createGoat() throws NoAnimalFoundException,NoProductFoundException{
        return new ConcreteAnimal(Animals.GOAT);
    }
    public AnimalAbstract createChicken() throws NoAnimalFoundException,NoProductFoundException {
        return new ConcreteAnimal(Animals.CHICKEN);
    }
}
