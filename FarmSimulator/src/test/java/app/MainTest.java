package app;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import model.Model;
import model.actors.actions.ActionsManager.Action;
import model.actors.person.Farmer;
import model.actors.person.PersonAbstract.Role;
import model.item.ItemType;
import model.place.land.LandAbstract;
import model.place.land.PlantLand;
import model.place.land.AnimalLand;
import model.place.land.chunks.AnimalChunk;
import model.place.land.chunks.PlantChunk;

public class MainTest {

    @Test
    public void plantedCarrot() throws Exception{

        Model model = null;
        model = new Model();


        model.setSelectedPerson(model.getPlayers()[0]); // select farmer
        assertEquals(Role.FARMER, model.getSelectedPerson().getRole()); // assert that the selected person is Farmer
        model.getSelectedPerson().getActions().enter(model.getMap().get(1).get(0)); // enter plant land
        assertEquals(model.getMap().get(1).get(0), model.getSelectedPerson().getPlace()); // assert that the farmer is in land


        final Model tmpModel = model;
        Farmer f = (Farmer)model.getSelectedPerson();
        model.setSelectedItem(f.getInventory().getInventory().get(2)); // get the hoe
        model.getSelectedPerson().getActions().executeAction(Action.PLOW_ALL,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                add(tmpModel.getSelectedItem());
                                                                                                add(tmpModel.getMap());}});


        PlantLand land = (PlantLand) model.getMap().get(1).get(0);
        Iterator<PlantChunk> iterator = land.iterator();

        
        while (iterator.hasNext()) {
            PlantChunk p = iterator.next();

                model.getSelectedPerson().getActions().enter(p);

            assertEquals(true, p.getDirtStatus()); // assert that all the chunks are plowed
        }

        f = (Farmer)model.getSelectedPerson();
        model.setSelectedItem(f.getInventory().getInventory().get(0)); // get the carrot
        model.getSelectedPerson().getActions().executeAction(Action.PLANT,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                             add(tmpModel.getSelectedItem());
                                                                                             add(tmpModel.getMap());}});
        assertEquals(ItemType.Plants.CARROT,((PlantChunk)(f.getPlace())).getPlant().getType()); // assert that the carrot is planted
    }

    @Test
    public void addedAnimalToTheChunk() throws Exception{
        Model model = null;
        model = new Model();

        model.setSelectedPerson(model.getPlayers()[0]); // select farmer
        assertEquals(Role.FARMER, model.getSelectedPerson().getRole()); // assert that the selected person is Farmer
        model.getSelectedPerson().getActions().enter(((AnimalLand)(model.getMap().get(1).get(2))).getElements().get(0)); // enter animal land
        assertEquals(((AnimalLand)(model.getMap().get(1).get(2))).getElements().get(0), model.getSelectedPerson().getPlace()); // assert that the farmer is in land

        final Model tmpModel = model;
        Farmer f = (Farmer)model.getSelectedPerson();

        model.setSelectedItem(f.getInventory().getInventory().get(1)); // get the chicken
        model.getSelectedPerson().getActions().executeAction(Action.ADD_ANIMAL,new ArrayList<>(){{add(tmpModel.getSelectedPerson().getPlace());
                                                                                                  add(tmpModel.getSelectedItem());
                                                                                                  add(tmpModel.getMap());}});
        assertEquals(ItemType.Animals.CHICKEN, ((AnimalChunk)(f.getPlace())).getAnimal().getType()); // assert that the animal has been added in the chunk
    }

    

    
}
