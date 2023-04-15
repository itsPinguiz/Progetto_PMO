package view.worldpanel;

import javax.swing.*;

import model.Model;
import model.exceptions.CustomExceptions.ActionNotAvailableException;
import model.exceptions.CustomExceptions.PlaceNotAvailableException;
import model.item.animal.AnimalAbstract;
import model.item.plants.PlantAbstract;
import model.place.Places;
import model.place.land.AnimalLand;
import model.place.land.LandAbstract;
import model.place.land.PlantLand;
import model.place.land.chunks.AnimalChunk;
import model.place.land.chunks.PlantChunk;
import view.View;

import java.awt.*;

public class LandView {
    private Model model;
    private View view;
    GridBagConstraints constraints;

    public LandView(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    
    public JPanel createInsideLand(){
        /*
        * This method creates the panel that will be displayed when the user clicks on a land
        */
        // get the actual place
        LandAbstract actualPlace = (LandAbstract)this.model.getSelectedPerson().getPlace();

        // create the panel that will contain the elements
        JPanel insideLand = new JPanel(new GridLayout(3, 3,30,30));
        insideLand.setPreferredSize(new Dimension(800, 500));

        // depending on the type of the place, display the elements
        if (actualPlace.getElements() != null){
        // if it's an animal land
        if (actualPlace.getType() == Places.ANIMAL_LAND){ 
            for(AnimalChunk animalChunk : ((AnimalLand)(actualPlace)).getElements()){
            JButton button = new JButton((animalChunk.getAnimal() == null)? "Empty" : animalChunk.getAnimal().getType().toString());
            insideLand.add(button);
            button.addActionListener(view.getWorldPanelView().changePlaceListener(this,"createAnimalChunkPanel", animalChunk,true,false));
            }
        } // if it's a plant land
        else if (actualPlace.getType() == Places.PLANT_LAND){ 
            for(PlantChunk chunk : ((PlantLand)(actualPlace)).getElements()){
            JButton button = new JButton((chunk.getPlant() == null )? "Empty" : chunk.getPlant().getType().toString());
            insideLand.add(button);
            button.addActionListener(view.getWorldPanelView().changePlaceListener(this,"createPlantChunkPanel",chunk,true,false));
            }
        }}

        // add the exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(view.getWorldPanelView().changePlaceListener(view.getWorldPanelView(),"createWorldPanel", null, false, true));
        insideLand.add(exitButton);
        return insideLand;
        }

        public JPanel createPlantChunkPanel(PlantChunk chunk) throws PlaceNotAvailableException, ActionNotAvailableException{
            /*
            * This method creates the panel that will be displayed when the player enters a chunk
            */
            // get the plant inside the chunk
            PlantAbstract plant = chunk.getPlant();
            
            // create the panel that will contain the elements
            JPanel chunkPanel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
        
            // set the panel's size 
            chunkPanel.setPreferredSize(new Dimension(800, 500));
        
            // set the label's text
            JLabel plantLabel = new JLabel("<html><div style='font-size:16px;'>" + ((plant == null)?"Empty": plant.getType().toString())+
                            "</div><div style='font-size:12px;'>Life Stage: " + ((plant == null)?"No Plant": plant.getLifeStage().toString()) +
                            "<br>Water Level: </br>" + chunk.getWaterLevel() +
                            "<br>Fertilization Level: </br>" + chunk.getFertilizationLevel() +
                            "<br>Plowed :</br> " + ((chunk.getDirtStatus()== true)?"Yes":"No") +
                            "</div></html>");
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(10, 10, 10, 10);
            c.anchor = GridBagConstraints.CENTER;
            chunkPanel.add(plantLabel, c);
            
            // add the exit button
            JButton exitButton = new JButton("Exit");
            exitButton.setPreferredSize(new Dimension(100, 50));
            exitButton.addActionListener(view.getWorldPanelView().changePlaceListener(this,"createInsideLand", chunk.getLand(),false,false));
            c.gridx = 1;
            c.gridy = 3;
            c.insets = new Insets(0, 0, 30,0 );
            c.anchor = GridBagConstraints.WEST;
            chunkPanel.add(exitButton, c);
        
            // Update the panel
            view.getWorldPanelView().getWorldPanel().revalidate();
            view.getWorldPanelView().getWorldPanel().repaint();
            return chunkPanel;
        }
        
        

    public JPanel createAnimalChunkPanel(AnimalChunk chunk) throws PlaceNotAvailableException, ActionNotAvailableException{
        /*
        * This method creates the panel that will be displayed when the player enters a chunk
        */
        // get the animal inside the chunk
        AnimalAbstract animal = chunk.getAnimal();
        
        // create the panel that will contain the elements
        JPanel chunkPanel = new JPanel(new GridLayout(1, 2));
        JLabel animalLabel = new JLabel();
        
        // set the panel's size 
        chunkPanel.setPreferredSize(new Dimension(800, 500));

        // set the label's text
        animalLabel.setText("<html><divstyle = 'font-size:16px;'>" + ((animal == null)? "Empty" : animal.getType().toString()) +
                            "</div><div style='font-size:12px;' Status>" + ((animal == null)? "No status" : animal.getStatus()) +
                            "<br>Hunger Level:<br>" + ((animal == null)? "No hunger" : animal.getHunger()));

        // add the label to the panel
        chunkPanel.add(animalLabel);
        
        // add the exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(view.getWorldPanelView().changePlaceListener(this,"createInsideLand", chunk.getLand(),false,false));
        // add the exit button to the panel
        chunkPanel.add(exitButton);

        // Update the panel
        view.getWorldPanelView().getWorldPanel().revalidate();
        view.getWorldPanelView().getWorldPanel().repaint();
        return chunkPanel;
    }
}
