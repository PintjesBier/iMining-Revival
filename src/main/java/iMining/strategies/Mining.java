package iMining.strategies;

import iMining.core.Core;
import iMining.data.Constants;
import iMining.data.Methods;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;

import java.util.ArrayList;
import java.util.List;

import static org.rev317.min.api.methods.Players.getMyPlayer;

/**
 * Created by Tristan on 26/08/2018.
 */
public class Mining implements Strategy {
    @Override
    public boolean activate() {
        return Game.isLoggedIn() && !Inventory.isFull();
    }

    @Override
    public void execute()
    {
        //VARIABLES
        List<Integer> RandomDrops = new ArrayList<Integer>();
        RandomDrops.add(Constants.AMETHYST_ID);
        RandomDrops.add(Constants.MBOX_ID);
        RandomDrops.add(Constants.PROSPECTOR_HAT_ID);
        RandomDrops.add(Constants.PROSPECTOR_TOP_ID);
        RandomDrops.add(Constants.PROSPECTOR_BOTTOM_ID);
        RandomDrops.add(Constants.PROSPECTOR_BOOTS_ID);

        //WOODCUTTING CLASS
        if (getMyPlayer().getAnimation() == -1)
        {
            Core.CurrentStatus = "Mining rocks";
            Logger.addMessage("iMining: Mining rocks", true);
            Core.Rock = SceneObjects.getClosest(Methods.CheckRockToMine());
            Core.Rock.interact(SceneObjects.Option.MINE);

            //WAIT UNTIL ARRIVED AT ROCK
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Core.Rock.distanceTo() <= 2;
                }
            }, 5000);

            Core.Rock = SceneObjects.getClosest(Methods.CheckRockToMine());
            Core.Rock.interact(SceneObjects.Option.CHOP_DOWN);

            //WAIT FOR ACTION
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return getMyPlayer().getAnimation() == Constants.PICKAXE_SWINGING_ID;
                }
            }, 2000);

            //WAIT FOR ACTION
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return getMyPlayer().getAnimation() == -1;
                }
            }, 7500);
        }

        //LOOP THROUGH RANDOM DROPS
        for (final int i : RandomDrops) {
            if ((GroundItems.getClosest(i) != null) && GroundItems.getClosest(i).distanceTo() <= 10) {
                try {
                    Core.CurrentStatus = "Taking random drop";
                    Logger.addMessage("iMining: Taking random drop", true);
                    GroundItems.getClosest(i).take();

                    //WAIT FOR ACTION
                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Inventory.contains(i);
                        }
                    }, 2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
