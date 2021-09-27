package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginFormPage {
    private final WebDriver driver;

    @FindBy(id = "input-email")
    private WebElement inputUsername;

    @FindBy(id = "input-password")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement btnLogin;

    public LoginFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginUser(String username, String password) {
        //we fill the user name and pwd
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        //click on login button
        btnLogin.click();
    }
}
