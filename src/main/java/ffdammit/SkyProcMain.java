/*
 * Copyright (c) 2016. Created by Bystander from Nexus. License - the same as the one of SkyProc, whatever it is.
 *  I don't really care. Would be nice to be mentioned if you create some derived work
 */

package ffdammit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ffdammit.NBWSaveFile.Settings;
import ffdammit.dto.RaceData;
import lev.gui.LSaveFile;
import skyproc.*;
import skyproc.genenums.Gender;
import skyproc.gui.SPMainMenuPanel;
import skyproc.gui.SUM;
import skyproc.gui.SUMGUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bystander
 */
public class SkyProcMain implements SUM {

    /*
     * The important functions to change are:
     * - getStandardMenu(), where you set up the GUI
     * - runChangesToPatch(), where you put all the processing code and add records to the output patch.
     */

    public static String myPatchName = "NPCProc";
    public static String authorName = "Bystander";
    public static String version = "1.5";
    public static String welcomeText = "Removes opposite gender animations flag from all NPCs, optionally fixes races models, heights";
    public static String descriptionToShowInSUM = welcomeText;
    public static Color headerColor = new Color(66, 181, 184);  // Teal
    public static Color settingsColor = new Color(72, 179, 58);  // Green
    public static Font settingsFont = new Font("Serif", Font.BOLD, 15);
    public static SkyProcSave save = new NBWSaveFile();
    public static String racesDataPath = "races.json";
    /*
     * The types of records you want your patcher to import. Change this to
     * customize the import to what you need.
     */
    GRUP_TYPE[] importRequests = new GRUP_TYPE[]{
            GRUP_TYPE.NPC_,
            GRUP_TYPE.RACE
    };

    // Do not write the bulk of your program here
    // Instead, write your patch changes in the "runChangesToPatch" function
    // at the bottom
    public static void main(String[] args) {
        try {
            SPGlobal.createGlobalLog();
            SUMGUI.open(new SkyProcMain(), args);
        } catch (Exception e) {
            // If a major error happens, print it everywhere and display a message box.
            System.err.println(e.toString());
            SPGlobal.logException(e);
            JOptionPane.showMessageDialog(null, "There was an exception thrown during program execution: '" + e + "'  Check the debug logs or contact the author.");
            SPGlobal.closeDebug();
        }
    }

    @Override
    public String getName() {
        return "NPCProcPatch";
    }

    // This function labels any record types that you "multiply".
    // For example, if you took all the armors in a mod list and made 3 copies,
    // you would put ARMO here.
    // This is to help monitor/prevent issues where multiple SkyProc patchers
    // multiply the same record type to yeild a huge number of records.
    @Override
    public GRUP_TYPE[] dangerousRecordReport() {
        // None
        return new GRUP_TYPE[0];
    }

    @Override
    public GRUP_TYPE[] importRequests() {
        return importRequests;
    }

    @Override
    public boolean importAtStart() {
        return false;
    }

    @Override
    public boolean hasStandardMenu() {
        return true;
    }

    // This is where you add panels to the main menu.
    // First create custom panel classes (as shown by YourFirstSettingsPanel),
    // Then add them here.
    @Override
    public SPMainMenuPanel getStandardMenu() {
        SPMainMenuPanel settingsMenu = new SPMainMenuPanel(getHeaderColor());

        settingsMenu.setWelcomePanel(new WelcomePanel(settingsMenu));
        settingsMenu.addMenu(new GeneralSettingsPanel(settingsMenu), false, save, Settings.GENERAL_SETTINGS);
        settingsMenu.addMenu(new VisualSettingsPanel(settingsMenu), false, save, Settings.VISUAL_SETTINGS);
        settingsMenu.addMenu(new CombatSettingsPanel(settingsMenu), false, save, Settings.COMBAT_SETTINGS);

        return settingsMenu;
    }

    // Usually false unless you want to make your own GUI
    @Override
    public boolean hasCustomMenu() {
        return false;
    }

    @Override
    public JFrame openCustomMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasLogo() {
        return false;
    }

    @Override
    public URL getLogo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasSave() {
        return true;
    }

