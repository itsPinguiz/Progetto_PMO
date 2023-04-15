package model.item.tools;

import model.item.ItemType;

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
