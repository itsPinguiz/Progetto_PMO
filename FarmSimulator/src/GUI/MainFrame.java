package GUI;

import javax.swing.*;
import javax.swing.border.Border;

import Actors.Actions.ActionsManager;
import Actors.Actions.PlayerActions;
import Animal.AnimalAbstract;
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
  private Game game;

  public MainFrame() {
    this.game = new Game();
    setTitle("Farming Simulator");
    setSize(MAX_WIDTH, MAX_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    // Impostazione del layout principale
    Container contentPane = getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
    
    // Aggiunta dei pannelli al contenitore principale
    contentPane.add(this.createRolePanel());
    contentPane.add(this.createWorldPanel());

    // Impostare il ruolo predefinito come Agricoltore
     this.setRoleActions("Agricoltore");
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
    farmerItem = new JMenuItem("Agricoltore");
    ownerItem = new JMenuItem("Proprietario");
    placeLabel = new JLabel("World");// Inizialmente impostato come World
    roleLabel = new JLabel("Agricoltore"); // Inizialmente impostato come Agricoltore
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

    return rolePanel;
  }

  // Pannello Azioni Agricoltore
  private void setRoleActions(String role) {
    if (role == "Agricoltore") {
      this.game.setSelectedPerson(this.game.getPersons()[0]);
    } else if (role == "Proprietario") {
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
        
    JPanel barn = new JPanel();
    JPanel land = new JPanel(new GridLayout(3, 3));
    
    for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i)); // creazione del bottone con il testo numerico corrispondente
            land.add(button); // aggiunta del bottone al pannello principale
        }
    worldPanel.add(land);
    worldPanel.add(barn);    
    
    return worldPanel;
  }

  // display animals when opening animal land else display chunks
  private JPanel createInsideLand(){
    LandAbstract actualPlace = (LandAbstract)this.game.getSelectedPerson().getPlace();

    // create the panel that will contain the elements
    JPanel insideLand = new JPanel(new GridLayout(1, 2));
    insideLand.setPreferredSize(new Dimension(800, 500));
    insideLand.setBackground(Color.GREEN);

    // depending on the type of the place, display the elements
    this.placeLabel.setText(actualPlace.getType().toString());
    if (actualPlace.getElements() != null){
      if (actualPlace.getType() == Places.ANIMAL_LAND){
        for(AnimalAbstract animal : ((AnimalLand)(actualPlace)).getElements()){
          insideLand.add(new JButton(animal.getType().toString()));
        }
      } else if (actualPlace.getType() == Places.PLANT_LAND){
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
   

