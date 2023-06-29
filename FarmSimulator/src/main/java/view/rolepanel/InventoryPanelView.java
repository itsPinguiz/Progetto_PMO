package view.rolepanel;

import javax.swing.*;

import controller.Controller;
import model.actors.person.Farmer;
import model.inventory.Inventory;
import model.item.Item;
import model.item.ItemType;
import model.item.tools.AbstractTool;
import view.View;
import view.custom.DeselectableButtonGroup;

import java.awt.*;


public class InventoryPanelView {
    Controller controller;
    View view;
    JPanel inventoryPanel;
    JScrollPane scrollableInventoryPanel;
    
    public InventoryPanelView(Controller controller, View view) {
        this.controller = controller;
        this.view = view;
    }

    public JScrollPane createInventoryPanel() {
        /*
        * Create a panel with a grid layout to hold the inventory buttons
        */
        inventoryPanel = new JPanel(new GridLayout(4, 3,20,20));
        inventoryPanel.setPreferredSize(new Dimension(200, 500));

        inventoryPanel.setBorder(BorderFactory.createTitledBorder("Inventory"));

        Inventory inventory = ((Farmer) (controller.getSelectedPerson())).getInventory();

        // Create a deselectable button group for the toggle buttons
        DeselectableButtonGroup buttonGroup = new DeselectableButtonGroup();

        for (Item item : inventory.getItemInventory()) {
            // Create a JToggleButton instead of a JButton
            JToggleButton toggleButton = new JToggleButton((item.getType() instanceof ItemType.Tools)? "<html>" + item.getType().toString() +
                                                                                                       "<br>" + ((AbstractTool)(item)).getMaterial().toString() +
                                                                                                       "<br>" + ((AbstractTool)(item)).getToolStatus().toString() +
                                                                                                       "<html>":
                                                                                                       "<html>" + item.getType().toString() +
                                                                                                       "<br>" + item.getNumber() +
                                                                                                       "<html>");
            toggleButton.addActionListener(view.toggleButtonListener(buttonGroup, item, toggleButton));
            toggleButton.setSelected(controller.getSelectedItem() != null && item == controller.getSelectedItem()? true : false);
            // Add the toggle button to the button group and the panel
            buttonGroup.add(toggleButton);
            inventoryPanel.add(toggleButton);
        }

        // Wrap the inventoryPanel in a JScrollPane
        scrollableInventoryPanel = new JScrollPane(inventoryPanel);
        return scrollableInventoryPanel;
    }

    public JScrollPane getInventoryPanel() {
        return scrollableInventoryPanel;
    }
}
