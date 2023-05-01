package model.item.animal;

import java.util.ArrayList;

import model.Constants;
import model.exceptions.CustomExceptions.NoAnimalFoundException;
import model.exceptions.CustomExceptions.NoProductFoundException;
import model.item.ItemType;
import model.item.ItemType.Animals;
import model.item.animal.products.Products;

public class ConcreteAnimal extends AnimalAbstract {
    
    public ConcreteAnimal(Animals type) throws NoAnimalFoundException, NoProductFoundException{
        super();
        switch(type){
            case COW:
                this.type = Animals.COW;
                this.price = Constants.COW_PRICE;
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
                this.price = Constants.PIG_PRICE;
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
                this.price = Constants.GOAT_PRICE;
                this.status = 100;
                this.number = 1;
                this.maxNumber = 7;
                this.typeProduct = new ArrayList<Products>(){
                    {
                        add(new Products(ItemType.productsType.MILK));
                        add(new Products(ItemType.productsType.WOOL));
                        add(new Products(ItemType.productsType.MEAT));
                    }
                };
                break;
            case CHICKEN:
                this.type = Animals.CHICKEN;
                this.price = Constants.CHICKEN_PRICE;
                this.status = 100;
                this.number = 1;
                this.maxNumber = 30;
                this.typeProduct = new ArrayList<Products>(){
                    {
                        add(new Products(ItemType.productsType.EGGS));
                        add(new Products(ItemType.productsType.MEAT));
                    }
                };
                break;
            default:
                throw new NoAnimalFoundException();
            }
    }

}
