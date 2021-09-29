package org.firstinspires.ftc.teamcode.settings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class SettingUtil {
    public static SettingsMenu createTeleOpSettings(Telemetry telemetry, Gamepad gamepad) {
        ArrayList<Setting> settings = new ArrayList<>();
        ArrayList<String> translation;
        Setting setting;

        translation = new ArrayList<>();
        translation.add("Dual Stick");
        translation.add("Single Stick");
        setting = new Setting("drive_mode", "Drive Mode", 2, translation);
        settings.add(setting);

        translation = new ArrayList<>();
        translation.add("Test 1");
        translation.add("Test 2");
        translation.add("Test 3");
        setting = new Setting("test", "Test", 3, translation);
        settings.add(setting);

        return new SettingsMenu(settings, telemetry, gamepad);
    }
}
