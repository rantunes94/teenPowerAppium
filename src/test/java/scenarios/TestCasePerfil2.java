package scenarios;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

public class TestCasePerfil2 {
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

        driver.findElement(userId).sendKeys("Testes01");
        driver.findElement(password).sendKeys("password");
        driver.findElement(login_Button).click();

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

        Dimension size = driver.manage().window().getSize();
        int starty=(int)(size.height*0.5);
        int endy=(int)(size.height*0.15);
        int startx=size.width/2;
        driver.swipe(startx,starty,startx,endy,2000);
        driver.findElement(By.id("radioButtonFemale")).click();
        driver.findElement(By.id("itemSave")).click();

        Thread.sleep(1000);
        String activity3 = ((AndroidDriver<MobileElement>) driver).currentActivity();
        Assert.assertEquals(".Game.HomeActivity",activity3, "Após alterar o perfil e submeter com sucesso seria de esperar ser redirecionado para a activity HomeActivity");


        driver.findElement(By.id("menuItemProfile")).click();
        driver.swipe(startx,starty,startx,endy,2000);
        String str = driver.findElement(By.id("radioButtonFemale")).getAttribute("checked");

        if (str.equalsIgnoreCase("false"))
        {
            Assert.assertEquals("True",str,"Ao alterar o sexo " +
                    "do utilizador de masculino->feminino e guardar a alteração verificou-se que esta não foi gravada");;
        }
        driver.findElement(By.id("itemSave")).click();

    }
}
