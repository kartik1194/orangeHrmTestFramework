package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By txt_username = By.name("username");
    By txt_password = By.name("password");
    By btn_login = By.xpath("//button[@type='submit']");

    // Actions

    public void setUsername(String uname) {
        driver.findElement(txt_username).sendKeys(uname);
    }

    public void setPassword(String pwd) {
        driver.findElement(txt_password).sendKeys(pwd);
    }

    public void clickLogin() {
        driver.findElement(btn_login).click();
    }

    // ✅ ADD THIS METHOD
    public void login(String uname, String pwd) {
        setUsername(uname);
        setPassword(pwd);
        clickLogin();
    }
}