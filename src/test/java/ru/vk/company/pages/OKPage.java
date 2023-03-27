package ru.vk.company.pages;

import com.microsoft.playwright.Page;

public class OKPage {

    private final Page page;

    public OKPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://ok.ru/");
    }

    public String getTitle() {
        return page.title();
    }
}
