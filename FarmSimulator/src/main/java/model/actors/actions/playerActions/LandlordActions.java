package model.actors.actions.playerActions;

import java.util.ArrayList;

import model.Constants;
import model.actors.person.Landlord;
import model.exceptions.CustomExceptions.*;
import model.item.Item;
import model.place.barn.market.Market;
import model.place.land.LandAbstract;

/**
 * Class that represents the actions that the landlord can perform
 */
public class LandlordActions extends PlayerActions<Landlord>{
    /**
     * Constructor
     * @param landlord Landlord
     */
    public LandlordActions(Landlord landlord){
        super(landlord);
    }

    /**
     * Buys an item from the market
     * @throws NoItemFoundException
     * @throws InventoryIsFullException
     * @throws CloneNotSupportedException
     * @throws NoEnoughMoneyException
     * @throws CannotBuyItemException
     */
    public void buy_item() throws NoItemFoundException, 
                                  InventoryIsFullException, 
                                  CloneNotSupportedException,
                                  NoEnoughMoneyException,
                                  CannotBuyItemException {
        Item item;
        LandAbstract land;
        Landlord landlord = (Landlord)this.person;
        Market market = (Market)argument.getArg1();
        ArrayList<LandAbstract> lands =(ArrayList<LandAbstract>)(argument.getArg3().getLands());
        

        // check if the item is a land or not
        if (argument.getArg2() instanceof LandAbstract && !lands.contains(argument.getArg2())){
            land = (LandAbstract)argument.getArg2();

            if (lands.size()<10 && land.getPrice() <= landlord.getBalance()){
                lands.add(land);
                landlord.setBalance(- Constants.BASE_LAND_PRICE);
            }
            
        } else if (argument.getArg2() instanceof Item && market.getItemShop().getItemInventory().contains(argument.getArg2())){
            item = (Item)argument.getArg2();

            // add it to the barn
            market.getBarn().getBarnInventory().addItem(market.buyItem(item, landlord.getBalance()));
            // buy from maketz 
            // remove the money from the balance
            landlord.setBalance(- item.getPrice());
        } else {
            throw new CannotBuyItemException(argument.getArg2());
        }
    }

    /**
     * Sells an item to the market
     * @throws NoItemFoundException
     * @throws CloneNotSupportedException
     * @throws NoSellableLandException
     * @throws CannotSellItemException
     * @throws NotEnoughItemsException
     */
    public void sell_item() throws NoItemFoundException, 
                                   CloneNotSupportedException,
                                   NoSellableLandException,
                                   CannotSellItemException,
                                   NotEnoughItemsException{
        /*
         * Method to sell item
         * to the market
         */
        Market market = (Market)argument.getArg1();
        ArrayList<LandAbstract> lands =(ArrayList<LandAbstract>)(argument.getArg3().getLands());

        // check if the item is a land or not
         if (argument.getArg2() instanceof LandAbstract && lands.contains(argument.getArg2())){
            LandAbstract land = (LandAbstract)argument.getArg2();
            Landlord landlord = (Landlord)this.person;

            lands.remove(land);

            // add money to the balance
            landlord.setBalance(Constants.LAND_SELL_PRICE);
            
        } else if (argument.getArg2() instanceof Item && market.getBarn().getBarnInventory().getItemInventory().contains(argument.getArg2())){
            Item item = (Item)argument.getArg2();
            Landlord landlord = (Landlord)this.person;

            // remove item from the barn
            market.getBarn().getBarnInventory().removeItem(item,1);
            // add money to the balance
            landlord.setBalance(item.getPrice());
        } else {
            throw new CannotSellItemException(argument.getArg2());
        }
    }
}
