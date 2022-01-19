package webelements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Admin {

    @FindBy(xpath="//input[@name='email']")
    public static WebElement username;

    @FindBy(xpath="//input[@name='password']")
    public static WebElement password;

    @FindBy(xpath="//button[@tabindex='4']")
    public static WebElement login_button;

    @FindBy(xpath="(//*[@class='menu-title text-truncate'])[1]")
    public static WebElement dashboard_check;

    @FindBy(xpath="//*[text()='Member Lounage'] ")
    public static WebElement member_lounge;

    @FindBy(xpath="//*[@class='content-header-title float-start mb-0']")
    public static WebElement member_lounge_check;

    @FindBy(xpath="//*[@class='item-name']")
    public static WebElement members_name;
}
