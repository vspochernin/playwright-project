package ru.vk.company;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ru.vk.company.pages.MainPage;
import ru.vk.company.utils.ScreenShot;

import static org.junit.jupiter.api.Assertions.*;

// Демонстрируется самый элементарный вариант запуска тестов.
class Example1StartTest {

    private static Playwright playwright;
    private static Browser browser;

    private static MainPage mainPage;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();

        //browser = playwright.chromium().launch();
        // Можно запустить так, чтобы смотреть действия.
        // Однако, без запуска тесты проходят быстрее.
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));

        Page page = browser.newPage();

        mainPage = new MainPage(page);
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    @Test
    void checkTitleOfOKDotRU() {
        mainPage.navigateTo();

        String title = mainPage.getTitle();
        System.out.println(title);

        assertTrue(title.length() > 0);

        mainPage.getPage().screenshot(new Page.ScreenshotOptions().setPath(ScreenShot.EXAMPLE.getPath()));
    }
}
