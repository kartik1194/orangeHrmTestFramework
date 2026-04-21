package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import basetest.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String reportName;

    // ✅ Report Start
    @Override
    public void onStart(ITestContext context) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportName);

        sparkReporter.config().setDocumentTitle("OrangeHRM Automation Report");
        sparkReporter.config().setReportName("HRM Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OrangeHRM");
        extent.setSystemInfo("Module", "Employee Management");
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
    }

    // ✅ Test Pass
    @Override
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.PASS, "Test Passed: " + result.getName());
    }

    // ❌ Test Fail (Screenshot Attached)
    @Override
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test Failed: " + result.getName());
        test.log(Status.INFO, result.getThrowable().getMessage());

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseClass) testClass).driver;

        try {
            String screenshotPath = ((BaseClass) testClass)
                    .captureScreen(driver, result.getName());

            test.addScreenCaptureFromPath(screenshotPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ⚠️ Test Skipped
    @Override
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Skipped: " + result.getName());
    }

    // 📊 Report End
    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        // Auto open report
        String path = System.getProperty("user.dir") + "/reports/" + reportName;
        File reportFile = new File(path);

        try {
            Desktop.getDesktop().browse(reportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}