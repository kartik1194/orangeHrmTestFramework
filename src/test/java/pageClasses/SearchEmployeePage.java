package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchEmployeePage {

    WebDriver driver;

    public SearchEmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By pimMenu = By.xpath("//span[text()='PIM']");
    By employeeName = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    By searchBtn = By.xpath("//button[@type='submit']");
    By resultTable = By.xpath("//div[@class='oxd-table-body']");

    // Methods

    public void clickPIM() {
        driver.findElement(pimMenu).click();
    }

    public void searchEmployee(String name) {
        driver.findElement(employeeName).sendKeys(name);
        driver.findElement(searchBtn).click();
    }

    public boolean isEmployeeDisplayed() {
        return driver.findElement(resultTable).isDisplayed();
    }
}
