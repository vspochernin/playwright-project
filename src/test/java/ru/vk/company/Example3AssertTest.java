package ru.vk.company;

import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.vk.company.pages.MusicPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


// Демонстрируется возможность различных ассертов.
public class Example3AssertTest {

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
    public void checkAsserts() {
        // Будем ожидать соответствующего заголовка.
        assertThat(musicPage.getPage()).hasTitle("Одноклассники");

        // Попробуем по локатору что-то написать в строку поиска и проверить это.
        Locator locator = musicPage.getPage().getByPlaceholder("Искать на сайте");
        locator.first().type("тестовое сообщение");
        assertThat(locator).hasValue("тестовое сообщение");

        // Проверим наличие у элемента сложного аттрибута.
        assertThat(locator).hasAttribute("name", "st.query");

        // Проверим наличие в URL regex-паттерна с music.
        assertThat(musicPage.getPage()).hasURL(Pattern.compile(".*music"));
    }
}
