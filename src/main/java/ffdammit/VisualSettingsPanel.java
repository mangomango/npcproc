package ffdammit;

import lev.gui.LCheckBox;
import skyproc.gui.SPMainMenuPanel;
import skyproc.gui.SPSettingPanel;
import skyproc.gui.SUMGUI;

public class VisualSettingsPanel extends SPSettingPanel {
    LCheckBox processOppositeGenderAnims;
    LCheckBox processRaces;
    LCheckBox processRaceHeights;
    LCheckBox processFaceData;

    public VisualSettingsPanel(SPMainMenuPanel parent_) {
        super(parent_, "Visual Settings", SkyProcMain.headerColor);
    }

    @Override
    protected void initialize() {
        super.initialize();
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

    }
}
