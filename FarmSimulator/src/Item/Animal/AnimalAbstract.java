/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Animal;

import java.util.ArrayList;
import Calendar.Calendar;
import Item.Interface.Item;
import Item.Products.Products;

/*****************
 * ANIMAL ABSTRACT
 ****************/
public abstract class AnimalAbstract extends Item implements AnimalInterface {
    //fields
    protected int hunger;
    protected ArrayList<Products> typeProduct;
    protected Calendar c = Calendar.getInstance();
    protected int creationDay;
    protected int lastUpdatedDay;

    public AnimalAbstract() {
        this.hunger = 0;
        this.creationDay = c.getDay();
        this.lastUpdatedDay = this.creationDay;
	}
    
    //method for changing hunger
    private void setHunger(){
        int dayPassed = c.getDay() - this.creationDay;
        this.hunger = dayPassed * 2;
    }

    //method for changing life 
    public void setLife(){
        super.status = (1/super.status)*c.getDay();
    }

    //method for getting hunger
    public int getHunger() {
        return this.hunger;
    }

    //method for getting the products
    public ArrayList<Products> getProducts() {
        return this.typeProduct;
    }

    private void updateLife() {
        int daysPassed = c.getDay() - lastUpdatedDay; // calcola i giorni passati dall'ultimo aggiornamento
        if (daysPassed > 0) { // se sono passati almeno 1 giorno
            super.status = (1/super.status) * Math.pow(2, daysPassed); // diminuisci la vita di uno per ogni giorno trascorso
            this.lastUpdatedDay = c.getDay(); // aggiorna il campo "lastUpdatedDay"
        }
    }

    private void updateProducts(){
        if (this.hunger > 30){
            for (int i = 0; i < this.typeProduct.size(); i++){
                this.typeProduct.get(i).setNumber(this.typeProduct.get(i).getNumber() - 2);
            }
        }
        else if (this.hunger > 60){
            for (int i = 0; i < this.typeProduct.size(); i++){
                this.typeProduct.get(i).setNumber(this.typeProduct.get(i).getNumber() - 4);
            }
        }
        else if (this.hunger > 90){
            for (int i = 0; i < this.typeProduct.size(); i++){
                this.typeProduct.get(i).setNumber(0);
            }
        }
    }

    //method for updating the animal
    public void update() {
        this.updateLife();
        this.setHunger();
        this.updateProducts();
    }


}
