
import Calendar.Calendar;
import Market.Market;

public class App {
    public static void main(String[] args) throws Exception {
        Market market = new Market();
        Calendar calendar = new Calendar();

        for(int i = 0; i < market.getItem().size(); i++){
            System.out.println(market.getItem().get(i).getType());
        }

        for(int i = 0; i < 15; i ++){
            calendar.inc();
            market.upgradeItemShop(calendar.getDay());
        }

        System.out.println("\n\n");
        
        for(int i = 0; i < market.getItem().size(); i++){
            System.out.println(market.getItem().get(i).getType());
        }

    }
}

