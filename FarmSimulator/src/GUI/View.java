package GUI;

import javax.swing.*;

import Actors.Actions.ActionsManager;
import Actors.Actions.PlayerActions;
import Exceptions.CustomExceptions.ActionNotAvailableException;
import Exceptions.CustomExceptions.PlaceNotAvailableException;
import GUI.Custom.DeselectableButtonGroup;
import Inventory.Inventory;
import Item.ItemType;
import Item.Animal.AnimalAbstract;
import Item.Interface.Item;
import Item.Plants.PlantAbstract;
import Place.Place;
import Place.Land.AnimalLand;
import Place.Land.PlantLand;
import Progress.GameBackup;
import Place.Land.PlantChunk;
import Place.Land.LandAbstract;
import Place.Places;
import Place.Barn.Barn;
import Place.Barn.Market.Market;
import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Calendar.Calendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Interface Testing
public class View extends JFrame{
  // constants
  final int MAX_HEIGHT = 600;
  final int MAX_WIDTH = 800;

  // attributes
  private JMenuBar menuBar;
  private JPanel buttonPanel;
  private JPanel rolePanel;
  private JPanel worldPanel;
  private JPanel insideBarn;
  private JPanel insideMarket;
  private JPanel barnInventory;
  private JLabel calendar;
  private JScrollPane scrollableInventoryPanel;
  private JMenu roleMenu;
  private JMenu backupMenu;
  private JMenuItem farmerItem;
  private JMenuItem ownerItem;
  private JToggleButton showInventoryButton;
  private JLabel roleLabel;
  private JLabel placeLabel;
  private Model model;
  private Controller controller;
  private GameBackup backup;
  private Map<String, JMenuItem> savedGameItems;
  private Item selectedItem;

  // constructor
  public View(Model model,Controller controller){
    // setup main frame
    setTitle("Farming Simulator");
    setSize(MAX_WIDTH, MAX_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    savedGameItems = new HashMap<>();
    this.model = model;
    this.controller = controller;
    this.backup = controller.getBackup();

    // setup main layout
    Container contentPane = getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

    // add panels to main layout
    try {
      contentPane.add(this.createRolePanel());
      contentPane.add(this.createWorldPanel());
    } catch (ActionNotAvailableException e) {
      e.printStackTrace();
    }
  }

  // update MVC
  public void updateMVC(Controller c, Model m){
    this.controller = c;
    this.model = m;
    this.backup = controller.getBackup();
  }

  // Create role panel
  private JPanel createRolePanel() throws ActionNotAvailableException  {
    // panel creation
    rolePanel = new JPanel(new BorderLayout());
    rolePanel.setPreferredSize(new Dimension(800, 100));
    rolePanel.setBackground(Color.RED);

    
    
    // set the layout of the role panel
    rolePanel.add(createMenuBar(), BorderLayout.NORTH); // align the menu bar to the left
    rolePanel.add(createActionsButtonPanel(model.getSelectedPerson().toString()), BorderLayout.CENTER);
    
    return rolePanel;
  }

  public JMenuBar createMenuBar(){
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
    roleLabel = new JLabel(model.getSelectedPerson().toString());
    calendar = new JLabel("Day: " + Calendar.getInstance().getDay() + 
                          "      Season: " + Calendar.getInstance().getSeason().toString().toLowerCase() +
                          "      Weather: " + Calendar.getInstance().getWeather().toString().toLowerCase());

    // define role menu items
    farmerItem = new JMenuItem(model.getPersons()[0].toString());
    ownerItem = new JMenuItem(model.getPersons()[1].toString());

    showInventoryButton.setBorderPainted(false);
    showInventoryButton.setContentAreaFilled(false);

    // action listener for inventory button
    showInventoryButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (showInventoryButton.isSelected()) {
          worldPanel.add(createInventoryPanel());
          showInventoryButton.setForeground(Color.WHITE);
        } else {
         worldPanel.remove(scrollableInventoryPanel);       
         showInventoryButton.setForeground(Color.BLACK);   
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
      }
    });

