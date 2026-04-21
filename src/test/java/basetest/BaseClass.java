package basetest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    public WebDriver driver;
    public Properties prop;

    @BeforeClass
    public void setup() throws IOException {

        // 1. Config file load karna
        FileInputStream file = new FileInputStream(
            ".//src//test//resources//config.properties"
        );
        prop = new Properties();
        prop.load(file);

        // 2. Browser setup
        String browser = prop.getProperty("browser");

        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        // 3. Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 4. URL open
        driver.get(prop.getProperty("url"));

        // 5. Maximize
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

public String captureScreen(WebDriver driver, String testName) throws IOException {

    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    String path = System.getProperty("user.dir") + "/screenshots/"
                + testName + "_" + timeStamp + ".png";

    File dest = new File(path);
    FileUtils.copyFile(src, dest);

    return path;
}}