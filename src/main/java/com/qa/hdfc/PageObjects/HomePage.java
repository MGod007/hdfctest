package com.qa.hdfc.PageObjects;

public interface HomePage {

    public static String getPopupclose() {
        return popupclose;
    }

    public static String getStockFrame() {
        return stockFrame;
    }

    public static String getStockOption() {
        return stockOption;
    }

    public static String getSelectAlbhabet() {
        return selectAlbhabet;
    }

    public static String getSelectStock() {
        return selectStock;
    }

    public static String getStockName() {
        return StockName;
    }

    public static String getStockSearchbox() {
        return stockSearchbox;
    }

    public static String getChooseStockOption() {
        return chooseStockOption;
    }
    String  stockFrame = "/html/body/noscript[2]/text()";
    String  stockOption = "//img[@alt='equity markets']";
    String  selectAlbhabet = "//a[@title='List of Stock for I']";
    String  selectStock = "//a[text()='ICICI Bank Ltd']";
    String  popupclose = "closepopup";
    public static final String  StockName = "//a[text()='%s']";
    String stockSearchbox = "//input[@id='search-input']";
    String  chooseStockOption = "//span[@title='%s']";

  
}
