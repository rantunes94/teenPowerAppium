package scenarios;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TestCaseLoginFalhado3 {
    protected AndroidDriver driver;

    @BeforeTest
    protected void prepareAndroidForAppium() throws MalformedURLException {
        File appDir = new File("C:\\Users\\Ruben\\Desktop\\androidAPP\\teen_power-android-app-0100bc6841bf\\app\\build\\intermediates\\instant-run-apk\\debug");
        //File appDir = new File("C:\\Users\\Ruben\\Desktop\\teen_power-android-app-0100bc6841bf\\app\\build\\intermediates\\instant-run-apk\\debug");
        File app = new File(appDir, "app-debug.apk");

        // Capabilites
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","Android");

        //mandatory capabilities
        //capabilities.setCapability("deviceName","Nexus 4 API 22");
        capabilities.setCapability("deviceName","appAndroid");
        capabilities.setCapability("platformName","Android");

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void testTestCaseLoginFalhado3() throws InterruptedException {
        String app_package_name = "pt.ipleiria.teenpowerapp:id/";
        By userId = By.id(app_package_name + "editTextName");
        By password = By.id(app_package_name + "editTextUsername");
//        By showPassword = By.id(app_package_name + "show_password");
        By login_Button = By.id(app_package_name + "buttonLogIn");

        driver.findElement(password).sendKeys("testvagrant123");
        //driver.findElement(showPassword).click();
        driver.findElement(login_Button).click();
        driver.findElement(userId).click();

        //By alert = By.id("android:id/alertTitle");
        Thread.sleep(2000);
        //String result = driver.findElementById("android:name/error_field_required").getText();


        String activity = ((AndroidDriver<MobileElement>) driver).currentActivity();
        System.out.println(activity);
        Assert.assertEquals("",".Authentication.LoginActivity",activity);

        

    }
}
