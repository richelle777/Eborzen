package org.isj.ing4.isi.music.utils;

import org.springframework.ui.Model;

public class Utils {

    public static void updateModel(Model model) {
        model.addAttribute("key", "");
    }

    public static void updateModelWithValue(Model model, String val) {
        model.addAttribute("key", val);
    }

}
