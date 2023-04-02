package GUI.Custom;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

public class DeselectableButtonGroup extends ButtonGroup {

    private JToggleButton lastSelected = null;

    public void handleClick(JToggleButton button) {
        if (button.equals(lastSelected)) {
            clearSelection();
            lastSelected = null;
        } else {
            lastSelected = button;
        }
    }
}
