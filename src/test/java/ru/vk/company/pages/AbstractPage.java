package ru.vk.company.pages;

import com.microsoft.playwright.Page;

public abstract class AbstractPage {

    public static final String URI_SEPARATOR = "/";
    protected final Page page;

    public AbstractPage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

    public abstract void navigateTo();
    public abstract void navigate(String path);
}
