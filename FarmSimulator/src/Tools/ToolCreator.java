package Tools;

import Tools.ConcreteTool.Interface.Tool;
import Tools.Factory.WoodFactory;
import Tools.Factory.IronFactory;
import Tools.Factory.ToolFactory;

public class ToolCreator {
    private final int MAX_INVENTORY_SIZE = 4;
    private Tool[] inventory;
    private final WoodFactory woodFactory;
    private final IronFactory ironFactory;

    public ToolCreator(){
        this.inventory   = new Tool[MAX_INVENTORY_SIZE];
        this.woodFactory = new WoodFactory();
        this.ironFactory = new IronFactory();
    }

    private void createStandardInventory(ToolFactory factory){
        this.inventory[0] = factory.createHoe();
        this.inventory[1] = factory.createSickle();
        this.inventory[2] = factory.createScissors();
        this.inventory[3] = factory.createWateringCan();
    }

    public Tool[] getWoodSet(){
        createStandardInventory(woodFactory);
        return this.inventory;
    }
    
    public Tool[] getIronSet(){
        createStandardInventory(ironFactory);
        return this.inventory;
    }
}
