package model.item.animal;

import java.io.Serializable;

import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.item.ItemType.Animals;

/**
 * A factory class for creating various types of animals. It implements Serializable, 
 * allowing instances of this class to be serialized to a byte stream.
 */
public class AnimalFactory implements Serializable{
    /**
     * Creates a new cow.
     *
     * @return A concrete instance of an Animal representing a cow.
     * @throws NoAnimalFoundException If the type of animal is not found.
     * @throws NoProductFoundException If the product produced by the animal is not found.
     */
    public AnimalAbstract createCow() throws NoAnimalFoundException,NoProductFoundException{
        return new ConcreteAnimal(Animals.COW);
    }

    /**
     * Creates a new pig.
     *
     * @return A concrete instance of an Animal representing a pig.
     * @throws NoAnimalFoundException If the type of animal is not found.
     * @throws NoProductFoundException If the product produced by the animal is not found.
     */
    public AnimalAbstract createPig() throws NoAnimalFoundException,NoProductFoundException{
        return new ConcreteAnimal(Animals.PIG);
    }

    /**
     * Creates a new goat.
     *
     * @return A concrete instance of an Animal representing a goat.
     * @throws NoAnimalFoundException If the type of animal is not found.
     * @throws NoProductFoundException If the product produced by the animal is not found.
     */
    public AnimalAbstract createGoat() throws NoAnimalFoundException,NoProductFoundException{
        return new ConcreteAnimal(Animals.GOAT);
    }
    
    /**
     * Creates a new chicken.
     *
     * @return A concrete instance of an Animal representing a chicken.
     * @throws NoAnimalFoundException If the type of animal is not found.
     * @throws NoProductFoundException If the product produced by the animal is not found.
     */
    public AnimalAbstract createChicken() throws NoAnimalFoundException,NoProductFoundException {
        return new ConcreteAnimal(Animals.CHICKEN);
    }
}
