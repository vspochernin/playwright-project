package ru.vk.company;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.vk.company.pages.MusicPage;
import ru.vk.company.utils.ScreenShot;

// Демонстрируется возможность наведения на элементы, ожидания элемента,
// скриншота элемента.
public class Example2MusicTest {

    private static final String INSTASAMKA_TRACK_URI = "track/123251851019460?i=3";
    private static final int LYRICS_TIMEOUT = 10_000;
    private static final int COMMON_DELAY = 160;

    private static Playwright playwright;
    private static Browser browser;
    private static MusicPage musicPage;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();
        browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        musicPage = new MusicPage(browser.newPage());
        musicPage.login();
        musicPage.navigateTo();
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    public void openApplication() {
        System.out.println(musicPage.getPage().title());
    }

    @Test
    public void checkTrackActionsMenuForVisibility() {
        Locator track = musicPage.getPage().locator("wm-track").first();
        track.hover();
        track.locator("wm-track-actions").hover(); // Будет ждать "wm-track-actions".
        musicPage.getPage().waitForTimeout(COMMON_DELAY);
        track.locator("wm-track-actions-menu") // Делаем скриншот отдельного элемента.
                .screenshot(new Locator.ScreenshotOptions()
                .setPath(ScreenShot.TRACK_ACTIONS_MENU.getPath()));
    }

    @Test
    public void checkLyricsForScrolling() {
        musicPage.navigate(INSTASAMKA_TRACK_URI);
        musicPage.getPage()
                .locator("wm-play-button")
                .click();
        musicPage.getPage().waitForTimeout(LYRICS_TIMEOUT);
        musicPage.getPage().getByTitle("Слова песни").first().click();
        musicPage.getPage().waitForTimeout(COMMON_DELAY);
        musicPage.getPage().locator("wm-lyrics")
                .first()
                .screenshot(new Locator.ScreenshotOptions()
                .setPath(ScreenShot.LYRICS.getPath()));
    }
}
