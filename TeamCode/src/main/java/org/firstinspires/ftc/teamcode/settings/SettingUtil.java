package org.firstinspires.ftc.teamcode.settings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

/*
* This class just holds static bulky methods for creating settings objects for the OpModes.
* The actual settings menu code is in SettingsMenu.java
*/
public class SettingUtil {
    public static SettingsMenu createTeleOpSettings(Telemetry telemetry, Gamepad gamepad) {
        ArrayList<Setting> settings = new ArrayList<>();
        ArrayList<String> translation;
        Setting setting;

        translation = new ArrayList<>();
        translation.add("Dual Stick (Recommended)");
        translation.add("Single Stick");
        translation.add("DPad");
        setting = new Setting("drive_mode", "Drive Mode", 3, translation);
        settings.add(setting);

        translation = new ArrayList<>();
        translation.add("Compact (Recommended)");
        translation.add("Expanded");
        setting = new Setting("arm_mode", "Arm Mode", 2, translation);
        settings.add(setting);

        return new SettingsMenu(settings, telemetry, gamepad);
    }

    public static SettingsMenu createBasicAutoOpSettings(Telemetry telemetry, Gamepad gamepad) {
        ArrayList<Setting> settings = new ArrayList<>();
        ArrayList<String> translation;
        Setting setting;

        setting = new Setting("drive_time", "Drive Time", 17, null, 8);
        settings.add(setting);
        setting = new Setting("wait_time", "Wait Time", 17, null);
        settings.add(setting);

        translation = new ArrayList<>();
        translation.add("Off");
        translation.add("On");
        setting = new Setting("spinner","Spinner",2, translation);
        settings.add(setting);

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

        return new SettingsMenu(settings, telemetry, gamepad);
    }
}
