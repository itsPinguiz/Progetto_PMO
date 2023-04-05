package Item.Tools;

// Import
import Item.ItemType;
import Item.Tools.Interface.AbstractTool;

// Fertilizer Class
public class Fertilizer extends AbstractTool{

    // Constructor
    public Fertilizer(){
        super();
        super.type = ItemType.Tools.FERTILIZER;
        super.status = 100;
        super.price = 10;
        super.number = 1;
    }
}
