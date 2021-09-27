package com.opencart.common;

import com.pageobjects.LoginFormPage;
import com.pageobjects.MainPage;
import org.openqa.selenium.WebDriver;

public class AccountCommons {
    private MainPage mainPage;
    private LoginFormPage loginFormPage;
    private WebDriver driver;

    public AccountCommons(WebDriver driver) {
        this.driver = driver;
        mainPage = new MainPage(driver);
        loginFormPage = new LoginFormPage(driver);
    }

    public void login(String username, String password){
        //go to the main page
        driver.get(mainPage.getURL());
        //click on login
        mainPage.clickOnLogin();
        //we fill the user name and pwd
        loginFormPage.loginUser(username, password);
    }
}
