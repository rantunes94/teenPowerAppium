package scenarios;

import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
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


import javax.xml.bind.Element;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TestCasePerfil3 {
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
        driver.tap(1,536,969,1000);
        driver.findElement(By.id("datePickerBirthDate")).sendKeys("1995");
        driver.tap(1,679,1116,1000);
        driver.findElement(By.id("itemSave")).click();

        driver.findElement(By.id("itemSave")).click();
        driver.quit();
    }

    @Test
    public void testTestCasePerfil2() throws InterruptedException {
        String app_package_name = "pt.ipleiria.teenpowerapp:id/";
        By userId = By.id(app_package_name + "editTextName");
        By password = By.id(app_package_name + "editTextUsername");
        By login_Button = By.id(app_package_name + "buttonLogIn");

        driver.findElement(userId).sendKeys("Testes01");
        driver.findElement(password).sendKeys("password");
        Thread.sleep(1000);
        driver.findElement(login_Button).click();
        Thread.sleep(1000);
        ///Este botão tem de desaparecer(botão guardar do perfil)
        Thread.sleep(2000);
        driver.findElement(By.id("itemSave")).click();
        ////

        Thread.sleep(2000);
        String activity = ((AndroidDriver<MobileElement>) driver).currentActivity();
        //System.out.println(activity);
        Thread.sleep(2000);
        Assert.assertEquals(".Game.HomeActivity",activity, "NÃO ESTOU NA ACTIVITY ESPERADA!!!");

        Thread.sleep(2000);
        driver.findElement(By.id("menuItemProfile")).click();


        Thread.sleep(1000);
        String activity2 = ((AndroidDriver<MobileElement>) driver).currentActivity();
        Assert.assertEquals(".Profile.ProfileActivity",activity2, "NÃO ESTOU NA ACTIVITY ESPERADA!!!");

        //SWIPE
        int starty=1122;
        int endy=173;
        int startx=366;
        driver.swipe(startx,starty,startx,endy,5000);
        // Fim de SWIPE

        /*
        536
        969
        */
        driver.tap(1,536,969,1000);
        By datePicker = By.id(app_package_name + "datePickerBirthDate");
        driver.findElement(datePicker).sendKeys("1998");
        Thread.sleep(1000);
        driver.tap(1,679,1116,1000);
        driver.findElement(By.id("itemSave")).click();

        Thread.sleep(1000);
        String activity3 = ((AndroidDriver<MobileElement>) driver).currentActivity();
        Assert.assertEquals(".Game.HomeActivity",activity3, "Após alterar o perfil e submeter com sucesso seria de esperar ser redirecionado para a activity HomeActivity");


        driver.findElement(By.id("menuItemProfile")).click();
        driver.swipe(startx,starty,startx,endy,5000);
        //driver.tap(1,536,969,1000);


        Assert.assertTrue(driver.findElement(By.id("pt.ipleiria.teenpowerapp:id/datePickerBirthDate")).getText().contains("1998"));
        driver.findElement(By.id("itemSave")).click();
        /*
        boolean str2= driver.findElement(datePicker).getText().contains("1998");

        if (str2 = false )
        {
            Assert.assertEquals("True",str2,"Ao alterar o ano da datePickerBirthDate no perfil  " +
                    "e guardar a alteração verificou-se que esta não foi gravada");;
        }
        driver.findElement(By.id("itemSave")).click();
        */

    }
}
