package org.firstinspires.ftc.teamcode.settings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.IndexedHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SettingsMenu {
    private IndexedHashMap<String, Setting> settings = new IndexedHashMap<>();

    private Telemetry telemetry;
    private Gamepad gamepad;
    private String selected;

    private int inputDelayLoop;

    public int getSetting(String key) {
        return settings.get(key).value;
    }
    
    public SettingsMenu(ArrayList<Setting> settings, Telemetry telemetry, Gamepad gamepad) {
        this.telemetry = telemetry;
        this.gamepad = gamepad;
        this.selected = settings.get(0).id;
        this.inputDelayLoop = 0;

        for (Setting setting : settings) {
            this.settings.put(setting.id, setting);
        }

        System.out.println(this.settings.getByIndex(0).translation);
        System.out.println(this.settings.getByIndex(1).translation);
    }

    public void loop() {
        handleInput();
        display();
    }

    private void handleInput() {
        if (inputDelayLoop > 150) {
            if (gamepad.dpad_right) {
                Setting setting = settings.get(selected);
                int value = setting.value + 1;
                if (value < setting.max) { //i feel like this is wrong, cant debug rn
                    setting.value = value;
                } else {
                    setting.value = 0;
                }
                settings.put(selected, setting);
                inputDelayLoop = 0;
            } else if (gamepad.dpad_left) {
                Setting setting = settings.get(selected);
                int value = setting.value - 1;
                if (value >= 0) {
                    setting.value = value;
                } else {
                    setting.value = setting.max - 1;
                }
                settings.put(selected, setting);
                inputDelayLoop = 0;
            } else if (gamepad.dpad_up) {
                int index = settings.keys.indexOf(selected) + 1;
                if (index >= settings.size()) {
                    index = 0;
                }
                selected = settings.keys.get(index);
                inputDelayLoop = 0;
            } else if (gamepad.dpad_down) {
                int index = settings.keys.indexOf(selected) - 1;
                if (index < 0) {
                    index = settings.keys.size() - 1;
                }
                selected = settings.keys.get(index);
                inputDelayLoop = 0;
            }
        } else {
            inputDelayLoop++;
        }
    }

    private void display() {
        telemetry.clear();
        telemetry.addData("Settings", "");
        for (String key : settings.keys) {
            String line = settings.get(key).translation.get(settings.get(key).value);

            if (key.equals(selected)) {
                line += " <-";
            }
            telemetry.addData(settings.get(key).name, line);
        }
    }
}
