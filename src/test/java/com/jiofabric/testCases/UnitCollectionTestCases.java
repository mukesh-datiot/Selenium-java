package com.jiofabric.testCases;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class UnitCollectionTestCases {

        private static WebDriver driver;

   //     public static String browser = "chrome";

//        @Before
//        @BeforeTest
//        public static void beforeClassSetup() {
//            System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chrome\\chromedriver.exe");
//            ChromeOptions options = new ChromeOptions();
//            options.setBinary("\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\"");
//            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//            driver = new ChromeDriver();
//            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//            driver.get("http://localhost:3000/");
//            driver.manage().window().maximize();
//
//        }

//    @After
//    @AfterTest
//    public static void afterClassSetup() {
//         driver.close();
//    }

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



//    public static void main(String[] args) throws Exception{
//        File src = new File("D:\\jiofabric\\automation\\src\\main\\resources\\unit.xlsx");
//        FileInputStream fis = new FileInputStream(src);
//        XSSFWorkbook wb = new XSSFWorkbook(fis);
//        XSSFSheet sheet1 = wb.getSheetAt(0);
//        String data0 = sheet1.getRow(0).getCell(0).getStringCellValue();
//        System.out.println("Data from excel is " +data0);
//
//    }

        @Test
        public void a_addUnit() throws Exception {
            String name = "";
            String unit = "";
            String selectedField = "";
//            List<String> aliases = new ArrayList<String>();
            String aliases = "";
            FileInputStream ip = new FileInputStream("D:\\jiofabric\\Selenium-java\\src\\main\\resources\\unit.xlsx");

            Workbook wb = WorkbookFactory.create(ip);
            Map<String, String> dataMap = new HashMap<String, String>();
            read(wb, "Main", dataMap);
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys){
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
                    String als = dataMap.get(s);
//                    aliases = Arrays.asList(als.split(",").clone());
                    aliases = als;
                }
            }
            ip.close();
            WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement addUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
            addUnitButton.click();
//            WebElement selectVertical = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-vertical-input']")));
//            selectVertical.click();
//            WebElement selectValueOfVertical = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[1]/div/div/select/option[2]")));
//            selectValueOfVertical.click();
            Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
            selectVertical.selectByVisibleText(selectedField);
            Thread.sleep(5000);
            WebElement addName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test--unit-name']")));
            addName.sendKeys(name);
            WebElement addUnits = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-unitName']")));
            addUnits.sendKeys(unit);
            Thread.sleep(2000);
//            WebElement addAliases = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[3]/fieldset/div[2]/div/div/div/div/div[1]/div[1]"));
//            addAliases.click();
 //           Thread.sleep(3000);


//            driver.findElement(By.xpath("//*[contains(text(),'Enter Aliases')]")).click();
//            Thread.sleep(1000);
//            WebElement addAliases1 = driver.findElement(By.xpath("//*[@id='react-select-3-input']"));
////            addAliases1.click();
//            Thread.sleep(2000);
            WebElement addAliases1 = driver.findElement(By.xpath("//input[starts-with(@id,'react-select-2-input')]"));
//            addAliases1.click();
//            Thread.sleep(2000);
//            for(String alias: aliases) {
//                addAliases1.sendKeys(alias + ",");
//                addAliases1.sendKeys(Keys.ENTER);
//                Thread.sleep(400);
//            }
            addAliases1.sendKeys(aliases);
            addAliases1.sendKeys(Keys.ENTER);
            Thread.sleep(4000);
//            WebElement addAliases2 = driver.findElement(By.xpath("//*[@id='react-select-5-input']"));
//            Thread.sleep(2000);
//            addAliases2.sendKeys("weerr");
//            Thread.sleep(2000);

