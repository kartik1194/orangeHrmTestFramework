package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basetest.BaseClass;
import pageClasses.LoginPage;
import pageClasses.LogoutPage;
import utilities.ExcelUtils;

public class LoginTest extends BaseClass {

    @Test(dataProvider = "loginData")
    public void verifyLogin(int rowNum, String username, String password, String expected) throws Exception {

        String path = System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx";

        System.out.println("Running for: " + username);

        // Login
        LoginPage lp = new LoginPage(driver);
        lp.setUsername(username);
        lp.setPassword(password);
        lp.clickLogin();

        String currentURL = driver.getCurrentUrl();

        // VALID LOGIN CASE
        if (expected.equalsIgnoreCase("valid")) {

            if (currentURL.contains("dashboard")) {
                System.out.println("Valid Login Passed ✅");

                ExcelUtils.setCellData(path, "Sheet1", rowNum, 3, "PASS");
                ExcelUtils.fillGreenColor(path, "Sheet1", rowNum, 3);

            } else {
                System.out.println("Valid Login Failed ❌");

                ExcelUtils.setCellData(path, "Sheet1", rowNum, 3, "FAIL");
                ExcelUtils.fillRedColor(path, "Sheet1", rowNum, 3);
            }

            // Logout after valid login
            LogoutPage logout = new LogoutPage(driver);
            logout.clickProfile();
            logout.clickLogout();
        }

        // INVALID LOGIN CASE
        else {

            if (currentURL.contains("login")) {
                System.out.println("Invalid Login Passed ❌");

                ExcelUtils.setCellData(path, "Sheet1", rowNum, 3, "PASS");
                ExcelUtils.fillGreenColor(path, "Sheet1", rowNum, 3);

            } else {
                System.out.println("Invalid Login Failed ❌");

                ExcelUtils.setCellData(path, "Sheet1", rowNum, 3, "FAIL");
                ExcelUtils.fillRedColor(path, "Sheet1", rowNum, 3);
            }

            // Reset page
            driver.navigate().to(prop.getProperty("url"));
        }
    }

    // 🔥 DataProvider with Row Index
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {

        String path = System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx";

        int rowCount = ExcelUtils.getRowCount(path, "Sheet1");

        Object data[][] = new Object[rowCount][4];

        for (int i = 1; i <= rowCount; i++) {

            data[i - 1][0] = i; // Row Number
            data[i - 1][1] = ExcelUtils.getCellData(path, "Sheet1", i, 0);
            data[i - 1][2] = ExcelUtils.getCellData(path, "Sheet1", i, 1);
            data[i - 1][3] = ExcelUtils.getCellData(path, "Sheet1", i, 2);
        }

        return data;
    }
}