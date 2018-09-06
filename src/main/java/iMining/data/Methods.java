package iMining.data;

import iMining.core.Core;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Random;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.rev317.min.api.methods.Skill;

import java.awt.event.KeyEvent;

public class Methods
{
    //VARIABLES
    private static int RockID;

    //WOODCUTING
    public static int CheckRockToMine ()
    {
        if (Core.GUIore == "Progressive")
        {
            if (Skill.MINING.getRealLevel() < 15)
            {
                RockID = Constants.COPPER_ROCK_ID;
            }
            else if (Skill.MINING.getRealLevel() >= 15 && Skill.MINING.getRealLevel() < 20)
            {
                RockID = Constants.IRON_ROCK_ID;
            }
            else if (Skill.MINING.getRealLevel() >= 20 && Skill.MINING.getRealLevel() < 40)
            {
                RockID = Constants.TIN_ROCK_ID;
            }
            else if (Skill.MINING.getRealLevel() >= 40 && Skill.MINING.getRealLevel() < 55)
            {
                RockID = Constants.GOLD_ROCK_ID;
            }
            else if (Skill.MINING.getRealLevel() >= 55 && Skill.MINING.getRealLevel() < 70)
            {
                RockID = Constants.MITHRIL_ROCK_ID;
            }
            else if (Skill.MINING.getRealLevel() >= 70)
            {
                RockID = Constants.ADAMANT_ROCK_ID;
            }
        }
        else if (Core.GUIore == "Copper")
        {
            RockID = Constants.COPPER_ROCK_ID;
        }
        else if (Core.GUIore == "Iron")
        {
            RockID = Constants.IRON_ROCK_ID;
        }
        else if (Core.GUIore == "Silver")
        {
            RockID = Constants.TIN_ROCK_ID;
        }
        else if (Core.GUIore == "Gold")
        {
            RockID = Constants.GOLD_ROCK_ID;
        }
        else if (Core.GUIore == "Mithril")
        {
            RockID = Constants.MITHRIL_ROCK_ID;
        }
        else if (Core.GUIore == "Adamant")
        {
            RockID = Constants.ADAMANT_ROCK_ID;
        }
        else if (Core.GUIore == "Rune (ore)")
        {
            RockID = Constants.RUNITE_ROCK_ID;
        }
        else if (Core.GUIore == "Rune (bar)")
        {
            RockID = Constants.RUNE_BAR_ROCK_ID;
        }

        return RockID;
    }

    //FUNCTIONAL
    public static String stripNonDigits(final CharSequence input)
    {
        final StringBuilder sb = new StringBuilder(input.length());

        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    //ANTI AFK
    public static void ANTIAFK()
    {
        Logger.addMessage("iArcade: Performing antiAFK", true);
        final int[]  keys   = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT };

        if (Random.between(0,4) == 2)
        {
            Core.CurrentStatus = "Anti AFK";
            int keyCode = keys[Random.between(0, keys.length)];
            Keyboard.getInstance().pressKey(keyCode);
            Time.sleep(Random.between(800,1500));
            Keyboard.getInstance().releaseKey(keyCode);
            Core.CurrentStatus = "Waiting...";
        }
    }


}