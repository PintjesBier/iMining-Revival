package iMining.strategies;

import iMining.core.Core;
import iMining.data.Constants;
import iMining.data.Methods;
import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.accessors.Ground;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.SceneObject;
import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Method;
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
    public void execute() {
        //WOODCUTTING CLASS
        if (getMyPlayer().getAnimation() == -1) {
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
        if ((GroundItems.getClosest(Constants.AMETHYST_ID) != null) && GroundItems.getClosest(Constants.AMETHYST_ID).distanceTo() <= 20) {
            try {
                Core.CurrentStatus = "Taking random drop";
                Logger.addMessage("iMining: Taking random drop", true);
                GroundItems.getClosest(Constants.AMETHYST_ID).take();

                //WAIT FOR ACTION
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Inventory.contains(Constants.AMETHYST_ID);
                    }
                }, 2000);
                Time.sleep(550, 850);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

