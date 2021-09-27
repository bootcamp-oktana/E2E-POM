package com.opencart.accounttests;

import com.dataprovider.SignUpDataProvider;
import com.opencart.Base;
import com.pageobjects.MainPage;
import com.pageobjects.SignUpFormPage;
import com.pageobjects.SignUpSuccessPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignUp extends Base {
    private MainPage mainPage;
    private SignUpFormPage signUpPageFormPage;
    private SignUpSuccessPage signUpSuccessPage;

    @BeforeTest
    public void initialize() {
        driver = initializeDriver();

        mainPage = new MainPage(driver);
        signUpPageFormPage = new SignUpFormPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(){
        //we go to the URL
        driver.get(mainPage.getURL());
        //1. Click on "MY ACCOUNT"
        //2. Select Register
        mainPage.clickOnRegister();
    }

    @Test(dataProvider = "valid data", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationValidData(String firstName, String lastName, String email, String phone,
                                              String password, String passwordConfirm) {
        signUpSuccessPage = new SignUpSuccessPage(driver);

        //Fill the form with valid data
        signUpPageFormPage.fillForm(firstName, lastName, email, phone, password, passwordConfirm);
        // Message displayed: Your Account Has Been Created!
        Assert.assertTrue(signUpSuccessPage.lblSuccessIsDisplayed());
    }

    @Test(dataProvider = "missing fields", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationMissingField(String firstName, String lastName, String email, String phone,
                                                 String password, String passwordConfirm) {
        //Fill the form with leaving 1 field on blank
        signUpPageFormPage.fillForm(firstName, lastName, email, phone, password, passwordConfirm);
        // Error message have to appear
        Assert.assertTrue(signUpPageFormPage.lblErrorGeneralIsDisplayed());
     }

    @Test(dataProvider = "email missing at", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationInvalidEmailMissingAt(String firstName, String lastName, String email, String phone,
                                                          String password, String passwordConfirm) {
        //Fill the form with an email with missing @
        signUpPageFormPage.fillForm(firstName, lastName, email, phone, password, passwordConfirm);
        // Message displayed: Missing @
    }

    @Test(dataProvider = "email missing dot com", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationInvalidEmailMissingDotCom(String firstName, String lastName, String email, String phone,
                                                              String password, String passwordConfirm) {
        //Fill the form with an email with missing .com
        signUpPageFormPage.fillForm(firstName, lastName, email, phone, password, passwordConfirm);
        // Message displayed: Missing .com
        Assert.assertTrue(signUpPageFormPage.lblErrorWrongEmailIsDisplayed());
    }

    @Test(dataProvider = "different passwords", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationDifferentPasswords(String firstName, String lastName, String email, String phone,
                                                       String password, String passwordConfirm) {
        //Fill the form with different passwords
        signUpPageFormPage.fillForm(firstName, lastName, email, phone, password, passwordConfirm);
        // Message displayed: Password does not match
        Assert.assertTrue(signUpPageFormPage.lblErrorMismatchPasswordIsDisplayed());
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
