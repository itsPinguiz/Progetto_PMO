package model;

/**
 * Class that contains all the constants of the game
 */
public class Constants {
    /**
     * Inventory constants
     */
    public final static int INVENTORY_MAX = 10;
    public final static int BARN_INVENTORY_MAX = 40;
    public final static int MARKET_SHOP_MAX = 10;

    /**
     * Plant constants
     */
    public final static int MAX_GROWTH = 100;
    public final static int GROWTH_RATE = 1;

    public final static int PLANT_FERTILIZATION_DEACRESE = -5;
    public final static int PLANT_WATER_DEACRESE = -5;

    public final static int CHUNK_FERTILIZATION_DEACRESE = -1;
    public final static int CHUNK_WATER_DEACRESE = -1;

    public final static int PLANT_DAYS_WITHOUT_WATER = 7;

    public final static int HARVEST_MULTIPLIER = 3;

    public final static int WHEAT_PRICE = 10;
    public final static int ONION_PRICE = 15;
    public final static int POTATO_PRICE = 20;
    public final static int CARROT_PRICE = 25;

    public final static int WATERING_INDEX = 25;
    public final static int FERTILIZATION_INDEX = 10;

    public final static int FERTILIZATION_MAX = 100;
    public final static int WATERING_MAX = 100;

    /**
     * Animal constants
     */
    public final static int COW_PRICE = 200;
    public final static int PIG_PRICE = 60;
    public final static int GOAT_PRICE = 100;
    public final static int CHICKEN_PRICE = 20;

    public final static int FEED_INDEX = 10;
    public final static int GIVE_WATER_INDEX = 10;

    public final static int FEED_MAX = 10;
    public final static int GIVE_WATER_MAX = 10;

    public final static int HUNGER_INCREASE = 2;
    public final static int THIRST_INCREASE = 3;

    public final static int NEGATIVE_PRODUCTIVITY = 2;
    public final static int LOW_PRODUCTIVITY = 0;
    public final static int MEDIUM_PRODUCTIVITY = 1;
    public final static int HIGH_PRODUCTIVITY = 2;

    /**
     * Tool constants
     */ 
    public final static int IRON_MODIFIER = 7;
    public final static int WOOD_MODIFIER = 5;

    /**
     * Tool prices
     */

    /**
     * Iron Tool prices
     */
    public final static int IRON_HOE_PRICE = 24;
    public final static int IRON_SCISSORS_PRICE = 20;
    public final static int IRON_SICKLE_PRICE = 30;
    public final static int IRON_WATERINGCAN_PRICE = 10;
    public final static int IRON_FERTILIZER_PRICE = 8;

    /**
     * Wood Tool prices
     */
    public final static int WOOD_HOE_PRICE = 12;
    public final static int WOOD_SCISSORS_PRICE = 10;
    public final static int WOOD_SICKLE_PRICE = 15;
    public final static int WOOD_WATERINGCAN_PRICE = 5;
    public final static int WOOD_FERTILIZER_PRICE = 4;

    /**
     * Fertilizer price
     */
    public final static int FERTILIZER_PRICE = 10;
    
    /**
     * Products prices
     */
    public final static int MEAT_PRICE = 20;
    public final static int MILK_PRICE = 4;
    public final static int EGGS_PRICE = 2;
    public final static int WOOL_PRICE = 15;

    /**
     * Land prices
     */
    public final static int BASE_LAND_PRICE = 50;
    public final static int LAND_SELL_PRICE = 25;

    /**
     * Max Item Stack
     */
    public final static int STACK_MAX = 64;
}
