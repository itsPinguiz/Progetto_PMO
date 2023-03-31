package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import Actors.Actions.ActionsManager;
import Actors.Actions.PlayerActions;
import Item.Animal.AnimalAbstract;
import Item.Plants.PlantAbstract;
import Place.Place;
import Place.Land.AnimalLand;
import Place.Land.PlantLand;
import Progress.GameBackup;
import Place.Land.PlantChunk;
import Place.Land.LandAbstract;
import Place.Places;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Interface Testing
public class View extends JFrame {
  // constants
  final int MAX_HEIGHT = 600;
  final int MAX_WIDTH = 800;

  // attributes
  private JMenuBar menuBar;
  private JPanel buttonPanel;
  private JPanel rolePanel;
  private JPanel worldPanel;
  private JMenu roleMenu;
  private JMenu backupMenu;
  private JMenuItem farmerItem;
  private JMenuItem ownerItem;
  private JLabel roleLabel;
  private JLabel placeLabel;
  private Model model;
  private Controller controller;
  private GameBackup backup;

  // constructor
  public View() {
    // setup main frame
    setTitle("Farming Simulator");
    setSize(MAX_WIDTH, MAX_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
  }

  // add controller to the view
  public void addController(Controller controller) {
    this.controller = controller;
    this.model = controller.getModel();
    this.backup = controller.getBackup();

     // setup main layout
     Container contentPane = getContentPane();
     contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

     // add panels to main layout
     contentPane.add(this.createRolePanel());
     contentPane.add(this.createWorldPanel());
  }

  // Create role panel
  private JPanel createRolePanel(){
    // panel creation
    rolePanel = new JPanel(new BorderLayout());
    rolePanel.setPreferredSize(new Dimension(800, 100));
    rolePanel.setBackground(Color.RED);

    // creation of the border for right padding
    Border padding = BorderFactory.createEmptyBorder(0, 0, 0, 5);

    // define the menu bar
    menuBar = new JMenuBar();
    menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    roleMenu = new JMenu("Ruolo");
    farmerItem = new JMenuItem(model.getPersons()[0].toString());
    ownerItem = new JMenuItem(model.getPersons()[1].toString());
    placeLabel = new JLabel("World");
    roleLabel = new JLabel(model.getSelectedPerson().toString());
    placeLabel.setBorder(padding);

    buttonPanel.setBackground(Color.RED); // TODO: remove this line
    
    // action listener for role selection
    ActionListener roleListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.changeRole(e.getActionCommand());
        setRoleActions(e.getActionCommand());
        // Update the role label
        roleLabel.setText(e.getActionCommand());
        // Update the panel to disable buttons that are not available for the selected role
        Container parent = worldPanel.getParent();
        parent.remove(worldPanel);//remove the old panel

        // Creare un nuovo pannello e aggiungerlo al contenitore padre
        worldPanel = createWorldPanel();
        parent.add(worldPanel);

        // Aggiornare il contenitore padre
        parent.revalidate();
        parent.repaint();
      }
    };
    // add action listener to the menu items
    farmerItem.addActionListener(roleListener);
    ownerItem.addActionListener(roleListener);

    // create the separator
    JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
    separator.setPreferredSize(new Dimension(1, 20)); // change the size to make it visible
    separator.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // adjust the border

    // add elements to the menu bar
    roleMenu.add(farmerItem);
    roleMenu.add(ownerItem);
    menuBar.add(createBackupMenu());
    menuBar.add(Box.createHorizontalStrut(5));
    menuBar.add(roleMenu);
    menuBar.add(Box.createHorizontalStrut(5));
    menuBar.add(separator);
    menuBar.add(Box.createHorizontalStrut(5));
    menuBar.add(roleLabel);
    menuBar.add(Box.createHorizontalGlue());
    menuBar.add(placeLabel);
    menuBar.add(Box.createHorizontalStrut(5));
    
    // set the layout of the role panel
    rolePanel.add(menuBar, BorderLayout.NORTH); // align the menu bar to the left
    rolePanel.add(buttonPanel, BorderLayout.CENTER);

    // update the role actions panel
    this.setRoleActions(model.getSelectedPerson().toString());

