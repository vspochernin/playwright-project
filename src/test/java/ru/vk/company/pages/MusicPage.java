package ru.vk.company.pages;

import com.microsoft.playwright.Page;

public class MusicPage extends MainPage {

    private static final String OK_MUSIC_URI = "music";

    public MusicPage(Page page) {
        super(page);
    }

    public void navigateToMainPage() {
        super.navigateTo();
    }

    @Override
    public void navigateTo() {
        super.navigate(OK_MUSIC_URI);
    }

    @Override
    public void navigate(String uri) {
        super.navigate(OK_MUSIC_URI + AbstractPage.URI_SEPARATOR + uri);
    }
}
