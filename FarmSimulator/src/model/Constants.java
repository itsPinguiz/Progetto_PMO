package model;

public enum Constants {

    // animal price
    COW_PRICE(200),
    PIG_PRICE(60),
    GOAT_PRICE(100),
    CHICKEN_PRICE(20),

    // tool price
        // iron tool
        IRON_HOE_PRICE(24),
        IRON_SCISSORS_PRICE(20),
        IRON_SICKLE_PRICE(30),
        IRON_WATERINGCAN_PRICE(10),

        // wood tool
        WOOD_HOE_PRICE(12),
        WOOD_SCISSORS_PRICE(10),
        WOOD_SICKLE_PRICE(15),
        WOOD_WATERINGCAN_PRICE(5),
    
        // fertilizer
        FERTILIZER_PRICE(10),
    
    // product price
    MEAT_PRICE(20),
    MILK_PRICE(4),
    EGGS_PRICE(2),
    WOOL_PRICE(15),

    // land price
    BASE_LAND_PRICE(50),
    SELL_PRICE(25);

    
    
    private final int value;

    private Constants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
