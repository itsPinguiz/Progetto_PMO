package Plants;

public class Potato extends PlantAbstract {
    Potato(){
        this.life = 0;
        this.waterNeed = 0;
        this.product = "Potatos";
        this.nProduct = 0;
        //this.type = PlantType.POTATO;
    }

    @Override
    public String getPlantType() {
        return this.type;
    }

    @Override
    public int getLife() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getProduct() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void setLife(int value){
        //todo
    }
    
}
