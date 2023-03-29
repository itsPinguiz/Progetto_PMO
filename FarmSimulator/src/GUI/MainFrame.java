package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import Actors.Actions.ActionsManager;
import Actors.Actions.PlayerActions;
import Animal.AnimalAbstract;
import Exceptions.CustomExceptions.PlaceNotAvailableException;
import Main.Game;
import Place.Place;
import Place.Land.AnimalLand;
import Place.Land.PlantLand;
import Place.Land.PlantChunk;
import Place.Land.LandAbstract;
import Place.Land.PlantChunk;
import Place.Places;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;


//Interface Testing
public class MainFrame extends JFrame {

  int MAX_HEIGHT = 600;
  int MAX_WIDTH = 800;

  private JMenuBar menuBar;
  private JPanel buttonPanel;
  private JPanel rolePanel;
  private JPanel worldPanel;
  private JMenu roleMenu;
  private JMenuItem farmerItem;
  private JMenuItem ownerItem;
  private JLabel roleLabel;
  private JLabel placeLabel;
  private Timer placeLabelTimer;
  private Game game;

  // constructor
  public MainFrame() {
    this.game = new Game();
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
     
    updatePlaceLabel(this).start(); // start the timer
  }

  // create timer to update place label
  private Timer updatePlaceLabel(JFrame frame){
    placeLabelTimer = new Timer(100, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
          if (game.getSelectedPerson().getPlace() != null){
              placeLabel.setText(game.getSelectedPerson().getPlace().getType().toString());
          } else {
              placeLabel.setText("World");
          }
    }});

    // add a listener to the timer to stop it when the window is closed
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
          placeLabelTimer.stop(); // Ferma il timer quando la finestra viene chiusa
          dispose(); // Chiude la finestra
      }
    });
    return placeLabelTimer;
  }

  // Crea pannello di gestione ruolo
  private JPanel createRolePanel(){
    // Creazione del pannello
    rolePanel = new JPanel(new BorderLayout());
    rolePanel.setPreferredSize(new Dimension(800, 100));
    rolePanel.setBackground(Color.RED);

    // Creazione del bordo con padding a destra
    Border padding = BorderFactory.createEmptyBorder(0, 0, 0, 5);

    //Componenti aggiuntivi
    menuBar = new JMenuBar();
    menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    roleMenu = new JMenu("Ruolo");
    farmerItem = new JMenuItem(game.getPersons()[0].toString());
    ownerItem = new JMenuItem(game.getPersons()[1].toString());
    placeLabel = new JLabel("World");// Inizialmente impostato come World
    roleLabel = new JLabel(game.getSelectedPerson().toString()); // Inizialmente impostato come Agricoltore
    placeLabel.setBorder(padding);

    buttonPanel.setBackground(Color.RED);
    
    // Aggiungere gli ActionListener per i JMenuItem
    ActionListener roleListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          setRoleActions(e.getActionCommand());
          // Aggiornare il testo del JLabel quando viene selezionato un nuovo ruolo
          roleLabel.setText(e.getActionCommand());
      }
    };

    farmerItem.addActionListener(roleListener);
    ownerItem.addActionListener(roleListener);

    // Aggiungere i componenti alla JMenuBar
    roleMenu.add(farmerItem);
    roleMenu.add(ownerItem);
    menuBar.add(roleMenu);
    menuBar.add(new JSeparator(SwingConstants.VERTICAL)); // Aggiungere la riga verticale
    menuBar.add(roleLabel);
    menuBar.add(Box.createHorizontalGlue());
    menuBar.add(placeLabel);
    
    // Impostare il layout del JPanel
    rolePanel.add(menuBar, BorderLayout.NORTH);
    rolePanel.add(buttonPanel, BorderLayout.CENTER);

    // aggiorna i bottoni in base al ruolo selezionato di default
    this.setRoleActions(this.game.getPersons()[0].toString());

    return rolePanel;
  }

  // Pannello Azioni Agricoltore
  private void setRoleActions(String role) {
    if (role == game.getPersons()[0].toString()) {
      this.game.setSelectedPerson(this.game.getPersons()[0]);
    } else if (role == game.getPersons()[1].toString()) {
      this.game.setSelectedPerson(this.game.getPersons()[1]);
    }	

    PlayerActions actions = this.game.getSelectedPerson().getActions();

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;

    // Rimuovere tutti i bottoni dal pannello
    buttonPanel.removeAll();

    for (ActionsManager.Action a : actions.getActions()){
      buttonPanel.add(new JButton(a.toString()), constraints);
    }
    // Aggiornare il pannello
    revalidate();
    repaint();
  }
  
  // Crea pannello di gestione mondo
  private JPanel createWorldPanel(){
    
    worldPanel = new JPanel(new GridLayout(1, 2));
    worldPanel.setPreferredSize(new Dimension(800, 500));
    worldPanel.setBackground(Color.GREEN);
        
    JPanel barn = new JPanel(new GridLayout(1, 1));
    JPanel land = new JPanel(new GridLayout(3, 3));
    
    for(Place i: this.game.getMap().get(1)){
      JButton button = new JButton(i.getType().toString());
      button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              try {
                game.getSelectedPerson().getActions().enter(i);
              } catch (PlaceNotAvailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              };
          }
      });
      land.add(button);
  }
    barn.add(new JButton( this.game.getMap().get(0).get(0).getType().toString()));
    worldPanel.add(land);
    worldPanel.add(barn);    
    
    return worldPanel;
  }

  // display animals when opening animal land else display chunks
  private JPanel createInsideLand(){
    LandAbstract actualPlace = (LandAbstract)this.game.getSelectedPerson().getPlace();

    // create the panel that will contain the elements
    JPanel insideLand = new JPanel(new GridLayout(3, 3));
    insideLand.setPreferredSize(new Dimension(800, 500));
    insideLand.setBackground(Color.GREEN);

    // depending on the type of the place, display the elements
    this.placeLabel.setText(actualPlace.getType().toString());
    if (actualPlace.getElements() != null){
      if (actualPlace.getType() == Places.ANIMAL_LAND){ // if it's an animal land
        for(AnimalAbstract animal : ((AnimalLand)(actualPlace)).getElements()){
          insideLand.add(new JButton(animal.getType().toString()));
        }
      } else if (actualPlace.getType() == Places.PLANT_LAND){ // if it's a plant land
        for(PlantChunk chunk : ((PlantLand)(actualPlace)).getElements()){
          insideLand.add(new JButton(chunk.getPlant().getType().toString()));
        }
      }
    }
    return insideLand;
  }
  
  
  public static void main(String[] args) {
    MainFrame frame = new MainFrame();
    frame.setVisible(true);
    }
  }
   

