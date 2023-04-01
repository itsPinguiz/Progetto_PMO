package Item.Animal;

import Item.ItemType.Animals;

public class AnimalFactory {
    public AnimalAbstract createCow(){
        return new ConcreteAnimal(Animals.COW);
    }
    public AnimalAbstract createPig(){
        return new ConcreteAnimal(Animals.PIG);
    }
    public AnimalAbstract createGoat(){
        return new ConcreteAnimal(Animals.GOAT);
    }
    public AnimalAbstract createChicken(){
        return new ConcreteAnimal(Animals.CHICKEN);
    }
}
