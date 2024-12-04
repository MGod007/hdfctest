package com.qa.hdfc.BaseClass;

import com.qa.hdfc.PageEvents.TestClass;


public final class App  {
    
    
    private App() {
    }

         public static void main(String[] args) throws InterruptedException {
    
        TestClass  testClass  = new TestClass();
        testClass.setUp();
        
        
        testClass.selectStock("ICICI Bank Ltd");
        testClass.selectStock("Infosys Ltd");
        testClass.selectStock("HDFC Bank Ltd");
        testClass.selectStock("Reliance Industries Ltd");
        testClass.selectStock("Sun Pharmaceuticals Industries Ltd");
        testClass.selectStock("Tata Steel Ltd");
        
        testClass.closeSetup();
    }
}
