package ffdammit;

import lev.gui.LCheckBox;
import skyproc.gui.SPMainMenuPanel;
import skyproc.gui.SPSettingPanel;
import skyproc.gui.SUMGUI;

public class CombatSettingsPanel extends SPSettingPanel {

    LCheckBox processCombatInWater;

    public CombatSettingsPanel(SPMainMenuPanel parent_) {
        super(parent_, "Combat Settings", SkyProcMain.headerColor);
    }

    @Override
    protected void initialize() {
        super.initialize();

        processCombatInWater = new LCheckBox("Enemies cross the water", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        processCombatInWater.tie(NBWSaveFile.Settings.COMBAT_IN_WATER, SkyProcMain.save, SUMGUI.helpPanel, true);
        processCombatInWater.setOffset(2);
        processCombatInWater.addShadow();
        setPlacement(processCombatInWater);
        AddSetting(processCombatInWater);
        alignRight();

    }
}
