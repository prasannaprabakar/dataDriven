package login;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

public class login
{
    WebDriver driver;
    String data[][]=null;
    @BeforeTest
    public void chrome(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));


    }
    @DataProvider(name="loginData")
    public Object[][] logindataProvider() throws IOException
    {
        data=getExcelData();
        return data;
    }
    public String[][] getExcelData() throws IOException
    {

        String userDir=System.getProperty("user.dir");
        String pathSeparator = System.getProperty("file.separator");
        String file_path=userDir+pathSeparator+"src"+pathSeparator+"main"+pathSeparator+"resources"+pathSeparator+"login.xlsx";
        FileInputStream excel=new FileInputStream(file_path);//C:\Users\prasanna.prabakaran\Downloads\login.xlsx
        //Workbook workbook = new Workbook.getWorkbook(excel);
        XSSFWorkbook workbook=new XSSFWorkbook(excel);
        int noOfSheets=workbook.getNumberOfSheets();// count the no of sheets
        XSSFSheet sheet=workbook.getSheetAt(0);
        int lengthOfRows = sheet.getLastRowNum();
        int lengthOfCells = sheet.getRow(1).getLastCellNum();
        String testData[][]=new String[lengthOfRows-1][lengthOfCells];
       for(int rowIndex=1;rowIndex<lengthOfRows;rowIndex++){
            XSSFRow rowObj = sheet.getRow(rowIndex);
            for(int cellIndex=0;cellIndex<lengthOfCells;cellIndex++){
              XSSFCell cellObj = rowObj.getCell(cellIndex);
                testData[rowIndex-1][cellIndex]= String.valueOf(cellObj);
                System.out.println( testData[rowIndex-1][cellIndex]);

            }
        }
return testData;
    }
        @Test(dataProvider = "loginData")
        //  @Parameters({"username","password"})
        public void username_password(String uname,String pwd) throws InterruptedException
        {

        driver.get("https://demoqa.com/login");
        WebElement username=driver.findElement(By.id("userName"));
        username.sendKeys(uname);
        WebElement password=driver.findElement(By.id("password"));
        password.sendKeys(pwd);
        WebElement button=driver.findElement(By.xpath("//button[@id='login']"));
        //Thread.sleep(3000);
        button.click();
        //driver.close();

    }
    @AfterTest
    public void closeDrive(){
        driver.quit();
    }
}