package Animal;

import Calendar.Calendar;

public class Chicken extends AnimalAbstract{

    //contructor
    Chicken(Calendar c){
       this.life = 100;
       this.hunger = 0;
       this.nProduct = 0;
       this.name = "chicken";
       this.typeProduct = "meat";
       this.c = c;
    }
    //method for changing hunger of chicken
    public void setHunger(){
        //check if life == 0
        this.hunger = (1/this.life)*c.getDay();
    }

    //method for changing life of chicken
    public void setLife(){
        this.life = (1/this.life)*c.getDay();
    }

    @Override
    public int getHunger() {
        return this.hunger;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public String getAnimalType() {       
        return this.typeProduct;
    }

    @Override
    //every time you call this method the number of 
    //product are set to zero
    public int getProducts() {

        int tmp = this.nProduct;
        this.nProduct = 0;        
        return tmp;
    }
    
}
