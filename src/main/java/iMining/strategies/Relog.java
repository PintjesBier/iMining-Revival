package iMining.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Menu;

import java.awt.event.KeyEvent;

import static org.rev317.min.api.methods.Game.isLoggedIn;

/**
 * Created by Tristan on 3/09/2018.
 */
public class Relog implements Strategy {
    @Override
    public boolean activate() {
        return !Game.isLoggedIn();
    }

    @Override
    public void execute() {
        if(!isLoggedIn()) {
            Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return isLoggedIn();
                }
            }, 10000);
        } else if(Interfaces.getOpenInterfaceId() == 56000) {
            Menu.sendAction(200, 0, 0, 56002, 1);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Interfaces.getOpenInterfaceId() == -1 && Game.isLoggedIn();
                }
            }, 5000);

            Time.sleep(3000, 4000);

        }

    }
}
