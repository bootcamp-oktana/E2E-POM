package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {
    private final WebDriver driver;

    @FindBy(xpath = "//p[text()='You have not made any previous orders!']")
    private WebElement lblEmptyOrder;

    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement btnContinue;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean lblEmptyOrderIsDisplayed() {
        return lblEmptyOrder.isDisplayed();
    }
}
