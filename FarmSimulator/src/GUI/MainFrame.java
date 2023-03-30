package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import Actors.Actions.ActionsManager;
import Actors.Actions.PlayerActions;
import Item.Animal.AnimalAbstract;
import Exceptions.CustomExceptions.PlaceNotAvailableException;
import Item.Interface.Item;
import Main.Game;
import Place.Place;
import Place.Land.AnimalLand;
import Place.Land.PlantLand;
import Progress.GameBackup;
import Place.Land.PlantChunk;
import Place.Land.LandAbstract;
import Place.Land.PlantChunk;
import Place.Places;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Interface Testing
public class MainFrame extends JFrame {
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
  private Game game;
  private GameBackup backup;
  private Item selectedItem;
  private Item selectedEntity;

  // constructor
  public MainFrame() {
    this.game = new Game();
    this.backup = new GameBackup(game);
    setTitle("Farming Simulator");
    setSize(MAX_WIDTH, MAX_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

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

    // attribututes initialization
    menuBar = new JMenuBar();
    menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    roleMenu = new JMenu("Ruolo");
    
    farmerItem = new JMenuItem(game.getPersons()[0].toString());
    ownerItem = new JMenuItem(game.getPersons()[1].toString());
    placeLabel = new JLabel("World");// Inizialmente impostato come World
    roleLabel = new JLabel(game.getSelectedPerson().toString()); // Inizialmente impostato come Agricoltore
    placeLabel.setBorder(padding);
    buttonPanel.setBackground(Color.RED); // TODO: remove this line
    
    // Action Listener for role selection
    ActionListener roleListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
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

    farmerItem.addActionListener(roleListener);
    ownerItem.addActionListener(roleListener);

    JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
    separator.setPreferredSize(new Dimension(1, 20)); // change the size to make it visible
    separator.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // adjust the border

    // add elements to the menu bar
    roleMenu.add(farmerItem);
    roleMenu.add(ownerItem);

    menuBar.add(backupMenu());
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
    this.setRoleActions(this.game.getPersons()[0].toString());

    return rolePanel;
  }

  // Create backup panel
  private JMenu backupMenu(){
    // panel creation
    backupMenu = new JMenu("Backup");
    JMenuItem saveGame = new JMenuItem("Save");
    JMenu loadGame = new JMenu("Load");
    JMenu deleteGame = new JMenu("Delete");

    // delete game menu
    for (String save : backup.getSavesList()){
      JMenuItem savedBackupToDelete = new JMenuItem(save);
      deleteGame.add(savedBackupToDelete);
      ActionListener deleteCurrentGame = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              try {
                  backup.deleteSave(save);
                  deleteGame.remove(savedBackupToDelete); // remove the menu item from the deleteGame menu
              } catch (Exception e1) {
                  e1.printStackTrace();
              }

          }};
      savedBackupToDelete.addActionListener(deleteCurrentGame);
  }
    
