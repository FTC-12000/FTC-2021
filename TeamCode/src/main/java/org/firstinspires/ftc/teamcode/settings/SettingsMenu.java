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
    private IndexedHashMap<String, Integer> settings = new IndexedHashMap<>();
    private HashMap<String, Integer> maxValues = new HashMap<>();
    private HashMap<String, HashMap<Integer, String>> translation = new HashMap<>();

    private Telemetry telemetry;
    private Gamepad gamepad;
    private String selected;

    private int inputDelayLoop;

    public int getSetting(String key) {
        return settings.get(key);
    }
    
    public SettingsMenu(Telemetry telemetry, Gamepad gamepad) {
        this.telemetry = telemetry;
        this.gamepad = gamepad;
        this.selected = settings.keys.get(0);
        this.inputDelayLoop = 0;

        HashMap<Integer, String> map;

        map = new HashMap<>();
        map.put(-1, "Drive Mode");
        map.put(1, "Dual Stick");
        map.put(2, "Single Stick");
        settings.put("drive_mode", 1);
        maxValues.put("drive_mode", 2);
        translation.put("drive_mode", map);

        map = new HashMap<>();
        map.put(-1, "Test Setting");
        map.put(1, "Test Option 1");
        map.put(2, "Test Option 2");
        map.put(3, "Test Option 3");
        settings.put("test_setting", 1);
        maxValues.put("test_setting", 3);
        translation.put("test_setting", map);
    }

    public void loop() {
        handleInput();
        display();
    }

    private void handleInput() {
        if (inputDelayLoop > 150) {
            if (gamepad.dpad_right) {
                int setting = settings.get(selected) + 1;
                if (setting <= maxValues.get(selected)) {
                    settings.put(selected, setting);
                } else {
                    settings.put(selected, 1);
                }
                inputDelayLoop = 0;
            } else if (gamepad.dpad_left) {
                int setting = settings.get(selected) - 1;
                if (setting >= 1) {
                    settings.put(selected, setting);
                } else {
                    settings.put(selected, maxValues.get(selected));
                }
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
        System.out.println("newline");
        telemetry.addData("Settings", "");
        for (String key : settings.keys) {
            System.out.println(key);
            String line = translation.get(key).get(settings.get(key));

            if (key.equals(selected)) {
                line += " <-";
            }
            telemetry.addData(translation.get(key).get(-1), line);
        }
    }
}
