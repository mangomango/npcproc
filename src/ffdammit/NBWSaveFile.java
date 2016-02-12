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
        Add(Settings.PROCESS_RACE_MODELS, true, true);
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

        helpInfo.put(Settings.PROCESS_RACE_MODELS, "If enabled, the processing of female models in races will be " +
                "turned on, and those having male models will be switched to female ones. \n\n" +
                "A good example of such race record is Orcs");

        helpInfo.put(Settings.GENERAL_SETTINGS,
                "Settings related to this patcher program.");
    }

    // Note that some settings just have help info, and no actual values in
    // initSettings().
    public enum Settings {
        IMPORT_AT_START,
        PROCESS_RACE_MODELS,
        GENERAL_SETTINGS,
    }
}
