package scenarios;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TestCaseChat2 {
    protected AndroidDriver driver;

    @BeforeTest
    protected void prepareAndroidForAppium() throws MalformedURLException {
        //File appDir = new File("C:\\Users\\Ruben\\Desktop\\androidAPP\\teen_power-android-app-0100bc6841bf\\app\\build\\intermediates\\instant-run-apk\\debug");
        File appDir = new File("C:\\Users\\Ruben\\Desktop\\teen_power-android-app-0100bc6841bf\\app\\build\\intermediates\\instant-run-apk\\debug");

        //File appDir = new File(" C:\\Projetos\\androidAPP\\app\\build\\intermediates\\instant-run-apk\\debug");
        File app = new File(appDir, "app-debug.apk");

        // Capabilites
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName","appAndroid");
        capabilities.setCapability("platformName","Android");

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @AfterTest
    public void afterTest( ) {
        driver.findElement(By.id("menuItemProfile")).click();
        Dimension size = driver.manage().window().getSize();
        int starty=(int)(size.height*0.5);
        int endy=(int)(size.height*0.15);
        int startx=size.width/2;
        driver.swipe(startx,starty,startx,endy,2000);
        driver.findElement(By.id("radioButtonMale")).click();
        driver.findElement(By.id("itemSave")).click();
        driver.quit();
    }

    @Test
    public void testTestCasePerfil2() throws InterruptedException {
        String app_package_name = "pt.ipleiria.teenpowerapp:id/";
        By userId = By.id(app_package_name + "editTextName");
        By password = By.id(app_package_name + "editTextUsername");
        By login_Button = By.id(app_package_name + "buttonLogIn");
        By escrever_mensg = By.id(app_package_name + "editTextPost");


        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.findElement(userId).sendKeys("Testes01");
        driver.findElement(password).sendKeys("password");
        driver.findElement(login_Button).click();
        Thread.sleep(2000);
        ///Este botão tem de desaparecer(botão guardar do perfil)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("itemSave")));
        Thread.sleep(2000);
        driver.findElement(By.id("itemSave")).click();
        ////

        Thread.sleep(2000);
        String activity = ((AndroidDriver<MobileElement>) driver).currentActivity();
        //System.out.println(activity);
        Thread.sleep(2000);
        Assert.assertEquals(".Game.HomeActivity",activity, "NÃO ESTOU NA ACTIVITY ESPERADA!!!");

        Thread.sleep(2000);
        driver.findElement(By.id("menuItemChat")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("action_search")).click();
        //driver.findElement(userId).sendKeys("Testes02");
        Thread.sleep(2000);

        driver.tap(1,305,226,1000);
        Thread.sleep(1111);
        driver.findElement(escrever_mensg).click();
        Thread.sleep(1000);
        driver.tap(1,452,882,1000);
        //driver.findElement(escrever_mensg).sendKeys("Olá isto é um teste");
        Thread.sleep(1000);
        driver.tap(1,710,566,1000);


    }
}

