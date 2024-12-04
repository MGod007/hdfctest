package com.qa.hdfc.PageEvents;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.opencsv.CSVWriter;
import com.qa.hdfc.PageObjects.BalanceSheet;
import com.qa.hdfc.PageObjects.HomePage;


public class TestClass implements HomePage, BalanceSheet{
    WebDriver   driver  = new ChromeDriver();
    String csvFile = "hdfctest\\src\\test\\java\\com\\qa\\hdfc\\resources\\BalanceSheet.csv";
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    double AssetGrowth;
    double LiabilityGrowth;
    double ReverseChangeGrowth;
    private ExtentReports extent;
    private ExtentTest test;
    
      @BeforeClass
      public void setUp() throws InterruptedException{
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        driver.get("https://www.hdfcsec.com/");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(HomePage.getPopupclose()))).click();
    }

    @Test
    public void searchStock(String stock) throws InterruptedException{
        driver.findElement(By.id(HomePage.getPopupclose())).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(HomePage.getStockOption())));
        actions.perform();
        driver.findElement(By.xpath(HomePage.getStockOption())).click();
        ChromeOptions  chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("--disable-notifications");
        driver.findElement(By.xpath(HomePage.getSelectAlbhabet())).click();
        driver.findElement(By.xpath(String.format(HomePage.StockName, stock))).click();
       
    }

    @Test
    public void validateLiabilityGrowth_In_3Year() throws InterruptedException{
        test = extent.createTest("Validate Liability growth in 3 years in percentage");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(HomePage.getPopupclose())));
        driver.findElement(By.id(HomePage.getPopupclose())).click();
        driver.findElement(By.id(BalanceSheet.getBalanceSheet())).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BalanceSheet.getLiabilityValue())));
        List<WebElement>  list =  driver.findElements(By.xpath(BalanceSheet.getLiabilityValue()));
        double year2024 = Double.parseDouble(list.get(0).getText().replace(",", ""));
        double year2023 = Double.parseDouble(list.get(1).getText().replace(",", ""));
        double year2022 = Double.parseDouble(list.get(2).getText().replace(",", ""));
        LiabilityGrowth = (year2024 - year2022)*100/year2022;
        System.out.println("Total Liability growth in 3 years in percentage:- " + LiabilityGrowth);
        test.pass("Total Reverse change growth in 3 years in percentage:-" + LiabilityGrowth);
    }
    
    @Test
    public void validateAssetGrowth_In_3Year(){
        test = extent.createTest("Validate Asset growth in 3 years in percentage");
        List<WebElement>  list =  driver.findElements(By.xpath(BalanceSheet.getAssetValue()));

        double year2024 = Double.parseDouble((list.get(0).getText().replace(",", "")));
        double year2023 = Double.parseDouble(list.get(1).getText().replace(",", ""));
        double year2022 = Double.parseDouble(list.get(2).getText().replace(",", ""));
        AssetGrowth = (year2024 - year2022)*100/year2022;
        System.out.println("Total Asset growth in 3 years in percentage:- " + AssetGrowth);
        test.pass("Total Reverse change growth in 3 years in percentage:-" + AssetGrowth);
    }

    @Test
    public void validateReverceChangeGrowth_In_3Year(){
        test = extent.createTest("Validate Reverse Change growth in 3 years in percentage");
        List<WebElement>  list =  driver.findElements(By.xpath(BalanceSheet.getReverseChangeValue()));
        
        double year2024 = Double.parseDouble((list.get(0).getText().replace(",", "")));
        double year2023 = Double.parseDouble(list.get(1).getText().replace(",", ""));
        double year2022 = Double.parseDouble(list.get(2).getText().replace(",", ""));
        ReverseChangeGrowth = (year2024 - year2022)*100/year2022;
        System.out.println("Total Reverse change growth in 3 years in percentage:- " + ReverseChangeGrowth);
        test.pass("Total Reverse change growth in 3 years in percentage:-" + ReverseChangeGrowth);
    }

    @Test
    public void saveBalanceSheet_CSV(){
        test = extent.createTest("Add balancesheet data to Balancesheet CSV file");
        List<WebElement>  yearlist =  driver.findElements(By.xpath(BalanceSheet.getYearColumn()));
        List<WebElement>  shareCapitalElements =  driver.findElements(By.xpath(BalanceSheet.getShareCapital()));
        List<WebElement>  reservesSurplus =  driver.findElements(By.xpath(BalanceSheet.getReverseSurplus()));
        List<WebElement>  investments =  driver.findElements(By.xpath(BalanceSheet.getInvestments()));
        List<WebElement>  totalDebtElements =  driver.findElements(By.xpath(BalanceSheet.getTotalDebt()));
        List<WebElement>  totalLiabilitiesElements =  driver.findElements(By.xpath(BalanceSheet.getTotalLiabilities()));
        List<WebElement>  totalAssetsElements =  driver.findElements(By.xpath(BalanceSheet.getTotalAssets()));

        
        ArrayList<String>  column= new ArrayList<>();
        ArrayList<String>  shareCapitalData = new ArrayList<>();
        ArrayList<String>  reservesSurplusData = new ArrayList<>();
        ArrayList<String>  investmentsData = new ArrayList<>();
        ArrayList<String>  totalDebtData = new ArrayList<>();
        ArrayList<String>  totalLiabilitiesData = new ArrayList<>();
        ArrayList<String>  totalAssetsData = new ArrayList<>();
        ArrayList<Double>  Growth = new ArrayList<>();
        Growth.add(LiabilityGrowth);
        Growth.add(AssetGrowth);
        Growth.add(ReverseChangeGrowth);


        for (WebElement elem: yearlist) {
            column.add(elem.getText().replace("\n" , ""));
        }
        
        for (WebElement elem: shareCapitalElements) {
            shareCapitalData.add(elem.getText().replaceAll("\"", ""));
        }
        
        for (WebElement elem: reservesSurplus) {
            reservesSurplusData.add(elem.getText());
        }
        
        for (WebElement elem: investments) {
            investmentsData.add(elem.getText());
        }
        for (WebElement elem: totalDebtElements) {
            totalDebtData.add(elem.getText());
        }
        for (WebElement elem: totalLiabilitiesElements) {
            totalLiabilitiesData.add(elem.getText());
        }
        for (WebElement elem: totalAssetsElements) {
            totalAssetsData.add(elem.getText());
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {
            
            for (int j = 0; j < column.size(); j++) {
                String[] record = { column.get(j), shareCapitalData.get(j), reservesSurplusData.get(j), investmentsData.get(j), totalDebtData.get(j), totalLiabilitiesData.get(j), totalAssetsData.get(j) }; 
                String[]  growthRecord = {"Growth Percentage", Growth.get(0).toString(), Growth.get(1).toString(), Growth.get(2).toString() };
                writer.writeNext(record);
                writer.writeNext(growthRecord);
            }
            
            System.out.println("Data written to CSV file successfully.");
            writer.close();
        } catch (IOException e) {
            
        }
        test.pass("Successfully added balancesheet data to CSV file.");
        
    }

    @Test
    public void selectStock(String stockName) throws InterruptedException{
        test = extent.createTest("Search and select stock");
        
        driver.findElement(By.xpath(HomePage.getStockSearchbox())).click();
        driver.findElement(By.xpath(HomePage.getStockSearchbox())).sendKeys(stockName);
        // wait.until(d -> driver.findElement(By.xpath(String.format(HomePage.getChooseStockOption(), stockName))).isDisplayed());
        new Actions(driver).moveToElement(driver.findElement(By.xpath(HomePage.getStockSearchbox()))).click().perform();
        new Actions(driver).moveToElement(driver.findElement(By.xpath(HomePage.getStockSearchbox()))).sendKeys(Keys.RETURN).perform();
        Thread.sleep(6000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(HomePage.getChooseStockOption(), stockName)))).click();
        // driver.findElement(By.xpath(String.format(HomePage.getChooseStockOption(), stockName))).click();
        validateLiabilityGrowth_In_3Year();
        validateAssetGrowth_In_3Year();
        validateReverceChangeGrowth_In_3Year();
        saveBalanceSheet_CSV();
        test.pass("Successfully opened selected stock and navigated to stock page.");
    }

    @AfterClass
    public void closeSetup(){
        driver.quit();
        extent.flush();
    }

}

