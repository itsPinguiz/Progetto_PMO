package view;

import javax.swing.*;

import controller.Controller;
import model.Model;
import model.actors.person.Farmer;
import model.actors.person.PersonAbstract.Role;
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

/**
 * View class of the MVC pattern, contains all the GUI elements
 */
public class View extends JFrame{
  /**
   * Constants
   */
  final int MAX_HEIGHT = 600;
  final int MAX_WIDTH = 800;

  /**
   * Attributes
   */
  private Controller controller;

  private RolePanelView rolePanelView;
  private WorldPanelView worldPanelView;
  private LandView landView;
  private BarnView barnView;

  /**
   * Constructor
   * @param controller
   */
  public View(Controller controller){
    // setup main frame
    setTitle("Farming Simulator");
    setSize(MAX_WIDTH, MAX_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    this.controller = controller;
  }

  /**
   * Creates all instances of the sub-panels and adds them to the main panel
   */
  public void updateMVC(Controller c, Model m){
    this.controller = c;
    try {
      this.rolePanelView = new RolePanelView(this.controller,this);
      this.worldPanelView = new WorldPanelView(this.controller,this);
      this.landView = new LandView(this.controller,this);
      this.barnView = new BarnView(this.controller,this);

      revalidate();
      repaint();
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
      exceptionPopup(e.getCause().getMessage());
    }
  }

  /**
   * Returns the role panel view
   * @return RolePanelView
   */
  public RolePanelView getRolePanelView() {
    return rolePanelView;
  }

  /**
   * Returns the world panel view
   * @return WorldPanelView
   */
  public WorldPanelView getWorldPanelView() {
    return worldPanelView;
  }

  /**
   * Returns the land view
   * @return LandView
   */
  public LandView getLandView() {
    return landView;
  }

  /**
   * Returns the barn view
   * @return BarnView
   */
  public BarnView getBarnView() {
    return barnView;
  }

  /**
   * Update the main panel with the new panel
   * @param mainPanel 
   * @param newPanel
   * @throws ActionNotAvailableException 
   */
  public void updateActualPanel(JPanel mainPanel, JPanel newPanel) throws ActionNotAvailableException{
    // remove the old panel and add the new one
    mainPanel.removeAll();
    mainPanel.add(newPanel);
    mainPanel.revalidate();
    mainPanel.repaint();
    
    // keep the selected item if the player is in the same place
    if (!(controller.getOldPlace() == controller.getSelectedPerson().getPlace())){
      controller.setSelectedItem(null);
    } else {
      if (rolePanelView.getShowInventoryButton().isSelected())
        rolePanelView.getShowInventoryButton().getActionListeners()[0].actionPerformed(null);
      // keep item selected and deselct if the item is not in the inventory anymore
      if (controller.getSelectedItem() != null && 
          controller.getSelectedItem() instanceof Item &&
          ((Item)controller.getSelectedItem()).getNumber() == 1){
        if (controller.getOldInventory()!= null && 
            !controller.getOldInventory().getInventory().contains(controller.getSelectedItem())){
            controller.setSelectedItem(null);
            controller.setOldInventory(null);
          }
        if (controller.getSelectedPerson().getPlace() != null && 
            controller.getSelectedPerson() instanceof Farmer &&
            ((Farmer)(controller.getSelectedPerson())).getInventory().getInventory().contains(controller.getSelectedItem())){
          controller.setOldInventory(((Farmer)(this.controller.getSelectedPerson())).getInventory());
        } else if (this.controller.getSelectedPerson().getPlace() != null &&
                   this.controller.getSelectedPerson().getPlace().getType() == Places.BARN){
          controller.setOldInventory(((Barn)(this.controller.getSelectedPerson().getPlace())).getBarnInventory());
        } 
      }
    }
    
    // enable the inventory button if the player is a farmer
    rolePanelView.getShowInventoryButton().setEnabled(controller.getSelectedPerson().getRole().equals(Role.FARMER)? true : false);
    // enable the role button if the player is not in the world
    rolePanelView.getRoleMenu().setEnabled(controller.getSelectedPerson().getPlace() == null? true : false);

    rolePanelView.updateRolePanelView();

    // Update the panel
    revalidate();
    repaint();
  }

  /**
   * Method to create an action listener for the toggle buttons
   * @param buttonGroup
   * @param item
   * @param toggleButton
   * @return
   */
  public ActionListener toggleButtonListener(DeselectableButtonGroup buttonGroup ,Object item, JToggleButton toggleButton){
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // if the player is in the same place, keep the selected item
        controller.setSelectedItem(buttonGroup.handleClick(toggleButton,(Object)item));
        getRolePanelView().updateActionButtons();
      }};
  }

  /**
   * Method to show a popup with the error message
   * @param message
   */
  public void exceptionPopup(String message) {
    Place place = this.controller.getSelectedPerson().getPlace();
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    try {
      // enter back in the place that might have been curropted to the exception
      if (place != null)
        this.controller.getSelectedPerson().getActions().enter(place);
    } catch (PlaceNotAvailableException e) {
      this.exceptionPopup(e.toString());
    }
  }
}




   