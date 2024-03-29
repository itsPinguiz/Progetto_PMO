package view.worldpanel;

import javax.swing.*;

import controller.Controller;
import model.Constants;
import model.actors.person.Landlord;
import model.actors.person.PersonAbstract.Role;
import model.item.Item;
import model.item.ItemType;
import model.item.tools.AbstractTool;
import model.place.Place;
import model.place.barn.Barn;
import model.place.barn.market.Market;
import model.place.land.LandAbstract;
import model.place.land.landTypes.AnimalLand;
import model.place.land.landTypes.PlantLand;
import view.View;
import view.custom.DeselectableButtonGroup;

import java.awt.*;

public class BarnView {
    // attributes
    Controller controller;
    View view;
    boolean displayMarket;

    JButton enterMarketButton;
    JPanel insideBarnPanel;
    JPanel barnInventoryPanel;
    JPanel marketPanel;
    JPanel landMarketPanel;
    JButton exitButton;
    DeselectableButtonGroup buttonGroup;
    JTabbedPane tabbedPane;

    // constructor
    public BarnView(Controller controller, View view) {
        this.controller = controller;
        this.view = view;
        this.displayMarket = true;
    }

    
    public JPanel createBarnPlace(Place p){
        // get the actual place
        Barn barnPlace = this.controller.getBarn();

        // create the panel that will contain the elements
        insideBarnPanel = new JPanel(new BorderLayout());
        insideBarnPanel.setPreferredSize(new Dimension((int)view.getSize().getWidth(), 500));
        marketPanel = new JPanel(new GridLayout(4, 3,20,20));
        marketPanel.setPreferredSize(new Dimension(400, 500)); 
        
        tabbedPane = new JTabbedPane();

        // Create a deselectable button group for the toggle buttons
        buttonGroup = new DeselectableButtonGroup();

        exitButton = new JButton("Exit");
        
        // display the items in the market or the market button
        if (barnPlace.getType() == p.getType()){ // if the player is in the barn
            enterMarketButton = new JButton("Enter");
        
            createBarnInventoryPanel(barnPlace);
            // add the button to enter the market
            enterMarketButton.addActionListener(view.getWorldPanelView().changePlaceListener(this,"createBarnPlace", barnPlace.getMarket(),true,false));
            
            // add the exit button
            exitButton.addActionListener(view.getWorldPanelView().changePlaceListener(view.getWorldPanelView(),"createWorldPanel", null, false, true));
            marketPanel.add(enterMarketButton);
            barnInventoryPanel.add(exitButton);

            // disable the possibility to enter the market if the player is not a landlord
            enterMarketButton.setEnabled(controller.getSelectedPerson().getRole() == Role.LANDLORD? true : false);

            if (displayMarket){
                tabbedPane.addTab("Market",marketPanel);
            }
            insideBarnPanel.add(tabbedPane,BorderLayout.EAST);
        } else{  // if the player is in the market
            Market marketPlace = barnPlace.getMarket();
            createBarnInventoryPanel(barnPlace);

            // create buttons for shop items
            if (marketPlace.getItemShop() != null){
                for(Item item : marketPlace.getItemShop().getItemInventory()){
                    JToggleButton toggleButton = new JToggleButton((item instanceof AbstractTool)?
                                                                   "<html>" + item.getType().toString() +
                                                                   "<br> " + ((AbstractTool)item).getMaterial().toString()+ 
                                                                   "<br> $" + item.getPrice()+  
                                                                   "<html>":
                                                                   "<html>" + item.getType().toString() +
                                                                   "<br> $" + item.getPrice()+  
                                                                   "<html>"); 
                    toggleButton.addActionListener(view.toggleButtonListener(buttonGroup, item, toggleButton));
                    // disable the button if the player doesn't have enough money
                    if (item.getPrice() > ((Landlord)(controller.getSelectedPerson())).getBalance()) {
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
            exitButton.addActionListener(view.getWorldPanelView().changePlaceListener(this,"createBarnPlace", controller.getBarn(), true, true));
            marketPanel.add(exitButton);


            // create the Market tab
            tabbedPane.addTab("Item Market", marketPanel);
            createLandMarketPanel();
    
            insideBarnPanel.add(tabbedPane,BorderLayout.EAST);
        }
    
        // add the elements to the panel
        insideBarnPanel.add(barnInventoryPanel, BorderLayout.WEST);
        insideBarnPanel.revalidate();
        insideBarnPanel.repaint();
        return insideBarnPanel;
    }

    private void createBarnInventoryPanel(Barn actualPlace) {
        barnInventoryPanel = new JPanel(new GridLayout(4, 3,20,20));
        barnInventoryPanel.setBorder(BorderFactory.createTitledBorder("Barn inventory"));
        barnInventoryPanel.setPreferredSize(new Dimension(380, 500));

        // display the items in the barn inventory
        if (actualPlace.getBarnInventory() != null){
            for(Item item : actualPlace.getBarnInventory().getItemInventory()){
                JToggleButton toggleButton = new JToggleButton((item.getType() instanceof ItemType.Tools)? "<html>" + item.getType().toString() +
                                                                                                        "<br> " + ((AbstractTool)item).getMaterial().toString()+
                                                                                                        "<br>" + item.getStatus() +
                                                                                                        "<html>":
                                                                                                        "<html>" + item.getType().toString() +
                                                                                                        "<br>" + item.getNumber() +
                                                                                                        "<html>");
                toggleButton.addActionListener(view.toggleButtonListener(buttonGroup, item, toggleButton));
                // add the button to the button group
                buttonGroup.add(toggleButton);
                toggleButton.setSelected(controller.getSelectedItem() != null && item == controller.getSelectedItem()? true : false);
                barnInventoryPanel.add(toggleButton);
            }
        }
    }

    public void setDisplayMarket(boolean set){
        this.displayMarket = set;
    }

    private void createLandMarketPanel() {
        landMarketPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        JToggleButton buyAnimalLand = new JToggleButton("<html> Buy Animal Land" + 
                                                        "<br> $" + Constants.BASE_LAND_PRICE + 
                                                        "<html>");
        JToggleButton buyPlantLand = new JToggleButton("<html> Buy Plant Land" + 
                                                       "<br> $" + Constants.BASE_LAND_PRICE + 
                                                       "<html>");
        buttonGroup.add(buyPlantLand);
        buttonGroup.add(buyAnimalLand);
    
        // create a new JPanel for the buy buttons
        JPanel buyLandPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        buyLandPanel.setBorder(BorderFactory.createTitledBorder("Buy Lands"));
        buyAnimalLand.setPreferredSize(new Dimension(170,100));

        buyPlantLand.addActionListener(view.toggleButtonListener(buttonGroup, new PlantLand(), buyPlantLand));
        buyAnimalLand.addActionListener(view.toggleButtonListener(buttonGroup, new AnimalLand(), buyAnimalLand));

        buyAnimalLand.setEnabled(((Landlord)(controller.getSelectedPerson())).getBalance()>= Constants.BASE_LAND_PRICE);
        buyPlantLand.setEnabled(((Landlord)(controller.getSelectedPerson())).getBalance()>= Constants.BASE_LAND_PRICE);
        
        buyLandPanel.add(buyAnimalLand);
        buyLandPanel.add(buyPlantLand);
        landMarketPanel.add(buyLandPanel, gbc);
    
        // create the sell land buttons
        JPanel sellLandMarketPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        int landNumber = 1;

        for (LandAbstract land : (this.controller.getLands())) {
            JToggleButton toggleButton = new JToggleButton("<html> " + landNumber + ". " + land.getType().toString() +
                                                           "<br> $" + (land).getPrice() + 
                                                           "<html>");
            toggleButton.addActionListener(view.toggleButtonListener(buttonGroup, land, toggleButton));
            buttonGroup.add(toggleButton);
            sellLandMarketPanel.add(toggleButton);
            landNumber++;
        }
        sellLandMarketPanel.setBorder(BorderFactory.createTitledBorder("Sell Lands"));
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        landMarketPanel.add(sellLandMarketPanel, gbc);
    
        tabbedPane.addTab("Land Market", landMarketPanel);
    }
    
    public JPanel[] getMarketPanels(){
        return new JPanel[] {this.landMarketPanel,this.marketPanel};
    }

    public JPanel getBarnPanel(){
        return createBarnPlace(controller.getSelectedPerson().getPlace());
    }

    public JPanel getBarnInventoryPanel(){
        return this.barnInventoryPanel;
    }
}    