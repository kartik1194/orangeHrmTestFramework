package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseClass;
import pageClasses.LoginPage;
import pageClasses.AddEmployeePage;

public class AddEmployeeTest extends BaseClass {

    @Test
    public void verifyAddEmployeeFullFlow() {

        // Login
        LoginPage lp = new LoginPage(driver);
        lp.setUsername(prop.getProperty("username"));
        lp.setPassword(prop.getProperty("password"));
        lp.clickLogin();

        // Add Employee
        AddEmployeePage ap = new AddEmployeePage(driver);

        ap.clickPIM();
        ap.clickAddEmployee();

        ap.addBasicDetails("Abhishek", "Kumar", "Sharma");

        // Validation
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("viewPersonalDetails"));

        System.out.println("Add Employee Test Passed ✅");
    }
}