//            Robot alises=new Robot();
//            for(int i=0;i<4;i++) {
//                alises.keyPress(KeyEvent.VK_N);
//                Thread.sleep(1000);
//                alises.keyRelease(KeyEvent.VK_N);
//            }
//
//            alises.keyPress(KeyEvent.VK_ENTER);
//            alises.keyRelease(KeyEvent.VK_ENTER);
//
//            for(int i=0;i<4;i++) {
//                alises.keyPress(KeyEvent.VK_U);
//                Thread.sleep(1000);
//                alises.keyRelease(KeyEvent.VK_U);
//            }
//
//            alises.keyPress(KeyEvent.VK_ENTER);
//            alises.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            WebElement saveDetailButton =driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
            saveDetailButton.click();
            Thread.sleep(2000);
            WebElement createMessageOnAddUnit = wait.until(ExpectedConditions.elementToBeClickable(By.id("swal2-title")));
            System.out.println(createMessageOnAddUnit.getText());
            Assert.assertEquals("Meta data saved successfully", createMessageOnAddUnit.getText());
        //    Assert.assertFalse("The unitName enter is not correct", driver.findElement(By.xpath("//*[text()='" + name + "']")).getText().equals(name));

        }

    @Test
    public void b_editUnit() throws Exception {
        String unitName = "";
        String updatedUnitName = "";
        FileInputStream ip = new FileInputStream("D:\\jiofabric\\Selenium-java\\src\\main\\resources\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        Map<String, String> dataMap = new HashMap<String, String>();
        read(wb, "Main", dataMap);
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys){
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
        editName.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        editName.clear();
        editName.sendKeys(updatedUnitName);
        Thread.sleep(2000);
        WebElement editUnits = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-unitName']")));
        editUnits.click();

        WebElement editPopUpOnEditUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
        System.out.println(editPopUpOnEditUnit.getText());
        Assert.assertEquals("unit name updated successfully", editPopUpOnEditUnit.getText());

    }

    @Test
    public void d_deleteUnit() throws Exception {
        String unitName = "";
        FileInputStream ip = new FileInputStream("D:\\jiofabric\\Selenium-java\\src\\main\\resources\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        Map<String, String> dataMap = new HashMap<String, String>();
        read(wb, "Main", dataMap);
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys){
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
                dataMap.put("selectedField", row.getCell(cellHeader.get("selectedField")).toString());
                dataMap.put("name", row.getCell(cellHeader.get("name")).toString());
                dataMap.put("unit", row.getCell(cellHeader.get("unit")).toString());
                dataMap.put("aliases", row.getCell(cellHeader.get("aliases")).toString());
                dataMap.put("unitName", row.getCell(cellHeader.get("unitName")).toString());
                dataMap.put("updatedUnitName", row.getCell(cellHeader.get("updatedUnitName")).toString());
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

    @Test
    public void createUnitWithBlankData() throws Exception {
        driver.get("http://localhost:3000/");
        WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement addUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
        addUnitButton.click();
        Thread.sleep(1000);
        WebElement saveDetailButton =driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
        saveDetailButton.click();
        WebElement errorOnNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div")));
        System.out.println(errorOnNameField.getText());
        Assert.assertEquals("Name is required", errorOnNameField.getText());
        WebElement errorOnUnitFiels = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[3]/fieldset/div[1]/div[1]/div/div")));
        System.out.println(errorOnUnitFiels.getText());
        Assert.assertEquals("unit name is required", errorOnUnitFiels.getText());

    }

    @Test
    public void createUnitWithBlankUnit() throws Exception {

        String name = "";
        String unit = "";
        FileInputStream ip = new FileInputStream("D:\\jiofabric\\automation\\src\\main\\resources\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        Map<String, String> dataMap = new HashMap<String, String>();
        read(wb, "Main", dataMap);
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys){
            System.out.println("s = " + s);
            if (s.equals("name")) {
                name = dataMap.get(s);
            }

        }
        ip.close();
        driver.get("http://localhost:3000/");
        WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement addUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
        addUnitButton.click();
        WebElement selectVertical = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.click();
        WebElement selectValueOfVertical = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[1]/div/div/select/option[2]")));
        selectValueOfVertical.click();
//        WebElement addName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test--unit-name']")));
//        addName.sendKeys(name);
        WebElement addName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test--unit-name']")));
        addName.sendKeys(name);
//        WebElement addUnits = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-unitName']")));
//        addUnits.sendKeys(unit);
        WebElement saveDetailButton =driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
        saveDetailButton.click();
//        WebElement errorOnNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div")));
//        System.out.println(errorOnNameField.getText());
//        Assert.assertEquals("Name is required", errorOnNameField.getText());
        WebElement errorOnUnitFiels = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[3]/fieldset/div[1]/div[1]/div/div")));
        System.out.println(errorOnUnitFiels.getText());
        Assert.assertEquals("unit name is required", errorOnUnitFiels.getText());

    }

    @Test
    public void createUnitWithBlankName() throws Exception {
        String unit = "";
        FileInputStream ip = new FileInputStream("D:\\jiofabric\\automation\\src\\main\\resources\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        Map<String, String> dataMap = new HashMap<String, String>();
        read(wb, "Main", dataMap);
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys){
            System.out.println("s = " + s);
            if (s.equals("unit")) {
                unit = dataMap.get(s);
            }
        }
        ip.close();
        driver.get("http://localhost:3000/");
        WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement addUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
        addUnitButton.click();
        WebElement selectVertical = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.click();
        WebElement selectValueOfVertical = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[1]/div/div/select/option[2]")));
        selectValueOfVertical.click();
        WebElement saveDetailButton =driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
        saveDetailButton.click();
        WebElement addUnits = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-unitName']")));
        addUnits.sendKeys(unit);
        WebElement errorOnNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div")));
        System.out.println(errorOnNameField.getText());
        Assert.assertEquals("Name is required", errorOnNameField.getText());

    }

    @Test
    public void clickingOnBackButton() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(1000);
        WebElement addUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
        addUnitButton.click();
        Thread.sleep(1000);
        WebElement clickOnBackButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-info ml-1']")));
        clickOnBackButton.click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='btn btn-primary']")).isDisplayed());

    }

    @Test
    public void c_clickingOnUnitButton() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
       // Thread.sleep(1000);
        WebElement addUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-icon btn-light btn-hover-danger btn-sm mx-3']")));
        addUnitButton.click();
        Thread.sleep(1000);
        WebElement unitList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='modal-content']")));
        Assert.assertTrue(unitList.isDisplayed());

    }


}

