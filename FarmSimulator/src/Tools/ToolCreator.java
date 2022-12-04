package Tools;

import Tools.ConcreteTool.Interface.Tool;
import Tools.Factory.IronFactory;
import Tools.Factory.WoodFactory;

public class ToolCreator {
    private Tool[] inventory = new Tool[4];
    private final WoodFactory woodFactory = new WoodFactory();
    private Tool Hoe;
    private Tool Sickle;
    private Tool Scissors;
    private Tool WateringCan;

    public void createStandardInventory(){
        this.Hoe          = woodFactory.createHoe();
        this.Sickle       = woodFactory.createSickle();
        this.Scissors     = woodFactory.createScissors();
        this.WateringCan  = woodFactory.createWateringCan();
    }

    public void storeStandardItem(){
        this.inventory[0] = this.Hoe;
        this.inventory[1] = this.Scissors;
        this.inventory[2] = this.Sickle;
        this.inventory[3] = this.WateringCan;
    }
    
    public Tool[] getStandardInventory(){
        createStandardInventory();
        storeStandardItem();
        return this.inventory;
    }
}
