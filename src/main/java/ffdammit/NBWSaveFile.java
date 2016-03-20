/*
 * Copyright (c) 2016. Created by Bystander from Nexus. License - the same as the one of SkyProc, whatever it is.
 *  I don't really care. Would be nice to be mentioned if you create some derived work
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ffdammit;

import skyproc.SkyProcSave;

/**
 * @author Bystander
 */
public class NBWSaveFile extends SkyProcSave {

    @Override
    protected void initSettings() {
        //  The Setting,	    The default value,	    Whether or not it changing means a new patch should be made
        Add(Settings.IMPORT_AT_START, true, false);
        Add(Settings.PROCESS_OPPOSITE_GENDER_ANIMS, true, true);
        Add(Settings.PROCESS_RACE_MODELS, true, true);
        Add(Settings.PROCESS_RACE_HEIGHTS, true, true);
        Add(Settings.PROCESS_FACE_VISUALS, false, true);
        Add(Settings.PRESERVE_PROTECTION_OPTIONS, 0, true);
        Add(Settings.COMBAT_IN_WATER, false, true);
    }

    @Override
    protected void initHelp() {

        helpInfo.put(Settings.IMPORT_AT_START,
                "If enabled, the program will begin importing your mods when the program starts.\n\n"
                        + "If turned off, the program will wait until it is necessary before importing.\n\n"
                        + "NOTE: This setting will not take effect until the next time the program is run.\n\n"
                        + "Benefits:\n"
                        + "- Faster patching when you close the program.\n"
                        + "- More information displayed in GUI, as it will have access to the records from your mods."
                        + "\n\n"
                        + "Downsides:\n"
                        + "- Having this on might make the GUI respond sluggishly while it processes in the "
                        + "background.");

        helpInfo.put(Settings.PROCESS_OPPOSITE_GENDER_ANIMS, "Remove Opposite Gender Animations flag from female NPCs");

        helpInfo.put(Settings.PROCESS_RACE_MODELS, "If enabled, the processing of female models in races will be " +
                "turned on, and those having male models will be switched to female ones. \n\n" +
                "A good example of such race record is Orcs");

        helpInfo.put(Settings.PROCESS_RACE_HEIGHTS, "Changes male/female heights according to the provided races.json file");

        helpInfo.put(Settings.PROCESS_FACE_VISUALS, "Carry over face data from ESPs specified in faces.txt. \n\n" +
                "Ensures that NPCs get correct appearance even if the load order has been messed up");

        helpInfo.put(Settings.PRESERVE_PROTECTION_OPTIONS, "Set rules for conflict resolution, when NPC has both Essential and Protected flags in the load order\n\n" +
                "Alternatively you can convert all essential NPCs to protected and vice versa");

        helpInfo.put(Settings.COMBAT_IN_WATER, "NPCs and creatures cross the water to chase enemies");

        helpInfo.put(Settings.GENERAL_SETTINGS,
                "Settings related to this patcher program.");

        helpInfo.put(Settings.COMBAT_SETTINGS, "Settings related to combat tweaks for races and NPCs");

        helpInfo.put(Settings.VISUAL_SETTINGS, "Visual/appearance tweaks for races and NPCs");
    }

    // Note that some settings just have help info, and no actual values in
    // initSettings().
    public enum Settings {
        IMPORT_AT_START,
        PROCESS_OPPOSITE_GENDER_ANIMS,
        PROCESS_RACE_MODELS,
        PROCESS_RACE_HEIGHTS,
        PROCESS_FACE_VISUALS,
        PRESERVE_PROTECTION_OPTIONS,
        COMBAT_IN_WATER,
        GENERAL_SETTINGS,
        COMBAT_SETTINGS,
        VISUAL_SETTINGS
    }
}