    return rolePanel;
  }

  // Role Actions panel
  public void setRoleActions(String role) {
    // Get the actions of the selected role
    PlayerActions actions = this.model.getSelectedPerson().getActions();

    // Set the selected role
    controller.changeRole(role);

    // Set the layout of the button panel
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;

    // Remove all the buttons from the panel
    buttonPanel.removeAll();

    // iterate over the actions and add the buttons
    for (ActionsManager.Action a : actions.getActions()){
      // actions with < 1 argument
      if ( model.getSelectedPerson().getActions().getActionReqArgs(a) <=1){
        JButton button = new JButton(a.toString());
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.performAction(a);
            }
        });
        buttonPanel.add(button, constraints);
      } else{ // actions with more than 1 argument
        JToggleButton toggleButton = new JToggleButton(a.toString());
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        buttonPanel.add(toggleButton, constraints);
      }
    }
    // Update the panel
    revalidate();
    repaint();
  }

  // Create backup panel
  private JMenu createBackupMenu(){
    // panel creation
    backupMenu = new JMenu("Backup");
    JMenuItem saveGame = new JMenuItem("Save");
    JMenu loadGame = new JMenu("Load");
    JMenu deleteGame = new JMenu("Delete");

    // delete game menu
    for (String save : backup.getSavesList()) {
      JMenuItem savedBackupToDelete = new JMenuItem(save);
      deleteGame.add(savedBackupToDelete);

      ActionListener deleteCurrentGame = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.deleteSave(save);
            deleteGame.remove(savedBackupToDelete); // delete from delete menu
            // remove from load save menu
            Component[] loadGameItems = loadGame.getMenuComponents();
            for (Component item : loadGameItems) {
                if (item instanceof JMenuItem && ((JMenuItem)item).getText().equals(save)) {
                    loadGame.remove(item);
                    break;
                }
            }

            }
        };
        savedBackupToDelete.addActionListener(deleteCurrentGame);
      }
      
    // save game menu
    ActionListener saveCurrentGame = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String saveName = controller.saveGame();
        deleteGame.add(new JMenuItem(saveName));
        loadGame.add(new JMenuItem(saveName));
      }
    };
    saveGame.addActionListener(saveCurrentGame);

    // load game menu
    for (String save : backup.getSavesList()){
      JMenuItem savedBackup = new JMenuItem(save);
      ActionListener loadCurrentGame = controller.createLoadCurrentGameActionListener(save);
      savedBackup.addActionListener(loadCurrentGame);
      loadGame.add(savedBackup);
    }

    // add elements to the menu bar
    backupMenu.add(saveGame);
    backupMenu.add(loadGame);
    backupMenu.add(deleteGame);
    return backupMenu;
  }

  // Crea pannello di gestione mondo
  public JPanel createWorldPanel(){
    // create the panel
    worldPanel = new JPanel(new GridLayout(1, 2));
    worldPanel.setPreferredSize(new Dimension(800, 500));
    worldPanel.setBackground(Color.GREEN); // TODO: remove this line
    
    // create the land and barn panels
    JPanel barn = new JPanel(new GridLayout(1, 1));
    JPanel land = new JPanel(new GridLayout(3, 3));
    
    // enable the possibility to change the role
    roleMenu.setEnabled(true); 

    // add the land buttons
    for(Place i: this.model.getMap().get(1)){
      JButton button = new JButton(i.getType().toString());
      button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              controller.enterNewPlace(i);
              updateActualPanel(worldPanel, createInsideLand());
          }
      });
      land.add(button);
      // disable land button if the role is not the farmer
      button.setEnabled((model.getSelectedPerson().toString() == "Farmer")?true:false);
    }
    // add the barn button
    barn.add(new JButton( this.model.getMap().get(0).get(0).getType().toString()));
    worldPanel.add(land);
    worldPanel.add(barn);    
    
    // update the labels
    updateLabels(worldPanel);
    return worldPanel;
  }

  // display animals when opening animal land else display chunks
  private JPanel createInsideLand(){
    // get the actual place
    LandAbstract actualPlace = (LandAbstract)this.model.getSelectedPerson().getPlace();

    // create the panel that will contain the elements
    JPanel insideLand = new JPanel(new GridLayout(3, 3));
    insideLand.setPreferredSize(new Dimension(800, 500));
    insideLand.setBackground(Color.GREEN); // TODO: remove this line

    // disable the possibility to change the role
    roleMenu.setEnabled(false); 

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
                if (chunk.getPlant() != null){
                  controller.enterNewPlace(actualPlace);
                  updateActualPanel(worldPanel, createChunkPanel(chunk, actualPlace));
                }
            }});
        }
      }}

      // add the exit button
      JButton exitButton = new JButton("Exit");
      exitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.leaveOldPlace();
          updateActualPanel(worldPanel, createWorldPanel());
          //updateLabels(insideLand);
        }});
      insideLand.add(exitButton);
      return insideLand;
    }

  // create the panel that will show the elements inside a chunk
  private JPanel createChunkPanel(PlantChunk chunk, LandAbstract plantLand){
    // get the plant inside the chunk
    PlantAbstract plant = chunk.getPlant();
    
    // create the panel that will contain the elements
    JPanel chunkPanel = new JPanel(new GridLayout(1, 2));
    JLabel plantLabel = new JLabel();
    
    // set the panel's size 
    chunkPanel.setPreferredSize(new Dimension(800, 500));
    chunkPanel.setBackground(Color.GREEN); // TODO: remove this line

    // set the label's text
    plantLabel.setText("<html><div style='font-size:16px; font-weight:bold;'>" + plant.getType().toString() +
                       "</div><div style='font-size:12px;'>Life Stage: " + plant.getLifeStage().toString() +
                       "<br>Water Level: " + chunk.getWaterLevel() +
                       "<br>Fertilization Level: " + chunk.getFertilizationLevel() +
                       "</div></html>");

    // add the label to the panel
    chunkPanel.add(plantLabel);

    // enter the chunk
    controller.enterNewPlace(chunk);
    
    // add the exit button
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.enterNewPlace(plantLand);
        updateLabels(chunkPanel);
        updateActualPanel(worldPanel, createInsideLand());
      }});
    // add the exit button to the panel
    chunkPanel.add(exitButton);
    
    // remove the previous buttons
    buttonPanel.removeAll();
    setRoleActions(this.model.getSelectedPerson().toString());

    // Update the panel
    revalidate();
    repaint();
    return chunkPanel;
  }  

  // Update labels
  private void updateLabels(JPanel panel){
    this.roleLabel.setText(this.model.getSelectedPerson().toString());
    this.placeLabel.setText((this.model.getSelectedPerson().getPlace() == null)? "World" : this.model.getSelectedPerson().getPlace().getType().toString());
    setRoleActions(this.model.getSelectedPerson().toString());

    // Update the panel
    panel.revalidate();
    panel.repaint();
  }
  
  // update actual panel
  public void updateActualPanel(JPanel mainPanel, JPanel newPanel){
    setRoleActions(model.getSelectedPerson().toString());
    mainPanel.removeAll();
    mainPanel.add(newPanel);
    mainPanel.revalidate();
    mainPanel.repaint();

    // Update the panel
    revalidate();
    repaint();
    updateLabels(mainPanel);
  }
}

   

