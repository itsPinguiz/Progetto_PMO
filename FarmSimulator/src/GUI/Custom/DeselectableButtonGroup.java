package GUI.Custom;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

import Item.Interface.Item;

public class DeselectableButtonGroup extends ButtonGroup {

    private JToggleButton lastSelected = null;

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
