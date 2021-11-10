package org.firstinspires.ftc.teamcode.settings;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/*
* This is the actual settings object, which stores info about a setting (specifically so i can put it in a Map)
*/
public class Setting {
    public String id;
    public String name;
    public int value;
    public int max;
    public List<String> translation;

    public Setting(String id, String name, int max) {
        this(id, name, max, null, 0);
    }

    public Setting(String id, String name, int max, List<String> translation) {
        this(id, name, max, translation, 0);
    }

    public Setting(String id, String name, int max, int value) {
        this(id, name, max, null, value);
    }

    public Setting(String id, String name, int max, @Nullable List<String> translation, int value) {
        if (translation == null) {
            translation = new ArrayList<String>();
            for (int i = 0; i < max; i++) {
                translation.add(Integer.toString(i));
                System.out.println(Integer.toString(i));
            }
        }

        this.id = id;
        this.name = name;
        this.value = value;
        this.max = max;
        this.translation = translation;
    }
}
