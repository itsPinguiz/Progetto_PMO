package Plants;

public class Weat extends PlantAbstract {

    Weat(){
        if(isPlantable()){
            this.life = 0;
            this.levelWater = 0;
            this.product = "Weats";
            this.nProduct = 0;
            this.type = "Weat";
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
        //to do
    }
    
}
