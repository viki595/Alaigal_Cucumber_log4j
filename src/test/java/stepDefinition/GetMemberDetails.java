package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utility.Base;
import webelements.Admin;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class GetMemberDetails  extends Base {

   public static Logger logger =Logger.getLogger(GetMemberDetails.class);
   public static ArrayList<String> od=new ArrayList<>();

    @Given("^User On Login Page$")
    public void user_On_Login_Page() {
        PropertyConfigurator.configure("Log4j.properties");
        browser();
        PageFactory.initElements(driver,Admin.class);
        driver.get("http://alaigal.in/admin/login");

    }

    @Given("^Enters User Name \"([^\"]*)\" And The Password \"([^\"]*)\"$")
    public void enters_User_Name_And_The_Password(String name, String password)  {

        Admin.username.sendKeys(name);
        Admin.password.sendKeys(password);

    }

    @When("^User Clicks Login Button$")
    public void user_Clicks_Login_Button() throws InterruptedException, AWTException {

        Admin.login_button.click();
        Thread.sleep(1000);
        robot().keyPress(KeyEvent.VK_ESCAPE);
        robot().keyRelease(KeyEvent.VK_ESCAPE);

    }

    @Then("^User Should See The Admin Dashboard$")
    public void user_Should_See_The_Admin_Dashboard() {

        String check=Admin.dashboard_check.getText();
        Assert.assertEquals("Dashboards",check);

    }

    @When("^User Clicks On The MemberLounge$")
    public void user_Clicks_On_The_MemberLounge()  {

        Admin.member_lounge.click();

    }

    @Then("^User Should see The Members profiles$")
    public void user_Should_see_The_Members_profiles()  {

        String mcheck=Admin.member_lounge_check.getText();
        Assert.assertEquals("Member Lounge",mcheck);

    }

    @Then("^Members Names Are Saved In XL$")
    public void Members_Names_Are_Saved_In_XL() throws IOException {

        Workbook wb=new HSSFWorkbook();
        Sheet sh= wb.createSheet();
        Row row=sh.createRow(0);
        row.createCell(0).setCellValue("S.NO");
        row.createCell(1).setCellValue("NAME");
        row.createCell(2).setCellValue("INDUSTRY TYPE");
        row.createCell(3).setCellValue("COMPANY NAME");

        for (int i = 1; i <= 9; i++) {
            WebElement memb = driver.findElement(By.xpath("(//*[@class='item-name'])[" + i + "]"));
            WebElement indus = driver.findElement(By.xpath("(//*[@class='card-text item-description'])[" + i + "]"));
            WebElement comp = driver.findElement(By.xpath(" (//*[@class=\"add-to-cart\"])["+i+"]"));

            Row new_row = sh.createRow(i);
            new_row.createCell(0).setCellValue(i);
            new_row.createCell(1).setCellValue(memb.getText());
            new_row.createCell(2).setCellValue(indus.getText());
            new_row.createCell(3).setCellValue(comp.getText());

            OutputStream fileOut = new FileOutputStream("D://New_cucumber.xls");
            wb.write(fileOut);  

        }
    }

    @When("^User Loads Old Member Lounge Data$")
    public ArrayList<String> user_Loads_Old_Member_Lounge_Data() throws IOException {


        FileInputStream file_read=new FileInputStream("D://cucumber.xls");
        HSSFWorkbook workbook=new HSSFWorkbook(file_read);
        HSSFSheet sheet=workbook.getSheetAt(0);

        ArrayList<String> Old_members_data= new ArrayList<>();

        for (Row old_row : sheet) {
            Cell cell = old_row.getCell(1);
            String vm=cell.getStringCellValue();
            Old_members_data.add(vm);
        }
        od.addAll(Old_members_data);
        return Old_members_data; 

    }

    @Then("^Compare Old And New Data For New User$")
    public void compare_Old_And_New_Data_For_New_User() throws IOException {
        BasicConfigurator.configure();
        FileInputStream new_file_read=new FileInputStream("D://New_cucumber.xls");
        HSSFWorkbook n_workbook=new HSSFWorkbook(new_file_read);
        HSSFSheet n_sheet=n_workbook.getSheetAt(0);

        ArrayList<String> new_members_data= new ArrayList<>();

        for (Row new_row : n_sheet) {
            Cell cell = new_row.getCell(1);
            String nvm=cell.getStringCellValue();
            new_members_data.add(nvm);
        }

        HashSet<String> s1 = new HashSet<>(new_members_data);
        od.forEach(s1::remove);

        logger.info("OLD MEMBERS COUNT "+"  ----  "+od.size());
        logger.info("NEW MEMBERS COUNT "+"  ----  "+new_members_data.size());
        logger.info("NEWLY ADDED MEMBER IS "+"  -----  "+s1);

        good night();
        going to sleep();
    }
}

