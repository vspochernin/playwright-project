package ru.vk.company;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ru.vk.company.pages.MainPage;

import static org.junit.jupiter.api.Assertions.*;

class Example1Test {

    private static Playwright playwright;
    private static Browser browser;
    private static MainPage mainPage;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
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
    }
}
