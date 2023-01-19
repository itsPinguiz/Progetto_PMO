package Plants;

public class Carrot extends PlantAbstract {


    Carrot(){
        if(isPlantable()){
            this.life = 0;
            this.waterNeed = 0;
            this.product = "Carrots";
            this.nProduct = 0;
            this.type = PlantType.CARROT;
            
        }else{
            //throw exception
        }
    }

    @Override
    public String getPlantType() {
        // TODO Auto-generated method stub
        return null;
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
    //check if there are seeds in barn
        return false;
    }
    
}
