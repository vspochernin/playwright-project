package ru.vk.company.pages;

import com.microsoft.playwright.Page;

public class MainPage {

    private final Page page;

    public MainPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://ok.ru/");
    }

    public String getTitle() {
        return page.title();
    }
}
