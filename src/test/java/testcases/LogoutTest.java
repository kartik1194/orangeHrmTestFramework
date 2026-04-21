package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseClass;
import pageClasses.LoginPage;
import pageClasses.LogoutPage;

public class LogoutTest extends BaseClass {

    @Test
    public void verifyLogout() {

        // Login
        LoginPage lp = new LoginPage(driver);
        lp.login(prop.getProperty("username"), prop.getProperty("password"));

        // Logout
        LogoutPage lo = new LogoutPage(driver);
        lo.clickProfile();
        lo.clickLogout();

        // Validation
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("login"));

        System.out.println("Logout Test Passed");
    }
}