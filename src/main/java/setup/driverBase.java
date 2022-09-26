package setup;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class driverBase {
    static Logger log = LoggerFactory.getLogger(driverBase.class);
    public static WebDriver driver = null;

    public void setupChromeDriver(){
        try{
            driver = driverSetup.openChromeDriver();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public WebDriver getDriver(){
        return driver;
    }


    public void closeDriver() {
        driverSetup.tearDown();
    }

}
