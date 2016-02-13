/*
 * Copyright (c) 2016. Created by Bystander from Nexus. License - the same as the one of SkyProc, whatever it is.
 *  I don't really care. Would be nice to be mentioned if you create some derived work
 */

package ffdammit;

import lev.gui.LCheckBox;
import skyproc.gui.SPMainMenuPanel;
import skyproc.gui.SPSettingPanel;
import skyproc.gui.SUMGUI;

/**
 * @author Bystander
 */
public class GeneralSettingsPanel extends SPSettingPanel {

    LCheckBox importOnStartup;
    LCheckBox processRaces;

    public GeneralSettingsPanel(SPMainMenuPanel parent_) {
        super(parent_, "General Settings", SkyProcMain.headerColor);
    }

    @Override
    protected void initialize() {
        super.initialize();

        importOnStartup = new LCheckBox("Import Mods on Startup", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        importOnStartup.tie(NBWSaveFile.Settings.IMPORT_AT_START, SkyProcMain.save, SUMGUI.helpPanel, true);
        importOnStartup.setOffset(2);
        importOnStartup.addShadow();
        setPlacement(importOnStartup);
        AddSetting(importOnStartup);

        alignRight();

        processRaces = new LCheckBox("Process race models", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        processRaces.tie(NBWSaveFile.Settings.PROCESS_RACE_MODELS, SkyProcMain.save, SUMGUI.helpPanel, true);
        processRaces.setOffset(2);
        processRaces.addShadow();
        setPlacement(processRaces);
        AddSetting(processRaces);

        alignRight();
    }
}
