package view.rolepanel;
import javax.swing.*;

import controller.Controller;
import model.actors.actions.ActionsManager;
import model.actors.person.Landlord;
import model.actors.person.PersonAbstract.Role;
import model.calendar.Calendar;
import model.exceptions.CustomExceptions.ActionNotAvailableException;
import model.exceptions.CustomExceptions.PlaceNotAvailableException;
import model.item.Item;
import model.place.land.LandAbstract;
import model.place.land.chunks.AnimalChunk;
import model.place.land.chunks.PlantChunk;
import model.progress.GameBackupManager;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Create role panel
  public class RolePanelView{
    private Controller controller;
    private View view;

    private InventoryPanelView inventoryPanelView;
    private JPanel buttonPanel;
    private JPanel rolePanel;
    private JMenuBar menuBar;
    private JMenu roleMenu;
    private JToggleButton showInventoryButton;
    private JMenu backupMenu;
    private JLabel placeLabel;
    private JLabel roleLabel;
    private JLabel calendar;
    private Map<String, JMenuItem> savedGameItems;


    public RolePanelView(Controller controller, View view) throws ActionNotAvailableException{
        this.controller = controller;
        this.view = view;
        savedGameItems = new HashMap<>();
        
        rolePanel = createRolePanel();
        inventoryPanelView = new InventoryPanelView(controller, view);
    }

    public JPanel getRolePanel() {
        return rolePanel;
    }

    public JPanel createRolePanel() throws ActionNotAvailableException {
        // panel creation
        rolePanel = new JPanel(new BorderLayout());
        rolePanel.setPreferredSize(new Dimension((int)view.getSize().getWidth(), 75));

        // set the layout of the role panel
        createMenuBar();
        createActionsButtonPanel(controller.getSelectedPerson().getRole());

        return rolePanel;
    }

    public void createMenuBar(){
        /*
         * Menu bar creation
         */
        // define the menu bar
        menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
    
         // define menu bar elements
        roleMenu = new JMenu("Ruolo");
        showInventoryButton = new JToggleButton("Inventory");
        placeLabel = new JLabel("World");
        roleLabel = new JLabel(controller.getSelectedPerson().getRole().toString());
        calendar = new JLabel("Day: " + Calendar.getInstance().getDay() +
                "      Season: " + Calendar.getInstance().getSeason().toString() +
                "      Weather: " + Calendar.getInstance().getWeather().toString());

        // define role menu items
        JMenuItem farmerItem = new JMenuItem(controller.getPlayer(Role.FARMER).toString());
        JMenuItem ownerItem = new JMenuItem(controller.getPlayer(Role.LANDLORD).toString());

    
        showInventoryButton.setBorderPainted(false);
        showInventoryButton.setContentAreaFilled(false);
    
        // action listener for inventory button
        showInventoryButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (showInventoryButton.isSelected()) {
                view.getWorldPanelView().getWorldPanel().add(inventoryPanelView.createInventoryPanel());
              showInventoryButton.setForeground(Color.WHITE);
            } else {
                view.getWorldPanelView().getWorldPanel().remove(inventoryPanelView.getInventoryPanel());       
             showInventoryButton.setForeground(Color.BLACK);   
            }
            menuBar.revalidate();
            menuBar.repaint();
          }
        });
    
        // disable inventory button if the role is "Landlord"
        if(controller.getSelectedPerson().getRole()==Role.LANDLORD) {
            showInventoryButton.setEnabled(false);
        } else{
          showInventoryButton.setEnabled(true);
        }
        
        // action listener for role selection
        ActionListener roleListener = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            controller.changeRole(e.getActionCommand() == "Farmer" ? Role.FARMER : Role.LANDLORD);
            rolePanel.removeAll();
            createMenuBar();
            try {
              controller.setOldPlace(controller.getSelectedPerson().getPlace());
              createActionsButtonPanel(controller.getSelectedPerson().getRole());
            } catch (ActionNotAvailableException e1) {
                view.exceptionPopup(e1.getCause().getMessage(),e.toString());
            }
            rolePanel.revalidate();
            rolePanel.repaint();
    
            // Update the panel to disable buttons that are not available for the selected role
            Container parent = view.getWorldPanelView().getWorldPanel().getParent();
            parent.remove(view.getWorldPanelView().getWorldPanel());//remove the old panel
    
            // Creare un nuovo pannello e aggiungerlo al contenitore padre
            try {
                view.getWorldPanelView().createWorldPanel();
            } catch (ActionNotAvailableException e1) {
                view.exceptionPopup(e1.getCause().getMessage(),e.toString());
            }
            parent.add(view.getWorldPanelView().getWorldPanel());
    
            // Update parent panel
            parent.revalidate();
            parent.repaint();
          }
        };
        // add action listener to the menu items
        farmerItem.addActionListener(roleListener);
        ownerItem.addActionListener(roleListener);
    
        // setup the position of the elements
    
        // add elements to the menu bar
        roleMenu.add(farmerItem);
        roleMenu.add(ownerItem);
        menuBar.add(createBackupMenu());
        menuBar.add(new JSeparator(SwingConstants.VERTICAL));
        menuBar.add(roleMenu);
        menuBar.add(Box.createHorizontalStrut(5));
        menuBar.add(roleLabel);
        menuBar.add(Box.createHorizontalStrut(50));
        menuBar.add(new JSeparator(SwingConstants.VERTICAL));
        menuBar.add(placeLabel);
        menuBar.add(Box.createHorizontalStrut(50));
        menuBar.add(new JSeparator(SwingConstants.VERTICAL));
        menuBar.add(Box.createHorizontalStrut(5));
        menuBar.add(calendar);
        menuBar.add(Box.createHorizontalStrut(15));
        menuBar.add(new JSeparator(SwingConstants.VERTICAL));
        menuBar.add(Box.createHorizontalStrut(100));
        menuBar.add(showInventoryButton);
        
        rolePanel.add(menuBar, BorderLayout.NORTH);
      }

      public void createActionsButtonPanel(Role role) throws ActionNotAvailableException {
        /*
         * This method creates the panel that contains the buttons for the actions
         */
        // define actions button panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Set the selected role
        controller.changeRole(role);

        // Set the layout of the button panel
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        // iterate over the actions and add the buttons
        for(ActionsManager.Action action : this.controller.getSelectedPerson().getActions().getAvailableActions()) {
            JButton button = new JButton(action.toString());   
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.performAction(action);
                        controller.setOldPlace(controller.getSelectedPerson().getPlace());
                        switch (controller.getSelectedPerson().getPlace().getType()) {
                            case ANIMAL_LAND:
                                view.updateActualPanel(view.getWorldPanelView().getWorldPanel(), view.getLandView().createInsideLand());
                                break;
                            case PLANT_LAND:
                                view.updateActualPanel(view.getWorldPanelView().getWorldPanel(), view.getLandView().createInsideLand());
                                break;
                            case PLANT_CHUNK:
                                view.updateActualPanel(view.getWorldPanelView().getWorldPanel(), view.getLandView().createPlantChunkPanel((PlantChunk)controller.getSelectedPerson().getPlace()));
                                break;
                            case ANIMAL_CHUNK:
                                view.updateActualPanel(view.getWorldPanelView().getWorldPanel(), view.getLandView().createAnimalChunkPanel((AnimalChunk)controller.getSelectedPerson().getPlace()));
                                break;
                            case BARN:
                                view.updateActualPanel(view.getWorldPanelView().getWorldPanel(), view.getBarnView().createBarnPlace(controller.getSelectedPerson().getPlace()));
                                break;
                            case MARKET:
                                view.updateActualPanel(view.getWorldPanelView().getWorldPanel(), view.getBarnView().createBarnPlace(controller.getSelectedPerson().getPlace())); 
                                break;
                            default:
                                view.exceptionPopup("Place not found","Place not found");
                                break;
                        }
                    } catch (  IllegalArgumentException 
                             | SecurityException 
                             | PlaceNotAvailableException
                             | ActionNotAvailableException e1) {
                        view.exceptionPopup(e1.getCause().getMessage(),e.toString());
                    }
                }
            });
            buttonPanel.add(button);
        }
        updateActionButtons();
    
        // Update the panel
        buttonPanel.revalidate();
        buttonPanel.repaint();
        rolePanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    

    public void updateActionButtons() {
        /*
         * Check if the selected person has the action enabled and if the action is valid
         */
        Set<ActionsManager.Action> actions = this.controller.getSelectedPerson().getActions().getAvailableActions();
    
        for (Component component : buttonPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                ActionsManager.Action action = ActionsManager.Action.valueOf((button.getText().replace(" ", "_")).toUpperCase());
    
                // Controlla se l'azione corrente è presente nel set di azioni del personaggio selezionato
                if (actions.contains(action)) {
                    boolean isEnabled = action.isOptional();
    
                    if (controller.getSelectedItem() != null) {
                        if (controller.getSelectedItem() instanceof Item) {
                            isEnabled = isEnabled || action.isItemValid(((Item)(controller.getSelectedItem())).getType(), null);
                        } else if (controller.getSelectedItem() instanceof LandAbstract) {
                            isEnabled = isEnabled || action.isItemValid(null, (LandAbstract) controller.getSelectedItem());
                        }
                    } else {
                        isEnabled = isEnabled || action.isItemValid(null, null);
                    }
    
                    button.setEnabled(isEnabled);
                } else {
                    button.setEnabled(false);
                }
            }
        }
    }
     
    private JMenu createBackupMenu() {
        /*
        * Backup menu creation
        */
        GameBackupManager backup = controller.getBackup();
        // panel creation
        backupMenu = new JMenu("Backup");
        JMenuItem saveGame = new JMenuItem("Save");
        JMenu loadGame = new JMenu("Load");
        JMenu deleteGame = new JMenu("Delete");

        // delete game menu
        for (String save : backup.getSavesList()) {
            JMenuItem savedBackupToDelete = new JMenuItem(save.substring(0, save.length() - 4));
            deleteGame.add(savedBackupToDelete);

            ActionListener deleteCurrentGame = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.deleteSave(save);
                    } catch (SecurityException e1) {
                        view.exceptionPopup(e1.getCause().getMessage(),e.toString());
                    }
                    deleteGame.remove(savedBackupToDelete); // delete from delete menu
                    // remove from load save menu
                    JMenuItem savedBackup = savedGameItems.get(save);
                    if (savedBackup != null) {
                        loadGame.remove(savedBackup);
                        savedGameItems.remove(save);
                    }
                    // revalidate and repaint the frame to update the menus
                    backupMenu.revalidate();
                    backupMenu.repaint();
                }
            };
            savedBackupToDelete.addActionListener(deleteCurrentGame);
        }

        // save game menu
        ActionListener saveCurrentGame = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] saveName = new String[1];

            saveName[0] = controller.saveGame();
            JMenuItem savedBackup = new JMenuItem(saveName[0].substring(0, saveName[0].length() - 4));
            savedGameItems.put(saveName[0], savedBackup);
            loadGame.add(savedBackup);

            // Create a new JMenuItem for the delete menu
            JMenuItem savedBackupToDelete = new JMenuItem(saveName[0].substring(0, saveName[0].length() - 4));
            savedBackupToDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.deleteSave(saveName[0]);
                    } catch ( SecurityException e1) {
                        view.exceptionPopup(e1.getCause().getMessage(),e.toString());
                    }
                    deleteGame.remove(savedBackupToDelete); // delete from delete menu
                    loadGame.remove(savedGameItems.get(saveName[0])); // remove from load save menu
                    savedGameItems.remove(saveName[0]); // remove from savedGameItems map

                    // revalidate and repaint the frame to update the menus
                    backupMenu.revalidate();
                    backupMenu.repaint();
                }
            });

            // Add the new JMenuItem to the delete menu
            deleteGame.add(savedBackupToDelete);

            // revalidate and repaint the frame to update the menus
            backupMenu.revalidate();
            backupMenu.repaint();
        }
        };
        saveGame.addActionListener(saveCurrentGame);

        // load game menu
        for (String save : backup.getSavesList()) {
            JMenuItem savedBackup = new JMenuItem(save.substring(0, save.length() - 4));
            savedBackup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.loadGame(save);
                }
            });
            loadGame.add(savedBackup);
            savedGameItems.put(save, savedBackup);
        }

        // add elements to the menu bar
        backupMenu.add(saveGame);
        backupMenu.add(loadGame);
        backupMenu.add(deleteGame);
        return backupMenu;
    }


    public void updateLabels() throws ActionNotAvailableException{
        /*
            * Update the labels with the new values
            */
        this.roleLabel.setText(this.controller.getSelectedPerson().getRole().toString());
        this.placeLabel.setText((this.controller.getSelectedPerson().getPlace() == null)? "World" : this.controller.getSelectedPerson().getPlace().getType().toString());
        this.calendar.setText((this.controller.getSelectedPerson().getRole() == Role.FARMER)?("Day: " + this.controller.getCalendar().getDay() + 
                                                                                       "      Season: " + this.controller.getCalendar().getSeason().toString().toLowerCase() +
                                                                                       "      Weather: " + this.controller.getCalendar().getWeather().toString().toLowerCase()) : 
                                                                                       "Day: " + this.controller.getCalendar().getDay() +
                                                                                       "      Balance: " + ((Landlord)(this.controller.getSelectedPerson())).getBalance());
                                                                                
        // Update the panel
        rolePanel.revalidate();
        rolePanel.repaint();
    }

    public JMenu getRoleMenu() {
        /*
         * Return the role menu
         */
        return roleMenu;
    }

    public JToggleButton getShowInventoryButton() {
        /*
         * Return the show inventory button
         */
        return showInventoryButton;
    }

    public void updateRolePanelView() throws ActionNotAvailableException{
        /*
         * Update the role panel view
         */
        // update the action buttons
        rolePanel.remove(buttonPanel);
        createActionsButtonPanel(controller.getSelectedPerson().getRole());

        // update the labels
        updateLabels();
    }
}