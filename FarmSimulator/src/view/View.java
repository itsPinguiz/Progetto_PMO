package view;

import javax.swing.*;

import controller.Controller;
import model.Model;
import model.actors.person.Farmer;
import model.exceptions.CustomExceptions.ActionNotAvailableException;
import model.exceptions.CustomExceptions.PlaceNotAvailableException;
import model.item.Item;
import model.place.Place;
import model.place.Places;
import model.place.barn.Barn;
import view.custom.DeselectableButtonGroup;
import view.rolepanel.RolePanelView;
import view.worldpanel.BarnView;
import view.worldpanel.LandView;
import view.worldpanel.WorldPanelView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame{
  // constants
  final int MAX_HEIGHT = 600;
  final int MAX_WIDTH = 800;

  // attributes  
  private Model model;
  private Controller controller;

  private RolePanelView rolePanelView;
  private WorldPanelView worldPanelView;
  private LandView landView;
  private BarnView barnView;
  
 

  // constructor
  public View(Model model,Controller controller){
    // setup main frame
    setTitle("Farming Simulator");
    setSize(MAX_WIDTH, MAX_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    
    this.model = model;
    this.controller = controller;
  }

  // update MVC
  public void updateMVC(Controller c, Model m){
    this.controller = c;
    this.model = m;
    try {
      this.rolePanelView = new RolePanelView(this.model, this.controller,this);
      this.worldPanelView = new WorldPanelView(this.model, this.controller,this);
      this.landView = new LandView(this.model,this);
      this.barnView = new BarnView(this.model, this.controller,this);
    } catch (ActionNotAvailableException e) {
      exceptionPopup(e.getCause().getMessage());
    }

    // setup main layout
    Container contentPane = getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

    // add panels to main layout
    try {
      contentPane.add(rolePanelView.createRolePanel());
      contentPane.add(worldPanelView.createWorldPanel());
    } catch (ActionNotAvailableException e) {
      e.printStackTrace();
    }
  }

  public RolePanelView getRolePanelView() {
    return rolePanelView;
  }

  public WorldPanelView getWorldPanelView() {
    return worldPanelView;
  }

  public LandView getLandView() {
    return landView;
  }

  public BarnView getBarnView() {
    return barnView;
  }

  public void updateActualPanel(JPanel mainPanel, JPanel newPanel) throws ActionNotAvailableException{
    /*
     * Update the main panel with the new panel
     */
    // remove the old panel and add the new one
    mainPanel.removeAll();
    mainPanel.add(newPanel);
    mainPanel.revalidate();
    mainPanel.repaint();
    
    // keep the selected item if the player is in the same place
    if (!(controller.getOldPlace() == model.getSelectedPerson().getPlace())){
      controller.setSelectedItem(null);
    } else {
      if (rolePanelView.getShowInventoryButton().isSelected())
        rolePanelView.getShowInventoryButton().getActionListeners()[0].actionPerformed(null);
      // keep item selected and deselct if the item is not in the inventory anymore
      if (controller.getSelectedItem() != null && controller.getSelectedItem().getNumber() == 1){
        if (controller.getOldInventory()!= null && !controller.getOldInventory().getInventory().contains(controller.getSelectedItem())){
          controller.setSelectedItem(null);;
          controller.setOldInventory(null);;
        }
        if (((Farmer)(this.model.getSelectedPerson())).getInventory().getInventory().contains(controller.getSelectedItem())){
            controller.setOldInventory(((Farmer)(this.model.getSelectedPerson())).getInventory());
        } else if (this.model.getSelectedPerson().getPlace().getType() == Places.BARN){
          controller.setOldInventory(((Barn)(this.model.getSelectedPerson().getPlace())).getBarnInventory());
        } 
      }
    }
    
    // enable the inventory button if the player is a farmer
    rolePanelView.getShowInventoryButton().setEnabled(model.getSelectedPerson().toString().equals("Farmer")? true : false);
    // enable the role button if the player is not in the world
    rolePanelView.getRoleMenu().setEnabled(model.getSelectedPerson().getPlace() == null? true : false);

    rolePanelView.updateRolePanelView();

    // Update the panel
    revalidate();
    repaint();
  }

  public ActionListener toggleButtonListener(DeselectableButtonGroup buttonGroup ,Item item, JToggleButton toggleButton){
    /*
     * Method to create an action listener for the toggle buttons
     */
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // if the player is in the same place, keep the selected item
        controller.setSelectedItem(buttonGroup.handleClick(toggleButton,item));
        getRolePanelView().updateActionButtons();
      }};
  }

  public void exceptionPopup(String message) {
    /*
    * Method to show a popup with the error message
    */
    Place place = this.model.getSelectedPerson().getPlace();
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    try {
      this.model.getSelectedPerson().getActions().enter(place);
    } catch (PlaceNotAvailableException e) {
      this.exceptionPopup(e.toString());
    }
  }
}




   
