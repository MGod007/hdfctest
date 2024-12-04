package com.qa.hdfc.PageObjects;

public interface  BalanceSheet {

    public static String getLiabilityValue() {
        return LiabilityValue;
    }

    public static String getBalanceSheet() {
        return balanceSheet;
    }

    public static String getAssetValue() {
        return AssetValue;
    }

    public static String getReverseChangeValue() {
        return ReverseChangeValue;
    }

    public static String getYearColumn() {
        return yearColumn;
    }

    public static String getShareCapital() {
        return shareCapital;
    }

    public static String getReverseSurplus() {
        return reverseSurplus;
    }

    public static String getInvestments() {
        return Investments;
    }

    public static String getTotalDebt() {
        return totalDebt;
    }

    public static String getTotalLiabilities() {
        return totalLiabilities;
    }

    public static String getTotalAssets() {
        return totalAssets;
    }

    
        String  balanceSheet = "companyDetailsBox3";
        String  LiabilityValue = "//td[text()='Total Liabilities']/following-sibling::td"; 
        String  AssetValue = "//td[text()='Total Assets']/following-sibling::td"; 
        String  ReverseChangeValue = "//td[text()='Reserves And Surplus']/following-sibling::td";   
        String  yearColumn = "//div[@id='tblBalanceSheet']/descendant::th";
        String  shareCapital = "//div[@id='tblBalanceSheet']/descendant::tr[2]/td";
        String  reverseSurplus = "//div[@id='tblBalanceSheet']/descendant::tr[3]/td";
        String  Investments = "//div[@id='tblBalanceSheet']/descendant::tr[4]/td";
        String  totalDebt = "//div[@id='tblBalanceSheet']/descendant::tr[5]/td";
        String  totalLiabilities = "//div[@id='tblBalanceSheet']/descendant::tr[6]/td";
        String  totalAssets = "//div[@id='tblBalanceSheet']/descendant::tr[7]/td";

}
