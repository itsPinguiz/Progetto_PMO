package Plants;

public class Potato extends PlantAbstract {

    //constructor todo
    Potato(){
        if(isPlantable()){
            this.life = 0;
            this.levelWater = 0;
            this.product = "Potatos";
            this.nProduct = 0;
            this.type = "Potato";
        }else{
            //throw exception
        }
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

    @Override
    public boolean isPlantable() {
        // TODO Auto-generated method stub
        return false;
    }

    private void setLife(){
        //todo
    }
    
}
