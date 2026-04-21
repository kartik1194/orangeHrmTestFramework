package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseClass;
import pageClasses.LoginPage;
import pageClasses.SearchEmployeePage;

public class SearchEmployeeTest extends BaseClass {

    @Test
    public void verifySearchEmployee() {

        // Login
        LoginPage lp = new LoginPage(driver);
        lp.setUsername(prop.getProperty("username"));
        lp.setPassword(prop.getProperty("password"));
        lp.clickLogin();

        // Search Employee
        SearchEmployeePage sp = new SearchEmployeePage(driver);

        sp.clickPIM();
        sp.searchEmployee("Abhishek");

        // Validation
        Assert.assertTrue(sp.isEmployeeDisplayed());

        System.out.println("Employee Found Successfully ✅");
    }
}