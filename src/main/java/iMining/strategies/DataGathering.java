package iMining.strategies;

import iMining.core.Core;
import org.parabot.core.ui.Logger;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Skill;

/**
 * Created by Tristan on 21/03/2018.
 */
public class DataGathering implements Strategy
{
    @Override
    public boolean activate() {
        return !Core.DataGathered;
    }

    @Override
    public void execute()
    {
        Logger.addMessage("iMining: gathering data", true);
        Core.CurrentStatus = "Gathering data";

        Core.StartLevel = Skill.MINING.getLevel();

        Core.DataGathered = true;
        Logger.addMessage("iMining: Data gathered", true);
        Core.CurrentStatus = "Data gathered";
    }
}
