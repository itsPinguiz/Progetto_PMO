package view.worldpanel;

import javax.swing.*;

import controller.Controller;
import model.Model;
import model.actors.person.Landlord;
import model.item.Item;
import model.item.ItemType;
import model.place.Place;
import model.place.barn.Barn;
import model.place.barn.market.Market;
import view.custom.DeselectableButtonGroup;
import view.View;

import java.awt.*;

public class BarnView {
    Model model;
    Controller controller;
    View view;

    private JButton enterMarketButton;

    public BarnView(Model model, Controller controller, View view) {
        this.model = model;
        this.controller = controller;
        this.view = view;
    }

    
    public JPanel createBarnPlace(Place p){
        // get the actual place
        Barn actualPlace = (Barn)this.model.getMap().get(0).get(0);

        // create the panel that will contain the elements
        JPanel insideBarnPanel = new JPanel(new GridLayout(1, 2));
        JPanel marketPanel = new JPanel(new GridLayout(4, 3,20,20));
        JPanel barnInventoryPanel = new JPanel(new GridLayout(4, 6,20,20));

        JButton exitButton;

        // Create a deselectable button group for the toggle buttons
        DeselectableButtonGroup buttonGroup = new DeselectableButtonGroup();

        insideBarnPanel.setPreferredSize(new Dimension(800, 500));

        marketPanel.setBorder(BorderFactory.createTitledBorder("Inside Market"));
        barnInventoryPanel.setBorder(BorderFactory.createTitledBorder("Barn inventory"));

        // display the items in the barn inventory
        if (actualPlace.getBarnInventory() != null){
        for(Item item : actualPlace.getBarnInventory().getInventory()){
            JToggleButton toggleButton = new JToggleButton((item.getType() instanceof ItemType.Tools)? "<html>" + item.getType().toString() + "<br>" + item.getStatus() +  "<html>":
                                                                                                    "<html>" + item.getType().toString() + "<br>" + item.getNumber() +  "<html>");
            toggleButton.addActionListener(view.toggleButtonListener(buttonGroup, item, toggleButton));
            // add the button to the button group
            buttonGroup.add(toggleButton);
            toggleButton.setSelected(controller.getSelectedItem() != null && item == controller.getSelectedItem()? true : false);
            barnInventoryPanel.add(toggleButton);
        }
        }    

        // display the items in the market or the market button
        if (actualPlace == p){ // if the player is in the barn
        enterMarketButton = new JButton("Market");
        exitButton = new JButton("Exit");

        // add the button to enter the market
        enterMarketButton.addActionListener(view.getWorldPanelView().changePlaceListener(this,"createBarnPlace", actualPlace.getMarket(),true,false));
        
        // add the exit button
        exitButton.addActionListener(view.getWorldPanelView().changePlaceListener(view.getWorldPanelView(),"createWorldPanel", null, false, true));
        marketPanel.add(enterMarketButton);
        barnInventoryPanel.add(exitButton);
        // disable the possibility to enter the market if the player is not a landlord
        enterMarketButton.setEnabled(model.getSelectedPerson().toString().equals("Landlord")? true : false);
        } else{ // if the player is in the market
        Market marketPlace = actualPlace.getMarket();
        exitButton = new JButton("Exit");

        // create buttons for shop items
        if (marketPlace.getItemShop() != null){
            for(Item item : marketPlace.getItemShop().getInventory()){
            JToggleButton toggleButton = new JToggleButton("<html>" + item.getType().toString() 
                                                            + "<br> $" + item.getPrice() +  "<html>"); 
            toggleButton.addActionListener(view.toggleButtonListener(buttonGroup, item, toggleButton));
            // disable the button if the player doesn't have enough money
            if (item.getPrice() > ((Landlord)(model.getSelectedPerson())).getBalance()) {
            toggleButton.setEnabled(false);
            } else{
                toggleButton.setEnabled(true);
            }
            // add the button to the button group
            buttonGroup.add(toggleButton);
            marketPanel.add(toggleButton);
            }
        }   
        // add the exit button
        exitButton.addActionListener(view.getWorldPanelView().changePlaceListener(this,"createBarnPlace", model.getMap().get(0).get(0), true, true));
        marketPanel.add(exitButton);
        }

        // add the elements to the panel
        insideBarnPanel.add(barnInventoryPanel);
        insideBarnPanel.add(marketPanel);
        return insideBarnPanel;
    }

}
