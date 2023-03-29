package ru.vk.company;

import java.util.Arrays;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


// Демонстрируется возможность эмуляции устройств.
public class Example4EmulationTest {

    private static Playwright playwright;
    private static Browser browser;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();
        browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    // Пробуем сменить юзер агент.
    @Test
    public void checkAgent() {
        BrowserContext userAgentContext = browser.newContext(new Browser.NewContextOptions()
                .setUserAgent("NEW BROWSER CONTEXT"));
        Page page = userAgentContext.newPage();
        page.navigate("https://www.whatsmyua.info/");
        Locator locator = page.locator("#custom-ua-string").first();
        System.out.println(locator.textContent());
    }

    // Пробуем пробраться в колизей.
    @Test
    public void checkLocation() {
        BrowserContext locationContext = browser.newContext(new Browser.NewContextOptions()
                .setGeolocation(41.890221, 12.492348)
                .setPermissions(Arrays.asList("geolocation")));
        Page page = locationContext.newPage();
        page.navigate("https://www.google.ru/maps/");
        page.locator("#mylocation").first().click();
        page.waitForTimeout(5000);
    }
}
