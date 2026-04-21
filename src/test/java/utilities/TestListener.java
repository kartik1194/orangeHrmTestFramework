//package utilities;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import basetest.BaseClass;
//
//public class TestListener implements ITestListener {
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//
//        // Get driver from BaseClass
//        Object testClass = result.getInstance();
//        WebDriver driver = ((BaseClass) testClass).driver;
//
//        // Safety check
//        if (driver != null) {
//
//            String testName = result.getName();
//            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//            try {
//                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//                String path = System.getProperty("user.dir") + "/screenshots/"
//                        + testName + "_" + timeStamp + ".png";
//
//                File dest = new File(path);
//                FileUtils.copyFile(src, dest);
//
//                System.out.println("📸 Screenshot captured: " + path);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            System.out.println("⚠️ Driver is null, screenshot not captured");
//        }
//    }
//}