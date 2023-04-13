package view.custom;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

import model.item.Item;

public class DeselectableButtonGroup extends ButtonGroup {

    protected static JToggleButton lastSelected = null;

    public Item handleClick(JToggleButton button, Item item) {
        Item tempItem = null;
        if (button.equals(lastSelected)) {
            clearSelection();
            tempItem = null;
            lastSelected = null;
        } else {
            lastSelected = button;
            tempItem = item;
        }
        return tempItem;
    }
}
