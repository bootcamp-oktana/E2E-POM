package com.dataprovider;

import com.opencart.utilities.StringFunctions;
import org.testng.annotations.DataProvider;

public class SignUpDataProvider {
    int n = 15;

    @DataProvider(name = "valid data")
    public Object[][] validData() {
        return new Object[][]{
                {"henry", "jimenez", StringFunctions.randomEmail(n), "123456", "password123", "password123"}
        };
    }

    @DataProvider(name = "missing fields")
    public Object[][] missingField() {
        return new Object[][]{
                {null, "jimenez", StringFunctions.randomEmail(n), "123456", "password123", "password123"},
                {"henry", null, StringFunctions.randomEmail(n), "123456", "password123", "password123"},
                {"henry", "jimenez", null, "123456", "password123", "password123"},
                {"henry", "jimenez", StringFunctions.randomEmail(n), null, "password123", "password123"},
                {"henry", "jimenez", StringFunctions.randomEmail(n), "123456", null, "password123"},
                {"henry", "jimenez", StringFunctions.randomEmail(n), "123456", "password123", null}
        };
    }

    @DataProvider(name = "email missing at")
    public Object[][] emailMissingAt() {
        return new Object[][]{
                {"henry", "jimenez", "test_123.com", "123456", "password123", "password123"},
                {"jorge", "cueva", "test_456.com", "654321", "password123", "password123"},
                {"alberto", "caceres", "test_678.com", "111111", "password123", "password123"}
        };
    }

    @DataProvider(name = "email missing dot com")
    public Object[][] emailMissingDotCom() {
        return new Object[][]{
                {"henry", "jimenez", "test_123@test", "123456", "password123", "password123"},
                {"jorge", "cueva", "test_456@test", "654321", "password123", "password123"},
                {"alberto", "caceres", "test_678@test", "111111", "password123", "password123"}
        };
    }

    @DataProvider(name = "different passwords")
    public Object[][] differentPasswords() {
        return new Object[][]{
                {"henry", "jimenez", StringFunctions.randomEmail(n), "123456", "password123", "password"},
                {"jorge", "cueva", StringFunctions.randomEmail(n), "654321", "password666", "password"},
                {"alberto", "caceres", StringFunctions.randomEmail(n), "111111", "password444", "password"}
        };
    }

    @DataProvider(name = "edit valid info")
    public Object[][] editValidInfo() {
        return new Object[][]{
                {"henry", "jimenez", "123456"},
                {"jorge", "cueva", "654321"},
                {"alberto", "caceres", "111111"}
        };
    }

    @DataProvider(name = "missing at email info edit")
    public Object[][] editEmailMissingAt() {
        return new Object[][]{
                {"test.com"}
        };
    }

    @DataProvider(name = "missing dot com info edit")
    public Object[][] editEmailMissingDotCom() {
        return new Object[][]{
                {"test@gmailcom"}
        };
    }
}
