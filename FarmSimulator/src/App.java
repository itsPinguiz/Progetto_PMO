import Actors.Person.Farmer;
import Market.Market;

public class App {
    public static void main(String[] args) throws Exception {
        Market mercato = new Market();

        for(int i = 0; i < mercato.getItem().size(); i++){
            System.out.println(mercato.getItem().get(i).getType());
        }
    }
}
