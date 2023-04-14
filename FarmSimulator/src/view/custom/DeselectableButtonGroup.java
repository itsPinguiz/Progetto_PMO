package view.custom;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

public class DeselectableButtonGroup extends ButtonGroup {

    protected static JToggleButton lastSelected = null;

    public Object handleClick(JToggleButton button, Object item) {
        Object tempItem = null;
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
