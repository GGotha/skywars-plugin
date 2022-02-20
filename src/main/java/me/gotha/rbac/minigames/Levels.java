package me.gotha.rbac.minigames;

import java.util.Random;

public class Levels {

    public static String[] levels = {"catacombs", "hexagonal", "sky"};

    public static String selectRandomlyALevel() {
        int index = new Random().nextInt(levels.length);
        return levels[index];
    };

}
