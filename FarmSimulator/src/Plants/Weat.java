package Plants;

import Plants.enums.PlantType;

public class Weat extends PlantAbstract {

    Weat(){
        this.life = 0;
        this.waterNeed = 0;
        this.product = "Weats";
        this.nProduct = 0;
        //this.type = PlantType.WEAT;
    }


    @Override
    public String getPlantType() {
        return this.type;
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
