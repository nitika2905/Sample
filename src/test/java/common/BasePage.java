package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class BasePage {
    static Logger log = LoggerFactory.getLogger(BasePage.class);
    public WebDriver driver;

    public static final int TIMEOUT_IN_SECS = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;

    }

    public void waitForElement(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECS);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            log.error("Expected element was not found !!! \n" + e.getMessage());
        }
    }
    public boolean isElementVisibleWithText(By by, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECS);
            wait.until(ExpectedConditions.textToBePresentInElement(element(by), text));
            return true;
        } catch (Exception e) {
            log.error("Expected element was not found !!! \n" + e.getMessage());
            return false;
        }
    }

    public WebElement element(By locator) {
        try {
            return driver.findElement(locator);
        } catch (Exception e) {
            log.error("Expected element was not found !!! \n" + e.getMessage());
            return null;
        }

    }

    public List<WebElement> getListElements(By by) {
        List<WebElement> list = null;
        try {
            list = driver.findElements(by);
        } catch (Exception e) {
            log.error("Expected element was not found !!! \n" + e.getMessage());
        }
        return list;
    }



}
