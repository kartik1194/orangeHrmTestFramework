package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {

    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By profileIcon = By.xpath("//p[@class='oxd-userdropdown-name']");
    By logoutBtn = By.xpath("//a[text()='Logout']");

    // Methods
    public void clickProfile() {
        driver.findElement(profileIcon).click();
    }

    public void clickLogout() {
        driver.findElement(logoutBtn).click();
    }
}