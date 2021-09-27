package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpSuccessPage {
    private final WebDriver driver;

    @FindBy(xpath = "//h1[contains(., 'Your Account Has Been Created')]")
    private WebElement lblSuccess;

    public SignUpSuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean lblSuccessIsDisplayed() {
        return lblSuccess.isDisplayed();
    }
}
