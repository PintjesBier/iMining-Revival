package iMining.strategies;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import iMining.core.Core;
import iMining.data.Constants;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;

/**
 * Created by Tristan on 26/08/2018.
 */
public class Banking implements Strategy {
    @Override
    public boolean activate() {
        return Game.isLoggedIn() && Inventory.isFull();
    }

    @Override
    public void execute() {
        if (Inventory.isFull()) {
            //UPDATE
            Logger.addMessage("iMining: Banking", true);
            Core.CurrentStatus = "Banking";

            if (SceneObjects.getClosest(Constants.BANK_CHEST_ID) != null)
            {
                Core.BankChest = SceneObjects.getClosest(Constants.BANK_CHEST_ID);

                //OPEN BANK
                Core.BankChest.interact(SceneObjects.Option.USE);
            }
            else if (Bank.getBank().distanceTo() <= 20)
            {
                Bank.open();

            }

            Time.sleep(750);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Bank.isOpen();
                }
            }, 7000);

            if (Bank.isOpen()) {
                Bank.depositAllExcept(Constants.IRON_PICKAXE_ID, Constants.RUNE_PICKAXE_ID, Constants.INFERNAL_PICKAXE_ID);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return !Inventory.contains(Constants.COPPER_ORE_ID, Constants.IRON_ORE_ID, Constants.SILVER_ORE_ID, Constants.GOLD_ORE_ID, Constants.MITHRIL_ORE_ID, Constants.ADAMANT_ORE_ID, Constants.RUNITE_ORE_ID, Constants.RUNE_BAR_ID);
                    }
                }, 5000);

                Bank.close();
            }
        }

    }
}
