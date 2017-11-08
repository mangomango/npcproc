package ffdammit;

import lev.gui.LCheckBox;
import skyproc.gui.SPMainMenuPanel;
import skyproc.gui.SPSettingPanel;
import skyproc.gui.SUMGUI;

public class CombatSettingsPanel extends SPSettingPanel {

    CombatSettingsPanel(SPMainMenuPanel parent_) {
        super(parent_, "Combat Settings", SkyProcMain.headerColor);
    }

    @Override
    protected void initialize() {
        super.initialize();

        LCheckBox processCombatInWater = new LCheckBox("Enemies cross the water", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        processCombatInWater.tie(NBWSaveFile.Settings.COMBAT_IN_WATER, SkyProcMain.save, SUMGUI.helpPanel, true);
        processCombatInWater.setOffset(2);
        processCombatInWater.addShadow();
        setPlacement(processCombatInWater);
        AddSetting(processCombatInWater);
        alignRight();

        LCheckBox processRemoveDisarm = new LCheckBox("Remove disarm shout from NPCs", SkyProcMain.settingsFont, SkyProcMain.settingsColor);
        processRemoveDisarm.tie(NBWSaveFile.Settings.REMOVE_DISARM, SkyProcMain.save, SUMGUI.helpPanel, true);
        processRemoveDisarm.setOffset(2);
        processRemoveDisarm.addShadow();
        setPlacement(processRemoveDisarm);
        AddSetting(processRemoveDisarm);
        alignRight();
    }
}
