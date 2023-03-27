package ru.vk.company;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ru.vk.company.pages.OKPage;

import static org.junit.jupiter.api.Assertions.*;

class OKTest {

    private static final String TITLE = "Социальная сеть Одноклассники. Общение с друзьями в ОК. Ваше место встречи с одноклассниками";

    private static Playwright playwright;
    private static Browser browser;
    private static OKPage okPage;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        Page page = browser.newPage();
        okPage = new OKPage(page);
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    void checkTitleOfOKDotRU() {
        okPage.navigate();
        String title = okPage.getTitle();
        assertEquals(TITLE, title);
    }
}