    // disable inventory button if the role is "Landlord"
    if(model.getSelectedPerson().toString().equals("Landlord")) {
        showInventoryButton.setEnabled(false);
    } else{
      showInventoryButton.setEnabled(true);
    }
    
    // action listener for role selection
    ActionListener roleListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.changeRole(e.getActionCommand());
        try {
          updateActualPanel(rolePanel, createActionsButtonPanel(e.getActionCommand()));
        } catch (ActionNotAvailableException e1) {
          e1.printStackTrace();
        }
        rolePanel.remove(menuBar);
        menuBar = createMenuBar();
        rolePanel.add(menuBar, BorderLayout.NORTH);
        rolePanel.revalidate();
        rolePanel.repaint();

        // Update the panel to disable buttons that are not available for the selected role
        Container parent = worldPanel.getParent();
        parent.remove(worldPanel);//remove the old panel

        // Creare un nuovo pannello e aggiungerlo al contenitore padre
        try {
          worldPanel = createWorldPanel();
        } catch (ActionNotAvailableException e1) {
          e1.printStackTrace();
        }
        parent.add(worldPanel);

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
    
    return menuBar;
  }

  public JPanel createActionsButtonPanel(String role) throws ActionNotAvailableException {
    /*
     * This method creates the panel that contains the buttons for the actions
     */
    // Get the actions of the selected role
    PlayerActions actions = this.model.getSelectedPerson().getActions();
    // define actions button panel
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    buttonPanel.setBackground(Color.RED); // TODO: remove this line

    // Set the selected role
    controller.changeRole(role);

    // Set the layout of the button panel
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;

    // iterate over the actions and add the buttons
    for (ActionsManager.Action a : actions.getActions()){
      JButton button = new JButton(a.toString());   
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                  controller.performAction(a,new ArrayList<>(){{add(model.getSelectedPerson().getPlace());
                                                                add(selectedItem);}});
                  switch (model.getSelectedPerson().getPlace().getType()) {
                    case ANIMAL_LAND:
                      updateActualPanel(worldPanel, createInsideLand());
                      break;
                    case PLANT_LAND:
                      updateActualPanel(worldPanel, createInsideLand());
                      break;
                    case PLANT_CHUNK:
                      updateActualPanel(worldPanel, createChunkPanel((PlantChunk)model.getSelectedPerson().getPlace()));
                      break;
                    case BARN:
                      updateActualPanel(worldPanel, createBarnPlace());
                      break;
                    case MARKET:
                      //updateActualPanel(worldPanel, createBarnPlace()); TODO: Fix
                      updateActualPanel(insideMarket, createMarketPanel());
                      break;
                    default:
                      System.out.println("Error: place not found");
                      break;
                  }
                  
                  if (model.getSelectedPerson().getPlace().getType() == Places.PLANT_CHUNK){
                   
                  } else if (model.getSelectedPerson().getPlace().getType() == Places.PLANT_LAND){
                   
                  }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException | PlaceNotAvailableException
                    | ActionNotAvailableException e1) {
                  e1.printStackTrace();
                }
            }
        });
      buttonPanel.add(button, constraints);
    }
    updateActionButtons();
    // Update the panel
    revalidate();
    repaint();
    return buttonPanel;
  }

  private JMenu createBackupMenu() {
    /*
     * Backup menu creation
     */
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
                } catch (InvocationTargetException | NoSuchMethodException | SecurityException | IOException
                        | InterruptedException e1) {
                    e1.printStackTrace();
                }
                deleteGame.remove(savedBackupToDelete); // delete from delete menu
                // remove from load save menu
                JMenuItem savedBackup = savedGameItems.get(save);
                if (savedBackup != null) {
                    loadGame.remove(savedBackup);
                    savedGameItems.remove(save);
                }
                // revalidate and repaint the frame to update the menus
                revalidate();
                repaint();
            }
        };
        savedBackupToDelete.addActionListener(deleteCurrentGame);
    }

    // save game menu
    ActionListener saveCurrentGame = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          String[] saveName = new String[1];
          try {
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
                      } catch (InvocationTargetException | NoSuchMethodException | SecurityException | IOException
                              | InterruptedException e1) {
                          e1.printStackTrace();
                      }
                      deleteGame.remove(savedBackupToDelete); // delete from delete menu
                      loadGame.remove(savedGameItems.get(saveName[0])); // remove from load save menu
                      savedGameItems.remove(saveName[0]); // remove from savedGameItems map

                      // revalidate and repaint the frame to update the menus
                      revalidate();
                      repaint();
                  }
              });

              // Add the new JMenuItem to the delete menu
              deleteGame.add(savedBackupToDelete);

          } catch (IOException e1) {
              e1.printStackTrace();
          }
          // revalidate and repaint the frame to update the menus
          revalidate();
          repaint();
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
                getContentPane().removeAll();
                try {
                    // rebuild the frame
                    getContentPane().add(createRolePanel());
                    getContentPane().add(createWorldPanel());
                    updateLabels();
                } catch (ActionNotAvailableException e1) {
                    e1.printStackTrace();
                }
                // revalidate and repaint the frame to update the menus
                revalidate();
                repaint();
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

  public JScrollPane createInventoryPanel() {
    /*
     * Create a panel with a grid layout to hold the inventory buttons
     */
    JPanel inventoryPanel = new JPanel(new GridLayout(4, 3));
    inventoryPanel.setPreferredSize(new Dimension(200, 500));
    inventoryPanel.setBackground(Color.YELLOW); // TODO: remove this line

    Inventory inventory = ((Farmer) (model.getSelectedPerson())).getInventory();

    // Create a deselectable button group for the toggle buttons
    DeselectableButtonGroup buttonGroup = new DeselectableButtonGroup();

    for (Item item : inventory.getInventory()) {
        // Create a JToggleButton instead of a JButton<
        JToggleButton toggleButton = new JToggleButton((item.getType() instanceof ItemType.Tools)? "<html>" + item.getType().toString() + "<br>" + item.getStatus() +  "<html>": item.getType().toString());
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedItem = buttonGroup.handleClick(toggleButton,item);
                updateActionButtons();
            }
        });

        // Add the toggle button to the button group and the panel
        buttonGroup.add(toggleButton);
        inventoryPanel.add(toggleButton);
    }

    // Wrap the inventoryPanel in a JScrollPane
    scrollableInventoryPanel = new JScrollPane(inventoryPanel);
    return scrollableInventoryPanel;
  }

  public JPanel createWorldPanel() throws ActionNotAvailableException{
    /*
     * This method creates the world panel, which contains the land and barn panels
     */
    // create the panel
    worldPanel = new JPanel(new GridLayout(1, 2));
    worldPanel.setPreferredSize(new Dimension(800, 500));
    worldPanel.setBackground(Color.GREEN); // TODO: remove this line

    // create the land and barn panels
    JPanel barn = new JPanel(new GridBagLayout());
    JPanel land = new JPanel(new GridLayout(3, 3));

    // enable the possibility to change the role
    roleMenu.setEnabled(true);

    // add the land buttons
    for (Place i : this.model.getMap().get(1)) {
        JButton button = new JButton(i.getType().toString());
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
                try {
                  controller.enterNewPlace(i);
                } catch (PlaceNotAvailableException e1) {
                  e1.printStackTrace();
                }
                try {
                  updateActualPanel(worldPanel, createInsideLand());
                } catch (ActionNotAvailableException e1) {
                  e1.printStackTrace();
                }
            }
        });
        land.add(button);
        // disable land button if the role is not the farmer
        button.setEnabled((model.getSelectedPerson().toString() == "Farmer") ? true : false);
    }
     // add the barn button
     JButton barnButton = new JButton(this.model.getMap().get(0).get(0).getType().toString());
     
     barnButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)  {
          try {
            controller.enterNewPlace(model.getMap().get(0).get(0));
          } catch (PlaceNotAvailableException e1) {
            e1.printStackTrace();
          }
          try {
            updateActualPanel(worldPanel, createBarnPlace());
          } catch (ActionNotAvailableException e1) {
            e1.printStackTrace();
          }
      }
  });

     barnButton.setPreferredSize(new Dimension(200,200));
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.gridx = 0;
     gbc.gridy = 0;
     gbc.gridwidth = 1;
     gbc.gridheight = 1;
     gbc.weightx = 0;
     gbc.weighty = 0;
     gbc.fill = GridBagConstraints.BOTH;
     gbc.anchor = GridBagConstraints.CENTER;
     gbc.insets = new Insets(0, 0, 0, 0);
     barn.add(barnButton, gbc);
 
     // add the next day button
     JButton nextDay = new JButton("Next day");
     nextDay.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
         controller.updateModel();
         try {
           updateActualPanel(worldPanel, createWorldPanel());
         } catch (ActionNotAvailableException e1) {
           e1.printStackTrace();
         }
       }
 
     });

     GridBagConstraints c = new GridBagConstraints();
     c.gridx = 0;
     c.gridy = 1;
     c.anchor = GridBagConstraints.SOUTHEAST;
     c.insets = new Insets(10, 10, 10, 0);
     c.gridwidth = 1;
     barn.add(nextDay, c);    

    worldPanel.add(land);
    worldPanel.add(barn);

    // update the labels
    updateLabels();
    return worldPanel;
  }

  private JPanel createInsideLand(){
    /*
     * This method creates the panel that will be displayed when the user clicks on a land
     */
    // get the actual place
    LandAbstract actualPlace = (LandAbstract)this.model.getSelectedPerson().getPlace();

    // create the panel that will contain the elements
    JPanel insideLand = new JPanel(new GridLayout(3, 3));
    insideLand.setPreferredSize(new Dimension(800, 500));
    insideLand.setBackground(Color.GREEN); // TODO: remove this line

    // depending on the type of the place, display the elements
    this.placeLabel.setText(actualPlace.getType().toString());
    if (actualPlace.getElements() != null){
      // if it's an animal land
      if (actualPlace.getType() == Places.ANIMAL_LAND){ 
        for(AnimalAbstract animal : ((AnimalLand)(actualPlace)).getElements()){
          JButton button = new JButton(animal.getType().toString());
          insideLand.add(button);
          
      }} // if it's a plant land
      else if (actualPlace.getType() == Places.PLANT_LAND){ 
        for(PlantChunk chunk : ((PlantLand)(actualPlace)).getElements()){
          JButton button = new JButton((chunk.getPlant() == null )? "Empty" : chunk.getPlant().getType().toString());
          insideLand.add(button);
          button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // if the chunk isn't empty enter the chunk 
                try {
                  controller.enterNewPlace(chunk);
                  updateActualPanel(worldPanel, createChunkPanel(chunk));
                } catch (ActionNotAvailableException | PlaceNotAvailableException e1) {
                  e1.printStackTrace();
                }
            }});
        }
      }}

      // add the exit button
      JButton exitButton = new JButton("Exit");
      exitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.leaveOldPlace();
          try {
            updateActualPanel(worldPanel, createWorldPanel());
          } catch (ActionNotAvailableException e1) {
            e1.printStackTrace();
          }
        }});
      insideLand.add(exitButton);
      return insideLand;
    }

  private JPanel createChunkPanel(PlantChunk chunk) throws PlaceNotAvailableException, ActionNotAvailableException{
    /*
     * This method creates the panel that will be displayed when the player enters a chunk
     */
    // get the plant inside the chunk
    PlantAbstract plant = chunk.getPlant();
    
    // create the panel that will contain the elements
    JPanel chunkPanel = new JPanel(new GridLayout(1, 2));
    JLabel plantLabel = new JLabel();
    
    // set the panel's size 
    chunkPanel.setPreferredSize(new Dimension(800, 500));
    chunkPanel.setBackground(Color.GREEN); // TODO: remove this line

    // set the label's text
    plantLabel.setText("<html><div style='font-size:16px;'>" + ((plant == null)?"Empty": plant.getType().toString())+
                       "</div><div style='font-size:12px;'>Life Stage: " + ((plant == null)?"No Plant": plant.getLifeStage().toString()) +
                       "<br>Water Level: </br>" + chunk.getWaterLevel() +
                       "<br>Fertilization Level: </br>" + chunk.getFertilizationLevel() +
                       "<br>Plowed :</br> " + ((chunk.getDirtStatus()== true)?"Yes":"No") +
                       "</div></html>");

    // add the label to the panel
    chunkPanel.add(plantLabel);
    
    // add the exit button
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          controller.enterNewPlace(chunk.getLand());
          updateLabels();
          updateActualPanel(worldPanel, createInsideLand());
        } catch (ActionNotAvailableException| PlaceNotAvailableException e1) {
          e1.printStackTrace();
        }
      }});
    // add the exit button to the panel
    chunkPanel.add(exitButton);

    // Update the panel
    revalidate();
    repaint();
    return chunkPanel;
  }  

  private JPanel createBarnPlace(){
    // get the actual place
    Barn actualPlace = (Barn)this.model.getSelectedPerson().getPlace();

    // create the panel that will contain the elements
    insideBarn = new JPanel(new GridLayout(1, 2));

    JPanel market = new JPanel(new GridLayout(4, 3));
    barnInventory = new JPanel(new GridLayout(4, 6));

    // Create a deselectable button group for the toggle buttons
    DeselectableButtonGroup buttonGroup = new DeselectableButtonGroup();

    insideBarn.setPreferredSize(new Dimension(800, 500));

    market.setBorder(BorderFactory.createTitledBorder("Inside Market"));
    barnInventory.setBorder(BorderFactory.createTitledBorder("Barn inventory"));
    market.setBackground(Color.GREEN); // TODO: remove this line
    barnInventory.setBackground(Color.ORANGE); // TODO: remove this line

    JButton enterMarket = new JButton("Market");
    enterMarket.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          controller.enterNewPlace(((Barn)(model.getSelectedPerson().getPlace())).getMarket());
          updateActualPanel(market, createMarketPanel());
        } catch (ActionNotAvailableException | PlaceNotAvailableException e1) {
          e1.printStackTrace();
        }
      }
      
    });

    // depending on the type of the place, display the elements
    this.placeLabel.setText(actualPlace.getType().toString());
    if (actualPlace.getBarnInventory() != null){
      for(Item item : actualPlace.getBarnInventory().getInventory()){
        JToggleButton toggleButton = new JToggleButton((item.getType() instanceof ItemType.Tools)? "<html>" + item.getType().toString() + "<br>" + item.getStatus() +  "<html>": item.getType().toString());
        toggleButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              selectedItem = buttonGroup.handleClick(toggleButton,item);
              updateActionButtons();
          }
      });

        buttonGroup.add(toggleButton);
        barnInventory.add(toggleButton);
      }
    }    

    // add the exit button
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.leaveOldPlace();
        try {

          updateActualPanel(worldPanel, createWorldPanel());
        } catch (ActionNotAvailableException e1) {
          e1.printStackTrace();
        }
      }});

    // add the elements to the panel
    barnInventory.add(exitButton);
    insideBarn.add(barnInventory);
    insideBarn.add(market);
    market.add(enterMarket);

    // disable the possibility to enter the market if the player is not a landlord
    enterMarket.setEnabled(model.getSelectedPerson().toString().equals("Landlord")? true : false);
    return insideBarn;
  }

  private JPanel createMarketPanel(){
    insideMarket = new JPanel(new GridLayout(4, 3));
    Market actualPlace = (Market)this.model.getSelectedPerson().getPlace();
    // Create a deselectable button group for the toggle buttons
    DeselectableButtonGroup buttonGroup = new DeselectableButtonGroup();

    if (actualPlace.getItemShop() != null){
      for(Item item : actualPlace.getItemShop().getInventory()){
        JToggleButton toggleButton = new JToggleButton(item.getType().toString() + " " + item.getNumber() + " " + item.getStatus() );
        toggleButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              selectedItem = buttonGroup.handleClick(toggleButton,item);
              updateActionButtons();
          }
      });
        buttonGroup.add(toggleButton);
        insideMarket.add(toggleButton);
      }
    }    

    // add the exit button
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.leaveOldPlace();
        try {
          controller.enterNewPlace(model.getMap().get(0).get(0));
          updateActualPanel(insideBarn, createBarnPlace());
        } catch (PlaceNotAvailableException|ActionNotAvailableException e1) {
          e1.printStackTrace();
        }
      }});
      insideMarket.add(exitButton);

    return insideMarket;
  }

  private void updateLabels() throws ActionNotAvailableException{
    /*
     * Update the labels with the new values
     */
    this.roleLabel.setText(this.model.getSelectedPerson().toString());
    this.placeLabel.setText((this.model.getSelectedPerson().getPlace() == null)? "World" : this.model.getSelectedPerson().getPlace().getType().toString());
    this.calendar.setText((this.model.getSelectedPerson().toString()=="Farmer")?("Day: " + this.model.getCalendar().getDay() + 
    "      Season: " + this.model.getCalendar().getSeason().toString().toLowerCase() +
    "      Weather: " + this.model.getCalendar().getWeather().toString().toLowerCase()) : "Day: " + this.model.getCalendar().getDay() + "      Balance: " + ((Landlord)(this.model.getSelectedPerson())).getBalance());

    // Update the panel
    revalidate();
    repaint();
  }
  
  public void updateActualPanel(JPanel mainPanel, JPanel newPanel) throws ActionNotAvailableException{
    /*
     * Update the main panel with the new panel
     */
    mainPanel.removeAll();
    mainPanel.add(newPanel);
    mainPanel.revalidate();
    mainPanel.repaint();

    showInventoryButton.setEnabled(model.getSelectedPerson().toString().equals("Farmer")? true : false);
    roleMenu.setEnabled(model.getSelectedPerson().getPlace() == null? true : false);

    rolePanel.remove(buttonPanel);
    rolePanel.add(createActionsButtonPanel(this.model.getSelectedPerson().toString()));

    // close inventory when changing world panel
    selectedItem= null;
    updateLabels();

    // Update the panel
    revalidate();
    repaint();
  }

  private void updateActionButtons() {
  /*
   * Check if the selected person has the action enabled and if the action is valid
   */
    Set<ActionsManager.Action> actions = this.model.getSelectedPerson().getActions().getActions();
    
    for (Component component : buttonPanel.getComponents()) {
        if (component instanceof JButton) {
            JButton button = (JButton) component;
            System.out.println((button.getText().replace(" ", "_").toUpperCase()));
            ActionsManager.Action action = ActionsManager.Action.valueOf((button.getText().replace(" ", "_")).toUpperCase());
            
            // Controlla se l'azione corrente è presente nel set di azioni del personaggio selezionato
            if (actions.contains(action)) {
                boolean isEnabled = action.isOptional() || action.isItemValid(null) || (selectedItem != null && action.isItemValid(selectedItem.getType()));
                button.setEnabled(isEnabled);
            } else {
                button.setEnabled(false);
            }
        }
    }
  }
}




   
