package Place.Barn.Market;

import java.util.ArrayList;

import Item.Interface.Item;

public interface MarketInt {
    ArrayList<Item> getItem();
    void removeItem(int itemIndex);
}
