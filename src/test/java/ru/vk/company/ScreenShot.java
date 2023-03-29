package ru.vk.company;

import java.nio.file.Paths;
import java.nio.file.Path;

public enum ScreenShot {
    TRACK_ACTIONS_MENU("track_actions_menu.png"),
    LYRICS("lyrics.png");

    private Path path;

    ScreenShot(String filename) {
        path = Paths.get("src", "test", "resources", filename);
    }

    public Path getPath() {
        return path;
    }
}
