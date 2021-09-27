package com.opencart.accounttests;

import com.dataprovider.LoginDataProvider;
import com.dataprovider.SignUpDataProvider;
import com.opencart.Base;
import com.opencart.common.AccountCommons;
import com.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class MyAccount extends Base {
    private AccountCommons accountCommons;
    private MyAccountPage myAccountPage;
    private WishlistPage wishlistPage;
    private EditAccountInfoFormPage editAccountInfoFormPage;
    private OrderHistoryPage orderHistoryPage;
    private NewsletterPage newsletterPage;
    private MainPage mainPage;

    @BeforeTest(alwaysRun = true)
    public void initialize() {
        driver = initializeDriver();

        accountCommons = new AccountCommons(driver);
        mainPage = new MainPage(driver);
        myAccountPage = new MyAccountPage(driver);
    }

    @BeforeMethod()
    public void beforeMethod(){
        //do a login with account commons class
        accountCommons.login(LoginDataProvider.USER_NAME, LoginDataProvider.USER_PWD);
        //go to my account page
        mainPage.goToMyAccount();
    }

    @Test
    public void testAccountElements() {
        //test elements are present
        Assert.assertTrue(myAccountPage.lblYourStoreIsDisplayed());
        Assert.assertTrue(myAccountPage.linkEditAccountIsDisplayed());
        Assert.assertTrue(myAccountPage.linkChangePasswordIsDisplayed());
        Assert.assertTrue(myAccountPage.linkModifyAddressBookIsDisplayed());
        Assert.assertTrue(myAccountPage.linkModifyWishlistIsDisplayed());
    }

    @Test
    public void testEmptyWishList() {
        wishlistPage = new WishlistPage(driver);

        //1. Click on Wishlist
        myAccountPage.clickOnWishlist();
        //1. A message:  "Your wish list is empty." is displayed
        Assert.assertTrue(wishlistPage.lblWishListEmptyIsDisplayed());
    }

    @Test(dataProvider = "edit valid info", dataProviderClass = SignUpDataProvider.class)
    public void testEditSuccessMessage(String firstname, String lastName, String phone) {
        editAccountInfoFormPage = new EditAccountInfoFormPage(driver);

        //1. Click on "Edit your account information"
        myAccountPage.clickOnEditInformation();
        //2. Fill the form with valid data
        editAccountInfoFormPage.fillForm(firstname, lastName, phone);
        //3. Redirects to "My Account"
        Assert.assertTrue(myAccountPage.lblYourStoreIsDisplayed());
        Assert.assertTrue(myAccountPage.linkEditAccountIsDisplayed());
        Assert.assertTrue(myAccountPage.linkChangePasswordIsDisplayed());
        Assert.assertTrue(myAccountPage.linkModifyAddressBookIsDisplayed());
        Assert.assertTrue(myAccountPage.linkModifyWishlistIsDisplayed());
        Assert.assertTrue(myAccountPage.lblSuccessEditIsDisplayed());
    }

    @Test(dataProvider = "missing at email info edit", dataProviderClass = SignUpDataProvider.class)
    public void testEditMissingAtEmailMessage(String email) {
        editAccountInfoFormPage = new EditAccountInfoFormPage(driver);

        //1. Click on Edit
        myAccountPage.clickOnEditInformation();
        //2. Fill the form with invalid email (missing @)
        editAccountInfoFormPage.changeEmail(email);
        //2. Message displayed: Please include @ in the email address.
    }

    @Test(dataProvider = "missing dot com info edit", dataProviderClass = SignUpDataProvider.class)
    public void testEditMissingDotComEmailMessage(String email) {
        editAccountInfoFormPage = new EditAccountInfoFormPage(driver);

        //1. Click on Edit
        myAccountPage.clickOnEditInformation();
        //2. Fill the form with invalid email (missing .com)
        editAccountInfoFormPage.changeEmail(email);
        //2. Message displayed: Please include @ in the email address.
        Assert.assertTrue(editAccountInfoFormPage.lblErrorWrongEmailIsDisplayed());
    }

    @Test
    public void testEmptyOrderHistory() {
        orderHistoryPage = new OrderHistoryPage(driver);

        //1. Click on "Order History"
        myAccountPage.clickOnOrderHistory();
        //2. A message: "You have not made any previous orders!" is displayed
        Assert.assertTrue(orderHistoryPage.lblEmptyOrderIsDisplayed());
    }

    @Test
    public void testNewsletterSubscription() {
        newsletterPage = new NewsletterPage(driver);

        //1. Click on "Newsletter"
        myAccountPage.clickOnNewsletter();
        //2. Toggle newsletter subscription
        newsletterPage.changeNewsletter();
        //2. Redirects to "My Account"
        Assert.assertTrue(myAccountPage.lblYourStoreIsDisplayed());
        Assert.assertTrue(myAccountPage.linkEditAccountIsDisplayed());
        Assert.assertTrue(myAccountPage.linkChangePasswordIsDisplayed());
        Assert.assertTrue(myAccountPage.linkModifyAddressBookIsDisplayed());
        Assert.assertTrue(myAccountPage.linkModifyWishlistIsDisplayed());
        //2. message" Success: Your newsletter subscription has been successfully updated!" is displayed
        Assert.assertTrue(myAccountPage.lblSuccessNewsLetterIsDisplayed());
    }

    @AfterMethod
    public void afterMethod(){
        mainPage.logout();
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
