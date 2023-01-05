package Plants;

public class Onion extends PlantAbstract {
    
    Onion(){
        if(isPlantable()){
            this.life = 0;
            this.levelWater = 0;
            this.product = "Onions";
            this.nProduct = 0;
            this.type = "Onion";
        }else{
            //throw exception
        }
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

    @Override
    public boolean isPlantable() {
        // check if there are seeds in barn
        return false;
    }
    
    private void setLife(){
        //to do 
    }
}
