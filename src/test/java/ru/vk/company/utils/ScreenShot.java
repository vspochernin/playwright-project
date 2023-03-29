package ru.vk.company.utils;

import java.nio.file.Paths;
import java.nio.file.Path;

public enum ScreenShot {

    EXAMPLE("example.png"),
    TRACK_ACTIONS_MENU("track_actions_menu.png"),
    LYRICS("lyrics.png");

    private final Path path;

    ScreenShot(String filename) {
        path = Paths.get("src", "test", "resources", filename);
    }

    public Path getPath() {
        return path;
    }
}