    // save game menu
    ActionListener saveCurrentGame = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          deleteGame.add(new JMenuItem(backup.saveCurrent()));
          loadGame.add(new JMenuItem(backup.saveCurrent()));
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }};
    saveGame.addActionListener(saveCurrentGame);

    // load game menu
    for (String save : backup.getSavesList()){
      JMenuItem savedBackup = new JMenuItem(save);
      ActionListener loadSelectedSave = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            backup.loadSave(save.substring(0, save.length() - 3));
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }};
      savedBackup.addActionListener(loadSelectedSave);
      loadGame.add(savedBackup);
    }

    // add elements to the menu bar
    backupMenu.add(saveGame);
    backupMenu.add(loadGame);
    backupMenu.add(deleteGame);
    return backupMenu;
  }

  // Role Actions panel
  private void setRoleActions(String role) {
    // Set the selected role
    if (role == game.getPersons()[0].toString()) {
      this.game.setSelectedPerson(this.game.getPersons()[0]);
    } else if (role == game.getPersons()[1].toString()) {
      this.game.setSelectedPerson(this.game.getPersons()[1]);
    }	

    PlayerActions actions = this.game.getSelectedPerson().getActions();

    // Set the layout of the button panel
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;

    // Remove all the buttons from the panel
    buttonPanel.removeAll();

    // iterate over the actions and add the buttons
    for (ActionsManager.Action a : actions.getActions()){
      // actions with 0 or 1 argument
      if ( game.getSelectedPerson().getActions().getActionReqArgs(a) <=0){
        JButton button = new JButton(a.toString());
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            System.out.println("Action " + a.toString() + " performed"); // TODO: remove this line
            game.getSelectedPerson().getActions().executeAction(a,game.getSelectedPerson().getPlace());
          }
        });
        buttonPanel.add(button, constraints);
      } else{ // actions with more than 1 argument
        // TODO
        JToggleButton button = new JToggleButton(a.toString());
         // Aggiungi un listener di mouse per rilevare le pressioni del pulsante
         button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              JToggleButton button = (JToggleButton) e.getSource();
              if (button.isSelected()) {
                  System.out.println("Button is pressed");
                  // aggiungere il codice da eseguire quando il pulsante viene premuto

              } else {
                  System.out.println("Button is released");
                  // aggiungere il codice da eseguire quando il pulsante viene rilasciato
              }
          }
         });
        buttonPanel.add(button, constraints);
      }
    }
    // Update the panel
    revalidate();
    repaint();
  }
  
  // Crea pannello di gestione mondo
  private JPanel createWorldPanel(){
    
    worldPanel = new JPanel(new GridLayout(1, 2));
    worldPanel.setPreferredSize(new Dimension(800, 500));
    worldPanel.setBackground(Color.GREEN); // TODO: remove this line
        
    JPanel barn = new JPanel(new GridLayout(1, 1));
    JPanel land = new JPanel(new GridLayout(3, 3));
    
    roleMenu.setEnabled(true); // enable the possibility to change the role

    for(Place i: this.game.getMap().get(1)){
      JButton button = new JButton(i.getType().toString());
      button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              try {
                game.getSelectedPerson().getActions().enter(i);
                setRoleActions(game.getSelectedPerson().toString());
                worldPanel.removeAll();
                worldPanel.add(createInsideLand());
                worldPanel.revalidate();
                worldPanel.repaint();
                // Aggiornare il pannello
                revalidate();
                repaint();
                updateLabels(worldPanel);
              } catch (PlaceNotAvailableException e1) {
                e1.printStackTrace();
              };
          }
      });
      land.add(button);
      // disable land button if the role is not the farmer
      button.setEnabled((game.getSelectedPerson().toString() == "Farmer")?true:false);
  }
    // add the barn button
    barn.add(new JButton( this.game.getMap().get(0).get(0).getType().toString()));
    worldPanel.add(land);
    worldPanel.add(barn);    
    
    updateLabels(worldPanel);
    return worldPanel;
  }

  // Update labels
  private void updateLabels(JPanel panel){
    this.roleLabel.setText(this.game.getSelectedPerson().toString());
    this.placeLabel.setText((this.game.getSelectedPerson().getPlace() == null)? "World" : this.game.getSelectedPerson().getPlace().getType().toString());
    setRoleActions(this.game.getSelectedPerson().toString());
    // Update the panel
    panel.revalidate();
    panel.repaint();
  }

  // display animals when opening animal land else display chunks
  private JPanel createInsideLand(){
    LandAbstract actualPlace = (LandAbstract)this.game.getSelectedPerson().getPlace();

    // create the panel that will contain the elements
    JPanel insideLand = new JPanel(new GridLayout(3, 3));
    insideLand.setPreferredSize(new Dimension(800, 500));
    insideLand.setBackground(Color.GREEN); // TODO: remove this line

    roleMenu.setEnabled(false); // disable the possibility to change the role

    // depending on the type of the place, display the elements
    this.placeLabel.setText(actualPlace.getType().toString());
    if (actualPlace.getElements() != null){
      if (actualPlace.getType() == Places.ANIMAL_LAND){ // if it's an animal land
        for(AnimalAbstract animal : ((AnimalLand)(actualPlace)).getElements()){
          insideLand.add(new JButton(animal.getType().toString()));
        }
      } else if (actualPlace.getType() == Places.PLANT_LAND){ // if it's a plant land
        for(PlantChunk chunk : ((PlantLand)(actualPlace)).getElements()){
          insideLand.add(new JButton((chunk.getPlant() == null )? "Empty" : chunk.getPlant().getType().toString()));
        }
      }}

      // add the exit button
      JButton exitButton = new JButton("Exit");
      exitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          worldPanel.removeAll();
          worldPanel.add(createWorldPanel());

          game.getSelectedPerson().getActions().leave();
          updateLabels(insideLand);
          
          // Aggiornare il pannello
          worldPanel.revalidate();
          worldPanel.repaint();
        }});
      insideLand.add(exitButton);
      return insideLand;
    }
  
  
  
  public static void main(String[] args) {
    MainFrame frame = new MainFrame();
    frame.setVisible(true);
    }
  }
   

