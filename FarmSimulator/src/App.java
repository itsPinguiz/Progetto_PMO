import Calendar.Calendar;
import Calendar.Calendar.Weather;

public class App {
    public static void main(String[] args) throws Exception {
        
       Calendar calendar = new Calendar();

        for(int i = 0; i < 36; i++){
            calendar.inc();
            System.out.println("----------------");
            System.out.println("Day: " + calendar.getDay());
            System.out.println("Season: " + calendar.getSeason());
            System.out.println("Weather: " + calendar.getWeather());
            
        }

        if(calendar.getWeather() == Weather.CLOUDY){
            System.out.println("\nIt's cloudy!\n");
        }
        else if(calendar.getWeather() == Weather.RAINY){
            System.out.println("\nIt's rainy!\n");
        }
        else if(calendar.getWeather() == Weather.SUNNY){
            System.out.println("\nIt's sunny!\n");
        }
        else if(calendar.getWeather() == Weather.CLOUDY){
            System.out.println("\nIt's cloudy!\n");
        }
    }
}
