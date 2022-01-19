package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Base {

   public static  WebDriver driver;

    public static WebDriver browser() {

        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver97.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    public static Robot robot() throws AWTException {

        Robot robot = new Robot();
        return robot;
    }
}