    @Override
    public LSaveFile getSave() {
        return save;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public ModListing getListing() {
        return new ModListing(getName(), false);
    }

    @Override
    public Mod getExportPatch() {
        Mod out = new Mod(getListing());
        out.setAuthor(authorName);
        return out;
    }

    @Override
    public Color getHeaderColor() {
        return headerColor;
    }

    // Add any custom checks to determine if a patch is needed.
    // On Automatic Variants, this function would check if any new packages were
    // added or removed.
    @Override
    public boolean needsPatching() {
        return false;
    }

    // This function runs when the program opens to "set things up"
    // It runs right after the save file is loaded, and before the GUI is displayed
    @Override
    public void onStart() throws Exception {
    }

    // This function runs right as the program is about to close.
    @Override
    public void onExit(boolean patchWasGenerated) throws Exception {
    }

    // Add any mods that you REQUIRE to be present in order to patch.
    @Override
    public ArrayList<ModListing> requiredMods() {
        return new ArrayList<>(0);
    }

    @Override
    public String description() {
        return descriptionToShowInSUM;
    }

    public List<String> loadTextArray(String fileName) {
        try {
            return Files.readAllLines(new File(fileName).toPath(), Charset.defaultCharset()).stream().map(String::trim).distinct().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void copyFaceData(final NPC_ src, NPC_ dest) {
        dest.setRace(src.getRace());
        dest.setSkin(src.getSkin());
        dest.setVoiceType(src.getVoiceType());
        dest.setAttackDataRace(src.getAttackDataRace());
        dest.setEyePreset(src.getEyePreset());
        dest.setFaceTint(RGB.Red, src.getFaceTint(RGB.Red));
        dest.setFaceTint(RGB.Green, src.getFaceTint(RGB.Green));
        dest.setFaceTint(RGB.Blue, src.getFaceTint(RGB.Blue));
        ArrayList<NPC_.TintLayer> tinting = src.getTinting();
        dest.clearTinting();
        for (NPC_.TintLayer tl : tinting) {
            dest.addTinting(tl);
        }
        ArrayList<FormID> headParts = src.getHeadParts();
        dest.clearHeadParts();
        for (FormID hp : headParts) {
            dest.addHeadPart(hp);
        }
        for (NPC_.FacePart fp : NPC_.FacePart.values()) {
            float faceValue = src.getFaceValue(fp);
            dest.setFaceValue(fp, faceValue);
        }
        dest.setHairColor(src.getHairColor());
        dest.setHeight(src.getHeight());
        dest.setMouthPreset(src.getMouthPreset());
        dest.setNosePreset(src.getNosePreset());
        dest.setWeight(src.getWeight());
    }

    // This is where you should write the bulk of your code.
    // Write the changes you would like to make to the patch,
    // but DO NOT export it.  Exporting is handled internally.
    @Override
    public void runChangesToPatch() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        final Map<String, RaceData> raceData = save.getBool(Settings.PROCESS_RACE_HEIGHTS) ?
                mapper.readValue(new File(racesDataPath), new TypeReference<HashMap<String, RaceData>>() {
                }) : null;

        final List<String> faceModList = save.getBool(Settings.PROCESS_FACE_VISUALS) ? loadTextArray("faces.txt") : null;

        final List<String> essentialList = save.getInt(Settings.PRESERVE_PROTECTION_OPTIONS) > 0 ? loadTextArray("essential.txt") : null;

        final List<String> protectedList = save.getInt(Settings.PRESERVE_PROTECTION_OPTIONS) > 0 ? loadTextArray("protected.txt") : null;

        final List<String> noProtectionList = save.getInt(Settings.PRESERVE_PROTECTION_OPTIONS) > 0 ? loadTextArray("noprotection.txt") : null;

        Mod patch = SPGlobal.getGlobalPatch();

        Mod merger = new Mod(getName() + "Merger", false);
        merger.addAsOverrides(SPGlobal.getDB());

        merger.getNPCs().forEach(n -> {

            boolean isNPCRecordChanged = false;

            if (faceModList != null) {
                ArrayList<MajorRecord> hist = n.getRecordHistory();

                if (hist.size() > 1) { // processing face data
                    MajorRecord mrr = null; // the last record in LO having mod match in faces.txt
                    for (MajorRecord mr : hist) {
                        ModListing ml = mr.getModImportedFrom();
                        Mod mod = SPDatabase.getMod(ml);
                        String modName = mod.getName();
                        if (faceModList.indexOf(modName) != -1) {
                            mrr = mr;
                        }
                    }
                    // do not process if the last record in the history anyway
                    if (mrr != null && mrr != hist.get(hist.size() - 1) && mrr instanceof NPC_) {
                        NPC_ from = (NPC_) mrr;
                        copyFaceData(from, n);
                        isNPCRecordChanged = true;
                    }
                }
            }

            if (save.getBool(Settings.REMOVE_DISARM)) {
                ArrayList<FormID> spells = n.getSpells();
                FormID disarm = null;
                for (FormID spell : spells) {
                    if (Objects.equals(spell.getTitle(), "070981Skyrim.esm")) {
                        disarm = spell;
                    }
                }
                if (disarm != null) {
                    n.removeSpell(disarm);
                    isNPCRecordChanged = true;
                }
            }

            if (save.getBool(Settings.REMOVE_DRAGON_DISARM)) {
                ArrayList<FormID> spells = n.getSpells();
                FormID disarm = null;
                for (FormID spell : spells) {
                    if (Objects.equals(spell.getTitle(), "016C40Skyrim.esm")) {
                        disarm = spell;
                    }
                }
                if (disarm != null) {
                    n.removeSpell(disarm);
                    isNPCRecordChanged = true;
                }
            }

            if (save.getInt(Settings.PRESERVE_PROTECTION_OPTIONS) > 0) {
                boolean isProtected = false;
                boolean isEssential = false;
                boolean isProtectionProcessed = false;
                if (essentialList != null && essentialList.contains(n.getName())) {
                    n.set(NPC_.NPCFlag.Essential, true);
                    n.set(NPC_.NPCFlag.Protected, false);
                } else if (protectedList != null && protectedList.contains(n.getName())) {
                    n.set(NPC_.NPCFlag.Essential, false);
                    n.set(NPC_.NPCFlag.Protected, true);
                } else if (noProtectionList != null && noProtectionList.contains(n.getName())) {
                    n.set(NPC_.NPCFlag.Essential, false);
                    n.set(NPC_.NPCFlag.Protected, false);
                } else if (save.getInt(Settings.PRESERVE_PROTECTION_OPTIONS) > 1) { // don't process if set to 'files only'
                    // try checking record's history
                    ArrayList<MajorRecord> hist = n.getRecordHistory();
                    for (MajorRecord mr : hist) {
                        if (mr instanceof NPC_) {
                            NPC_ nh = (NPC_) mr;
                            if (nh.get(NPC_.NPCFlag.Essential)) {
                                isEssential = true;
                                isProtectionProcessed = true;
                            }
                            if (nh.get(NPC_.NPCFlag.Protected)) {
                                isProtected = true;
                                isProtectionProcessed = true;
                            }
                        }
                    }
                    if (isProtectionProcessed) {
                        switch (save.getInt(Settings.PRESERVE_PROTECTION_OPTIONS)) {
                            case 2: // Protected/Essential -> Essential
                                isEssential = isEssential | isProtected;
                                if (isEssential) {
                                    isNPCRecordChanged = true;
                                    n.set(NPC_.NPCFlag.Protected, false);
                                    n.set(NPC_.NPCFlag.Essential, true);
                                }
                                break;
                            case 3: // Protected/Essential -> Protected
                                isProtected = isProtected | isEssential;
                                if (isProtected) {
                                    isNPCRecordChanged = true;
                                    n.set(NPC_.NPCFlag.Protected, true);
                                    n.set(NPC_.NPCFlag.Essential, false);
                                }
                                break;
                            case 4: // Essential -> Protected
                                isProtected = isProtected | isEssential | n.get(NPC_.NPCFlag.Essential) | n.get(NPC_.NPCFlag.Protected);
                                if (isProtected) {
                                    isNPCRecordChanged = true;
                                    n.set(NPC_.NPCFlag.Protected, true);
                                    n.set(NPC_.NPCFlag.Essential, false);
                                }
                                break;
                            case 5: // Protected -> Essential
                                isEssential = isProtected | isEssential | n.get(NPC_.NPCFlag.Essential) | n.get(NPC_.NPCFlag.Protected);
                                if (isEssential) {
                                    isNPCRecordChanged = true;
                                    n.set(NPC_.NPCFlag.Protected, false);
                                    n.set(NPC_.NPCFlag.Essential, true);
                                }
                                break;
                        }
                    }
                }
            }

            if (save.getBool(Settings.PROCESS_OPPOSITE_GENDER_ANIMS)) {
                if (n.get(NPC_.NPCFlag.Female) && n.get(NPC_.NPCFlag.OppositeGenderAnims)) {
                    n.set(NPC_.NPCFlag.OppositeGenderAnims, false);
                    isNPCRecordChanged = true;
                }
            }

            if (isNPCRecordChanged) {
                patch.addRecord(n);
            }
        });

        merger.getRaces().forEach(r -> r.getKeywordSet().getKeywordRefs().forEach(kw -> {
            boolean isRaceRecordChanged = false;
            String title = kw.getTitle();
            if (save.getBool(Settings.COMBAT_IN_WATER) && r.get(RACE.RACEFlags.Swims) && r.get(RACE.RACEFlags.NoCombatInWater)) {
                r.set(RACE.RACEFlags.NoCombatInWater, false);
                isRaceRecordChanged = true;
            }
            if (title != null && title.equals("013794Skyrim.esm")) { // NPC race
                if (save.getBool(Settings.PROCESS_RACE_MODELS)) {
                    try {
                        Model model = r.getPhysicsModel(Gender.FEMALE); // throws NPE sometimes
                        if (model != null && model.getFileName().equals("Actors\\Character\\DefaultMale.hkx")) {
                            model.setFileName("Actors\\Character\\DefaultFemale.hkx");
                            isRaceRecordChanged = true;
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                if (save.getBool(Settings.PROCESS_RACE_HEIGHTS) && !r.get(RACE.RACEFlags.Child)) {
                    String name = r.getName();
                    if (raceData != null && raceData.containsKey(name)) {
                        RaceData rd = raceData.get(name);
                        r.setHeight(Gender.MALE, rd.heightMale);
                        r.setHeight(Gender.FEMALE, rd.heightFemale);
                        isRaceRecordChanged = true;
                    }
                }
            }
            if (isRaceRecordChanged) {
                patch.addRecord(r);
            }
        }));
    }
}
