/*
 * Copyright (c) 2016. Created by Bystander from Nexus. License - the same as the one of SkyProc, whatever it is.
 *  I don't really care. Would be nice to be mentioned if you create some derived work
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ffdammit;

import lev.gui.LTextPane;
import skyproc.gui.SPMainMenuPanel;
import skyproc.gui.SPSettingPanel;

/**
 * @author Bystander
 */
public class WelcomePanel extends SPSettingPanel {

    LTextPane introText;

    public WelcomePanel(SPMainMenuPanel parent_) {
        super(parent_, SkyProcMain.myPatchName, SkyProcMain.headerColor);
    }

    @Override
    protected void initialize() {
        super.initialize();

        introText = new LTextPane(settingsPanel.getWidth() - 40, 400, SkyProcMain.settingsColor);
        introText.setText(SkyProcMain.welcomeText);
        introText.setEditable(false);
        introText.setFont(SkyProcMain.settingsFont);
        introText.setCentered();
        setPlacement(introText);
        Add(introText);

        alignRight();
    }
}
