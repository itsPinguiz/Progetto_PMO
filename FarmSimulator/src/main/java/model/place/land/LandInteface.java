package model.place.land;

import java.util.ArrayList;

public interface LandInteface {
    public int getNumElements();
    public int getPrice();
    public ArrayList<? extends Object> getElements();

    public void update();
}
