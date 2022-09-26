package setup;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class driverSetup {

    static Logger log = LoggerFactory.getLogger(driverSetup.class);
    public static WebDriver driver = null;

    public static WebDriver openChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://todomvc.com/examples/vue/");
        driver.manage().window().maximize();
        return driver;
    }

    public static void tearDown(){
        log.debug("Closing the driver instance");
        driver.close();
    }


}
