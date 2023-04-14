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
import model.place.land.PlantLand;
import view.custom.DeselectableButtonGroup;
import view.View;

import java.awt.*;
import java.awt.event.ActionListener;

public class BarnView {
    // attributes
    Model model;
    Controller controller;
    View view;

    JButton enterMarketButton;
    JPanel insideBarnPanel;
    JPanel barnInventoryPanel;
    JPanel marketPanel;
    JPanel landMarketPanel;
    JButton exitButton;
    DeselectableButtonGroup buttonGroup;

    // constructor
    public BarnView(Model model, Controller controller, View view) {
        this.model = model;
        this.controller = controller;
        this.view = view;
    }

    
    public JPanel createBarnPlace(Place p){
        // get the actual place
        Barn actualPlace = (Barn)this.model.getMap().get(0).get(0);

        // create the panel that will contain the elements
        insideBarnPanel = new JPanel(new BorderLayout());
        insideBarnPanel.setPreferredSize(new Dimension((int)view.getSize().getWidth(), 500));
        marketPanel = new JPanel(new GridLayout(4, 3,20,20));
        marketPanel.setPreferredSize(new Dimension(400, 500)); 
        landMarketPanel = new JPanel(new GridLayout(3, 2,20,20));
        landMarketPanel.setPreferredSize(new Dimension(370, 500)); 
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create a deselectable button group for the toggle buttons
        buttonGroup = new DeselectableButtonGroup();

        exitButton = new JButton("Exit");
        
        // display the items in the market or the market button
        if (actualPlace == p){ // if the player is in the barn
            enterMarketButton = new JButton("Enter");
        
            createBarnInventoryPanel(actualPlace);
            // add the button to enter the market
            enterMarketButton.addActionListener(view.getWorldPanelView().changePlaceListener(this,"createBarnPlace", actualPlace.getMarket(),true,false));
            
            // add the exit button
            exitButton.addActionListener(view.getWorldPanelView().changePlaceListener(view.getWorldPanelView(),"createWorldPanel", null, false, true));
            marketPanel.add(enterMarketButton);
            barnInventoryPanel.add(exitButton);

            // disable the possibility to enter the market if the player is not a landlord
            enterMarketButton.setEnabled(model.getSelectedPerson().toString().equals("Landlord")? true : false);

            tabbedPane.addTab("Market", marketPanel);
            
            insideBarnPanel.add(tabbedPane,BorderLayout.EAST);
        } else{  // if the player is in the market
            Market marketPlace = actualPlace.getMarket();
            createBarnInventoryPanel(actualPlace);

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

            JToggleButton buyAnimalLandButton = new JToggleButton("Animal Land");
            buyAnimalLandButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    //controller.setSelectedItem(new PlantLand()); // TODO: Fix this
                }
            });

            JToggleButton buyPlantLandButton = new JToggleButton("Plant Land");
            buyPlantLandButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    //controller.setSelectedItem(new PlantLand()); // TODO: Fix this
                }
            });    
                    buttonGroup.add(buyAnimalLandButton);
            buttonGroup.add(buyPlantLandButton);
            landMarketPanel.add(buyAnimalLandButton);
            landMarketPanel.add(buyPlantLandButton);
    
            // create the Market tab
            tabbedPane.addTab("Item Market", marketPanel);
            tabbedPane.addTab("Land Market", landMarketPanel);
            insideBarnPanel.add(tabbedPane,BorderLayout.EAST);
        }
    
        // add the elements to the panel
        insideBarnPanel.add(barnInventoryPanel, BorderLayout.WEST);
        insideBarnPanel.revalidate();
        insideBarnPanel.repaint();
        return insideBarnPanel;
    }

    public void createBarnInventoryPanel(Barn actualPlace) {
        barnInventoryPanel = new JPanel(new GridLayout(4, 3,20,20));
        barnInventoryPanel.setBorder(BorderFactory.createTitledBorder("Barn inventory"));
        barnInventoryPanel.setPreferredSize(new Dimension(380, 500));

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
    }
}    