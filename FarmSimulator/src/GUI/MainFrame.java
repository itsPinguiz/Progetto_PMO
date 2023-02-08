package GUI;

import javax.swing.*;
import java.awt.*;

//Interface Testing
public class MainFrame extends JFrame {
  
  private JComboBox<String> roleSelection;
  private JPanel mainPanel;
  private CardLayout cardLayout;
  
  public MainFrame() {
    setTitle("Farming Simulator");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Creazione del menu a tendina per selezionare il ruolo
    JMenuBar menuBar = new JMenuBar();
    roleSelection = new JComboBox<>(new String[]{"Proprietario", "Agricoltore"});
    menuBar.add(roleSelection);
    setJMenuBar(menuBar);
    
    // Creazione delle schede per i diversi ruoli
    mainPanel = new JPanel();
    cardLayout = new CardLayout();
    mainPanel.setLayout(cardLayout);
    
    mainPanel.add(createOwnerPanel(), "Proprietario");
    mainPanel.add(createFarmerPanel(), "Agricoltore");
    mainPanel.add(createDepotPanel(), "Deposito");
    mainPanel.add(createShopPanel(), "Negozio");
    mainPanel.add(createStoragePanel(), "Inventario");
    
    add(mainPanel);
    
    // Cambiamento di scheda in base alla selezione del ruolo
    roleSelection.addActionListener(e -> {
      cardLayout.show(mainPanel, (String) roleSelection.getSelectedItem());
    });
  }
  
  private JPanel createOwnerPanel() {
    JPanel panel = new JPanel();
    panel.add(new JButton("Acquista terreno"));
    panel.add(new JButton("Vendi terreno"));
    panel.add(new JButton("Acquista risorse"));
    panel.add(new JButton("Vendi risorse"));
    return panel;
  }
  
  private JPanel createFarmerPanel() {
    JPanel panel = new JPanel();
    panel.add(new JButton("Lavora il terreno"));
    panel.add(new JButton("Pianta"));
    panel.add(new JButton("Alleva animali"));
    return panel;
  }
  
  private JPanel createDepotPanel() {
    JPanel panel = new JPanel();
    panel.add(new JList<>(new String[]{"Risorse", "Strumenti", "Animali"}));
    return panel;
  }
  
  private JPanel createShopPanel() {
    JPanel panel = new JPanel();
    panel.add(new JButton("Acquista semi"));
    panel.add(new JButton("Acquista risorse"));
    panel.add(new JButton("Acquista animali"));
    panel.add(new JButton("Acquista terreno"));
    return panel;
  }

  private JPanel createStoragePanel() {
    JPanel storageScreen = new JPanel();
    storageScreen.setLayout(new BorderLayout());
    JList resourcesList = new JList();
    JTable toolsTable = new JTable();
    JTable animalsTable = new JTable();
    storageScreen.add(resourcesList, BorderLayout.WEST);
    storageScreen.add(toolsTable, BorderLayout.EAST);
    return storageScreen;
  }
  
  public static void main(String[] args) {
    MainFrame frame = new MainFrame();
    frame.setVisible(true);
    }
}
   

