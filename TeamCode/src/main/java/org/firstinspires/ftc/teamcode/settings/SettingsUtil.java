package org.firstinspires.ftc.teamcode.settings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

/*
* This class just holds static bulky methods for creating settings objects for the OpModes.
* The actual settings menu code is in SettingsMenu.java
*/
public class SettingsUtil {
    public static SettingsMenu createTeleOpSettings(Telemetry telemetry, Gamepad gamepad) {
        ArrayList<Setting> settings = new ArrayList<>();
        ArrayList<String> translation;
        Setting setting;

        // Drive Mode
        translation = new ArrayList<>();
        translation.add("Dual Stick (Recommended)");
        translation.add("Single Stick");
        translation.add("DPad");
        setting = new Setting("drive_mode", "Drive Mode", 3, translation);
        settings.add(setting);

        /* // Arm Speed
        translation = new ArrayList<>();
        translation.add("1:1 (Warning: Very fast!)");
        translation.add("1:2");
        translation.add("1:3");
        translation.add("1:4");
        translation.add("1:5");
        translation.add("1:6");
        setting = new Setting("arm_speed", "Arm Speed", 6, translation, 3);
        settings.add(setting);

        // Arm Mode
        translation = new ArrayList<>();
        translation.add("Compact (Recommended)");
        translation.add("Expanded");
        setting = new Setting("arm_mode", "Arm Mode", 2, translation);
        settings.add(setting);
        */

        return new SettingsMenu(settings, telemetry, gamepad);
    }

    public static SettingsMenu createBasicAutoOpSettings(Telemetry telemetry, Gamepad gamepad) {
        ArrayList<Setting> settings = new ArrayList<>();
        ArrayList<String> translation;
        Setting setting;

        // Drive Time and Wait Time
        setting = new Setting("drive_time", "Drive Time", 17, 8);
        settings.add(setting);
        setting = new Setting("wait_time", "Wait Time", 17);
        settings.add(setting);

        // Carousel
        translation = new ArrayList<>();
        translation.add("Off");
        translation.add("On");
        setting = new Setting("carousel","Carousel",2, translation);
        settings.add(setting);

        // Turn Direction
        translation = new ArrayList<>();
        translation.add("None");
        translation.add("Left");
        translation.add("Right");
        setting = new Setting("turn_direction","Turn Direction",3, translation);
        settings.add(setting);

        return new SettingsMenu(settings, telemetry, gamepad);
    }

    public static SettingsMenu createMainAutoOpSettings(Telemetry telemetry, Gamepad gamepad) {
        ArrayList<Setting> settings = new ArrayList<>();
        ArrayList<String> translation;
        Setting setting;

        // Team
        translation = new ArrayList<>();
        translation.add("Red");
        translation.add("Blue");
        setting = new Setting("team", "Team", 2, translation);
        settings.add(setting);

        // Position
        translation = new ArrayList<>();
        translation.add("Front");
        translation.add("Back");
        setting = new Setting("position", "Position", 2, translation);
        settings.add(setting);

        // Carousel
        translation = new ArrayList<>();
        translation.add("Off");
        translation.add("On");
        setting = new Setting("carousel","Carousel",2, translation);
        settings.add(setting);

        return new SettingsMenu(settings, telemetry, gamepad);
    }
}
