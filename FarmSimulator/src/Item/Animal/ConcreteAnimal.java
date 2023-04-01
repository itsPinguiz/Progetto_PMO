package Item.Animal;

import java.util.ArrayList;

import Exceptions.CustomExceptions.NoAnimalFoundException;
import Item.ItemType;
import Item.ItemType.Animals;
import Item.Products.Products;

public class ConcreteAnimal extends AnimalAbstract {
    
    public ConcreteAnimal(Animals type){
        super();
        try {
            switch(type){
                case COW:
                    this.type = Animals.COW;
                    this.price = 200;
                    this.status = 100;
                    this.number = 1;
                    this.maxNumber = 5;
                    this.typeProduct = new ArrayList<Products>(){
                        {
                            add(new Products(ItemType.productsType.MILK));
                            add(new Products(ItemType.productsType.MEAT));
                        }
                    };
                    break;
                case PIG:
                    this.type = Animals.PIG;
                    this.price = 60;
                    this.status = 100;
                    this.number = 1;
                    this.maxNumber = 10;
                    this.typeProduct = new ArrayList<Products>(){
                        {
                            add(new Products(ItemType.productsType.MEAT));
                        }
                    };
                    break;
                case GOAT:
                    this.type = Animals.GOAT;
                    this.price = 100;
                    this.status = 100;
                    this.number = 1;
                    this.maxNumber = 7;
                    this.typeProduct = new ArrayList<Products>(){
                        {
                            add(new Products(ItemType.productsType.MILK));
                            add(new Products(ItemType.productsType.MEAT));
                            add(new Products(ItemType.productsType.WOOL));
                        }
                    };
                    break;
                case CHICKEN:
                    this.type = Animals.CHICKEN;
                    this.price = 20;
                    this.status = 100;
                    this.number = 1;
                    this.maxNumber = 30;
                    this.typeProduct = new ArrayList<Products>(){
                        {
                            add(new Products(ItemType.productsType.MEAT));
                            add(new Products(ItemType.productsType.EGGS));
                        }
                    };
                    break;
                default:
                    throw new NoAnimalFoundException();
            }
        } catch (NoAnimalFoundException e) {
            System.out.println(e);
        }
    }

}