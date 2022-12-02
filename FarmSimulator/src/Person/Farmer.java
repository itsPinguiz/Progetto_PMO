public class Farmer implements Person{
    
    private Object actualPlace;

    public void doAction(String command){
        System.out.println(command);
    }

    public Object getPlace(){
        return actualPlace;
    }

    public void setPlace(Object newPlace){
        this.actualPlace = newPlace;
    } 

}
