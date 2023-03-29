package ru.vk.company.pages;

import com.microsoft.playwright.Page;

public class MainPage extends AbstractPage {
    private static final String OK_MAIN_PAGE_URL = "https://ok.ru/";

    public MainPage(Page page) {
        super(page);
    }

    @Override
    public void navigateTo() {
        page.navigate(OK_MAIN_PAGE_URL);
    }

    @Override
    public void navigate(String uri) {
        page.navigate(OK_MAIN_PAGE_URL + uri);
    }

    public void logInAs(String login, String password) {
        navigateTo();
        page.locator("#field_email").first().type(login);
        page.locator("#field_password").first().type(password);
        page.getByText("Войти в Одноклассники").first().click();
    }

    public String getTitle() {
        return page.title();
    }
}
