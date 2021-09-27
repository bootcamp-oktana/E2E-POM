package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditAccountInfoFormPage {
    private final WebDriver driver;

    @FindBy(id = "input-firstname")
    private WebElement inputFirstName;

    @FindBy(id = "input-lastname")
    private WebElement inputLastName;

    @FindBy(id = "input-email")
    private WebElement inputEmail;

    @FindBy(id = "input-telephone")
    private WebElement inputPhone;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement btnContinue;

    @FindBy(xpath = "//a[text()='Back']")
    private WebElement btnBack;

    @FindBy(xpath = "//div[@class='text-danger' and contains(. ,'E-Mail Address does not appear to be valid')]")
    private WebElement lblErrorWrongEmail;

    public EditAccountInfoFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillForm(String firstName, String lastName, String Phone) {
        inputFirstName.clear();
        inputFirstName.sendKeys(firstName);
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
        inputPhone.clear();
        inputPhone.sendKeys(Phone);
        clickOnContinue();
    }

    public void changeEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
        clickOnContinue();
    }

    public boolean lblErrorWrongEmailIsDisplayed() {
        return lblErrorWrongEmail.isDisplayed();
    }

    public void clickOnContinue() {
        btnContinue.click();
    }
}
