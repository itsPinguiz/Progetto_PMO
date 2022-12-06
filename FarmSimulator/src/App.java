
import Calendar.Calendar;


public class App {
    public static void main(String[] args) throws Exception {
        
        Calendar calendar = new Calendar();

        for(int i = 0; i < 280; i++){
            
            System.out.println(calendar.getDay());
            System.out.println(calendar.getSeason());
            System.out.println(calendar.getWeather());
            calendar.inc();
        }

    }
}
