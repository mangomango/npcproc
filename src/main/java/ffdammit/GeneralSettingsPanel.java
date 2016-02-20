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
    LCheckBox processOppositeGenderAnims;
    LCheckBox processRaces;
    LCheckBox processRaceHeights;
    LCheckBox processFaceData;
    LCheckBox preserveEssential;

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

        processOppositeGenderAnims = new LCheckBox("Process Opposite Gender Animations flag", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        processOppositeGenderAnims.tie(NBWSaveFile.Settings.PROCESS_OPPOSITE_GENDER_ANIMS, SkyProcMain.save, SUMGUI.helpPanel, true);
        processOppositeGenderAnims.setOffset(2);
        processOppositeGenderAnims.addShadow();
        setPlacement(processOppositeGenderAnims);
        AddSetting(processOppositeGenderAnims);

        alignRight();

        processRaces = new LCheckBox("Process race models", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        processRaces.tie(NBWSaveFile.Settings.PROCESS_RACE_MODELS, SkyProcMain.save, SUMGUI.helpPanel, true);
        processRaces.setOffset(2);
        processRaces.addShadow();
        setPlacement(processRaces);
        AddSetting(processRaces);

        alignRight();

        processRaceHeights = new LCheckBox("Process race heights", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        processRaceHeights.tie(NBWSaveFile.Settings.PROCESS_RACE_HEIGHTS, SkyProcMain.save, SUMGUI.helpPanel, true);
        processRaceHeights.setOffset(2);
        processRaceHeights.addShadow();
        setPlacement(processRaceHeights);
        AddSetting(processRaceHeights);

        alignRight();

        processFaceData = new LCheckBox("Process face data", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        processFaceData.tie(NBWSaveFile.Settings.PROCESS_FACE_VISUALS, SkyProcMain.save, SUMGUI.helpPanel, true);
        processFaceData.setOffset(2);
        processFaceData.addShadow();
        setPlacement(processFaceData);
        AddSetting(processFaceData);

        alignRight();

        preserveEssential = new LCheckBox("Preserve protection", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        preserveEssential.tie(NBWSaveFile.Settings.PRESERVE_ESSENTIAL_PROTECTED_FLAGS, SkyProcMain.save, SUMGUI.helpPanel, true);
        preserveEssential.setOffset(2);
        preserveEssential.addShadow();
        setPlacement(preserveEssential);
        AddSetting(preserveEssential);

        alignRight();
    }
}
