package Plants;

import Plants.enums.PlantType;

public class Carrot extends PlantAbstract {


    Carrot(){
        this.life = 0;
        this.waterNeed = 0;
        this.product = "Carrots";
        this.nProduct = 0;
        //this.type = PlantType.CARROT;
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
    public void setLife(int value) {
        // TODO Auto-generated method stub
        
    }    
}
