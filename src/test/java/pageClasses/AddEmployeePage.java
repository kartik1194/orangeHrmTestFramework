package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AddEmployeePage {

    WebDriver driver;
    WebDriverWait wait;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ---------- Locators ----------
    By pimMenu = By.xpath("//span[text()='PIM']");
    By addEmployeeBtn = By.xpath("//a[text()='Add Employee']");

    By firstName = By.name("firstName");
    By middleName = By.name("middleName");
    By lastName = By.name("lastName");
    By employeeId = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    By saveBtn = By.xpath("//button[@type='submit']");

    // Loader
    By loader = By.className("oxd-form-loader");

    // ---------- Methods ----------

    public void clickPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeBtn)).click();

        // Wait for Add Employee page load
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    }

    public void addBasicDetails(String fname, String mname, String lname) {

        // Fill details
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
        driver.findElement(middleName).sendKeys(mname);
        driver.findElement(lastName).sendKeys(lname);

        // Generate valid Employee ID
        int empId = (int)(Math.random() * 900000) + 100000;

        driver.findElement(employeeId).clear();
        driver.findElement(employeeId).sendKeys(String.valueOf(empId));

        // 🔥 WAIT FOR LOADER TO DISAPPEAR (MAIN FIX)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

        // Click Save safely
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

        // Wait for next page (Personal Details)
        wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
    }
}