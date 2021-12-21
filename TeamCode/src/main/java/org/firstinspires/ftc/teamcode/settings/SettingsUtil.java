package org.firstinspires.ftc.teamcode.settings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

/*
* This class just holds bulky static methods for creating settings objects for the OpModes.
* The actual settings menu code is in SettingsMenu
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

        // Spinner Direction
        translation = new ArrayList<>();
        translation.add("Clockwise");
        translation.add("Counterclockwise");
        setting = new Setting("spinner_direction", "Spinner Direction", 2, translation);
        settings.add(setting);

        // Drive Speed
        translation = new ArrayList<>();
        translation.add("25%");
        translation.add("50%");
        translation.add("75%");
        translation.add("100%");
        setting = new Setting("drive_speed", "Drive Speed", 4, translation, 2);
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
        translation.add("Left");
        translation.add("Right");
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
