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
        translation.add("Compact");
        translation.add("Expanded");
        setting = new Setting("arm_mode", "Arm Mode", 2, translation);
        settings.add(setting);

        return new SettingsMenu(settings, telemetry, gamepad);
    }

    public static SettingsMenu createBasicAutoOpSettings(Telemetry telemetry, Gamepad gamepad) {
        ArrayList<Setting> settings = new ArrayList<>();
        Setting setting;

        setting = new Setting("drive_time", "Drive Time", 17, null, 8);
        settings.add(setting);
        setting = new Setting("wait_time", "Wait Time", 17, null);
        settings.add(setting);

        return new SettingsMenu(settings, telemetry, gamepad);
    }
}
