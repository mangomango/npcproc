/*
 * Copyright (c) 2016. Created by Bystander from Nexus. License - the same as the one of SkyProc, whatever it is.
 *  I don't really care. Would be nice to be mentioned if you create some derived work
 */

package ffdammit;

import lev.gui.LCheckBox;
import lev.gui.LComboBox;
import skyproc.gui.SPMainMenuPanel;
import skyproc.gui.SPSettingPanel;
import skyproc.gui.SUMGUI;

/**
 * @author Bystander
 */
public class GeneralSettingsPanel extends SPSettingPanel {

    LCheckBox importOnStartup;
    LComboBox<String> preserveProtectionOptions;

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

        preserveProtectionOptions = new LComboBox<>("Protection options", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        preserveProtectionOptions.setSize(260, 60);
        preserveProtectionOptions.addItem("Disabled");
        preserveProtectionOptions.addItem("Files only");
        preserveProtectionOptions.addItem("Protected/Essential -> Essential");
        preserveProtectionOptions.addItem("Protected/Essential -> Protected");
        preserveProtectionOptions.addItem("Essential -> Protected");
        preserveProtectionOptions.addItem("Protected -> Essential");
        preserveProtectionOptions.tie(NBWSaveFile.Settings.PRESERVE_PROTECTION_OPTIONS, SkyProcMain.save, SUMGUI.helpPanel, true);
        setPlacement(preserveProtectionOptions);
        AddSetting(preserveProtectionOptions);

        alignRight();

    }
}
