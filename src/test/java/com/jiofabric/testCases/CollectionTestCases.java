package com.jiofabric.testCases;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.junit.AfterClass;
import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
import org.junit.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CollectionTestCases {

    private static WebDriver driver;

    public static String browser = "chrome";

    @Before
    @BeforeTest
    public static void beforeClassSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\"");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("http://localhost:3000/");
        driver.manage().window().maximize();

    }

    @After
    @AfterTest
    public static void afterClassSetup() {
        driver.close();
    }


    @Test
    public void addCollectionWithoutVerticalAndTypeField() throws Exception {
//        String name = "";
//        String unit = "";
//        FileInputStream ip = new FileInputStream("D:\\jiofabric\\automation\\src\\main\\resources\\unit.xlsx");
//
//        Workbook wb = WorkbookFactory.create(ip);
//        Map<String, String> dataMap = new HashMap<String, String>();
//        read(wb, "Main", dataMap);
//        Set<String> mapKeys = dataMap.keySet();
//        for (String s : mapKeys){
//            System.out.println("s = " + s);
//            if (s.equals("name")) {
//                name = dataMap.get(s);
//            }
//
//            if (s.equals("unit")) {
//                unit = dataMap.get(s);
//            }
//        }
//        ip.close();    [class='btn btn-primary']
        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(1000);
        WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]")));
        collectionButton.click();
        Thread.sleep(1000);
        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        addCollectionButton.click();
        Thread.sleep(1000);
        WebElement clickOnSaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        clickOnSaveButton.click();
        WebElement errorOnVerticalField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[1]/div/div/div")));
        System.out.println(errorOnVerticalField.getText());
        Assert.assertEquals("Vertical is required", errorOnVerticalField.getText());
        WebElement errorOnTypeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div")));
        System.out.println(errorOnTypeField.getText());
        Assert.assertEquals("Choose collection type", errorOnTypeField.getText());
    }

    @Test
    public void addCollectionWithoutSelectType() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(1000);
        WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]")));
        collectionButton.click();
        Thread.sleep(1000);
        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        addCollectionButton.click();
        Thread.sleep(1000);
        WebElement selectValueOfVertical = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[1]/div/div/select/option[2]")));
        selectValueOfVertical.click();
        WebElement clickOnSaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        clickOnSaveButton.click();
        WebElement errorOnTypeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div")));
        System.out.println(errorOnTypeField.getText());
        Assert.assertEquals("Choose collection type", errorOnTypeField.getText());
    }

    @Test
    public void addCollectionWithoutSelectVertical() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(1000);
        WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]")));
        collectionButton.click();
        Thread.sleep(1000);
        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        addCollectionButton.click();
        Thread.sleep(1000);
        Assert.assertFalse(driver.findElement(By.cssSelector("[data-test='test-select']")).isEnabled());
    }

    @Test
    public void clickingOnBackButton() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(2000);
        WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]")));
        collectionButton.click();
        Thread.sleep(2000);
        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        addCollectionButton.click();
        WebElement clickOnBackButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-info ml-1']")));
        clickOnBackButton.click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.cssSelector("[class='btn btn-primary']")).isDisplayed());

    }

    private static void read(Workbook wb, String main, Map<String, String> dataMap){
        Sheet sheet = wb.getSheet(main);
        int i, j;
        Map<String, Integer> cellHeader = new HashMap<String, Integer>();
        for (i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (i == 0) {
                for (j = 0; j < row.getLastCellNum(); j++) {
                    cellHeader.put(row.getCell(j).getStringCellValue(), j);
                }
                continue;
            }
//            if (main.equals("Main")) {
            System.out.print(row.getCell(cellHeader.get("name")) + "\t\t\t");
            dataMap.put("name", row.getCell(cellHeader.get("name")).toString());
            dataMap.put("unit", row.getCell(cellHeader.get("unit")).toString());
            dataMap.put("unitName", row.getCell(cellHeader.get("unitName")).toString());
//                System.out.print(row.getCell(cellHeader.get("Datasheet")) + "\t\t\t");
//                System.out.print(row.getCell(cellHeader.get("Execute")) + " Main \t\t\t");
            System.out.println();
//                nnn
//            }
//            else {
//                if (i == 0) {
//                    dataList = new ArrayList<Map<String, String>>();
//                }
//                if (row.getCell(cellHeader.get("Execute")).toString().equals("Yes")) {
//                    Map<String, String> data = new HashMap<String, String>();
//                    Set<String> mapKey = cellHeader.keySet();
//                    for (String s : mapKey){
//                        if (row.getCell(cellHeader.get(s)) != null) {
//                            data.put(s, row.getCell(cellHeader.get(s)).toString());
//                        }
//                    }
//                    dataList.add(data);
//                }
//            }
        }
    }
}
