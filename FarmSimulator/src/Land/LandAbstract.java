package Land;

public abstract class LandAbstract implements LandInteface {
    static final float BASE_PRICE_LAND = 50; //initial price of a land 
    protected boolean sellable; // decides whether a land can be sold or not
    protected float price;
    protected String type;
    
}
