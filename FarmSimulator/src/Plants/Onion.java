package Plants;

public class Onion extends PlantAbstract {
    
    Onion(){
        this.life = 0;
        this.waterNeed = 0;
        this.product = "Onions";
        this.nProduct = 0;
        //this.type = PlantType.ONION;
    }


    @Override
    public String getPlantType() {
        return getType();
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public int getProduct() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void setLife(int value){
        //to do 
    }
}
