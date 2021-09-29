package org.firstinspires.ftc.teamcode.settings;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Setting {
    public String id;
    public String name;
    public int value;
    public int max;
    public List<String> translation;


    public Setting(String id, String name, int max, @Nullable List<String> translation) {
        if (translation == null) {
            translation = new ArrayList<String>();
            for (int i = 0; i < max; i++) {
                translation.add(Integer.toString(i));
                System.out.println(Integer.toString(i));
            }
        }

        this.id = id;
        this.name = name;
        this.value = 0;
        this.max = max;
        this.translation = translation;
    }
}
