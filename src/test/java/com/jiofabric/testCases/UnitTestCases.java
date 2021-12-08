package com.jiofabric.testCases;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.util.*;


public class UnitTestCases {
    private static WebDriver driver;

    @Before
    @BeforeTest
    public static void beforeClassSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\"");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        //  driver = new ChromeDriver();
        //  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//            driver.get("http://localhost:3000/");
//            driver.manage().window().maximize();

    }

    @BeforeMethod
    public static void beforeMethodSetup(){
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public static void afterMethodSetup(){
        driver.close();
    }

    @After
    @AfterTest
    public static void afterClassSetup() {
        //  driver.close();
    }

    @Test
    public void a_addUnit() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        String name = "";
        String unit = "";
        String selectedField = "";
        String aliases = "";
        FileInputStream ip = new FileInputStream("D:\\jiofabric\\Selenium-java\\src\\main\\resources\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Main");
        for (Map<String, String> dataMap : dataList) {
            name = "";
            unit = "";
            selectedField = "";
            aliases = "";
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                System.out.println("s = " + s);
                if (s.equals("selectedField")) {
                    selectedField = dataMap.get(s);
                }
                if (s.equals("name")) {
                    name = dataMap.get(s);
                }

                if (s.equals("unit")) {
                    unit = dataMap.get(s);
                }
                if (s.equals("aliases")) {
                    aliases = dataMap.get(s);
                }
            }

            ip.close();
            WebElement addUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
            addUnitButton.click();
            Thread.sleep(1000);
            Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
            selectVertical.selectByVisibleText(selectedField);
            Thread.sleep(5000);
            WebElement addName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test--unit-name']")));
            addName.sendKeys(name);
            WebElement addUnits = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-unitName']")));
            addUnits.sendKeys(unit);
            Thread.sleep(1000);
            WebElement addAliases1 = driver.findElement(By.xpath("//input[starts-with(@id,'react-select')]"));
            addAliases1.sendKeys(aliases);
            addAliases1.sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            WebElement saveDetailButton = driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
            saveDetailButton.click();
            Thread.sleep(2000);
            WebElement createMessageOnAddUnit = wait.until(ExpectedConditions.elementToBeClickable(By.id("swal2-title")));
            System.out.println(createMessageOnAddUnit.getText());
            Assert.assertEquals("Meta data saved successfully", createMessageOnAddUnit.getText());
            //    Assert.assertFalse("The unitName enter is not correct", driver.findElement(By.xpath("//*[text()='" + name + "']")).getText().equals(name));

            //   closePath();
            Thread.sleep(2000);
        }
    }

        @Test
        public void b_editUnit() throws Exception {
            String unitName = "";
            String updatedUnitName = "";
            FileInputStream ip = new FileInputStream("D:\\jiofabric\\Selenium-java\\src\\main\\resources\\unit.xlsx");

            Workbook wb = WorkbookFactory.create(ip);
            List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
            dataList = read(wb, "Main");
            for (Map<String, String> dataMap : dataList) {
                Set<String> mapKeys = dataMap.keySet();
                for (String s : mapKeys) {
                    System.out.println("s = " + s);
                    if (s.equals("unitName")) {
                        unitName = dataMap.get(s);
                    }

                    if (s.equals("updatedUnitName")) {
                        updatedUnitName = dataMap.get(s);
                    }
                }
                ip.close();

                WebDriverWait wait = new WebDriverWait(driver, 50);
                WebElement searchUnitByName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='form-control']")));
                searchUnitByName.sendKeys(unitName);
                Thread.sleep(1000);
                WebElement editUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-unit")));
                editUnitButton.click();
                Thread.sleep(2000);
                WebElement editName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test--unit-name']")));
                editName.click();
                editName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                editName.clear();
                editName.sendKeys(updatedUnitName);
                Thread.sleep(2000);
                WebElement editUnits = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-unitName']")));
                editUnits.click();

                WebElement editPopUpOnEditUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
                System.out.println(editPopUpOnEditUnit.getText());
                Assert.assertEquals("unit name updated successfully", editPopUpOnEditUnit.getText());
                Thread.sleep(500);
                WebElement clickOnBackButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-info ml-1']")));
                clickOnBackButton.click();

            }
        }

    @Test
    public void d_deleteUnit() throws Exception {
        String unitName = "";
        FileInputStream ip = new FileInputStream("D:\\jiofabric\\Selenium-java\\src\\main\\resources\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Main");
        for (Map<String, String> dataMap : dataList) {
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                System.out.println("s = " + s);
                if (s.equals("unitName")) {
                    unitName = dataMap.get(s);
                }
            }
            ip.close();

            driver.get("http://localhost:3000/");
            WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement searchUnitByName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='form-control']")));
            searchUnitByName.sendKeys(unitName);
            WebElement editUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete-unit")));
            editUnitButton.click();
            Thread.sleep(2000);
            WebElement editName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='swal2-confirm swal2-styled']")));
            editName.click();
            WebElement deletePopUpOnDeleteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
            System.out.println(deletePopUpOnDeleteUnit.getText());
            Assert.assertEquals("Unit Deleted successfully", deletePopUpOnDeleteUnit.getText());
        }
    }

//    private static void openPath() throws Exception{
//        System.out.println("Open Path");
//        WebDriverWait wait = new WebDriverWait(driver, 50);
//        WebElement addUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
//        addUnitButton.click();
//        Thread.sleep(1000);
//    }
//
//    private static void closePath() {
//        System.out.println("Close Path");
//    }

    private static List<Map<String, String>> read(Workbook wb, String main){
        Sheet sheet = wb.getSheet(main);
        List dataList = new ArrayList<Map<String, String>>();
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
            System.out.println("&&&&&&&&& "+ row.getCell(cellHeader.get("Execute")).toString().equals("Yes"));
            if (row.getCell(cellHeader.get("Execute")).toString().equals("Yes")) {
                Map<String, String> data = new HashMap<String, String>();
                Set<String> mapKey = cellHeader.keySet();
                for (String s : mapKey){
                    if (row.getCell(cellHeader.get(s)) != null) {
                        data.put(s, row.getCell(cellHeader.get(s)).toString());
                    }
                }
                dataList.add(data);
            }
        }

        System.out.println("dataList " + dataList.toString());
        return dataList;
    }

}

