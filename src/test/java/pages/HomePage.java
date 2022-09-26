package pages;

import common.BasePage;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.openqa.selenium.Keys;
import setup.driverBase;

import java.util.List;

import static common.CommonUtils.pause;

public class HomePage extends BasePage {
    static Logger log = LoggerFactory.getLogger(HomePage.class);
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public static final By h1 = By.id("//h1");
    public static final By TextField = By.xpath("//input[@class='new-todo']");
    public static final By TODO_Pending_list = By.xpath("//ul[@class='todo-list']/li[@class='todo']");

    public static final By MARK_DONE = By.cssSelector("input.toggle");
    public static final By PENDING_ITEM_COUNT = By.xpath("//span[@class='todo-count']/strong");
    public static final By TODO_list = By.xpath("//ul[@class='todo-list']/li");

    public static final By ACTIVE_TAB = By.xpath("//a[@href='#/active']");
    public static final By All_TAB = By.xpath("//a[@href='#/all']");
    public static final By CLEAR_COMPLETED = By.xpath("//button[@class='clear-completed']");


    int pending_items_count=0;
    int all_items_count =0;

    public void verifyHeading(){
        log.debug("===verify Heading==");
        isElementVisibleWithText(h1, "todos");
    }

    public void addItem(String item){
        log.debug("===verify Adding an Item in the list==");
        element(TextField).click();
        element(TextField).sendKeys(item);
        element(TextField).sendKeys(Keys.RETURN);
    }

    public int getPendingItems(){
        log.debug("===verify Pending items from the list and check the count of pending items==");
        List<WebElement> list = getListElements(TODO_Pending_list);
        String count = element(PENDING_ITEM_COUNT).getText();
        Assert.assertEquals(list.size(), Integer.parseInt(count));
        return Integer.parseInt(count) ;
    }

    public int getAllListCount(){
        log.debug("===Get count of All items in the list==");
        List<WebElement> list = getListElements(TODO_list);
        if(list == null){
            return 0;
        }
        System.out.println("All List count: " + list.size());
        return list.size();

    }

    public void markFirstItemDone() {
        log.debug("===Marking first item from list as completed==");
        List<WebElement> list = getListElements(MARK_DONE);
        if (list.size() > 0) {
            list.get(0).click();
        }
    }

    public void activeTab(){
        log.debug("===Click on Active tab==");
        waitForElement(ACTIVE_TAB);
        element(ACTIVE_TAB).click();
    }

    public void verifyActiveTabShowPendingItemsOnly(){
        log.debug("===Verify Active Tab is showing only pending items==");
        getPendingItems();
    }

    public void allTab(){
        log.debug("===Click on All tab==");
        waitForElement(All_TAB);
        element(All_TAB).click();
    }
    public void verifyAllTabShowsAllItems(){
        log.debug("===Verify Active Tab is showing ALL items==");
        Assert.assertTrue(getAllListCount()>getPendingItems());
    }

    public void clearCompletedItem() {
        log.debug("===Click on ClearComplete button to clear all the items which are completed==");
        int all_items_count = getAllListCount();
        System.out.println("All items: " + all_items_count);
        pending_items_count = getPendingItems();
        System.out.println("Pending items: " + pending_items_count);
        waitForElement(CLEAR_COMPLETED);
        element(CLEAR_COMPLETED).click();
    }
    public void verifyItemsLeftAfterClearingCompletedItems(){
        log.debug("===Verify Completed items are removed==");
        Assert.assertEquals(getPendingItems(), pending_items_count);
        Assert.assertEquals(getAllListCount(),getPendingItems());
    }

    public void deleteItem() {
        log.debug("===Delete any item from list==");
        all_items_count = getAllListCount();
        System.out.println("initial items: " + all_items_count);

        WebElement ele = driver.findElement(By.xpath("//ul[@class='todo-list']/li[1]//button[@class='destroy']"));
        String js = "arguments[0].click()";
        ((JavascriptExecutor) driver).executeScript(js, ele);
        pause(1000);

    }

    public void verifyItemLeftAfterDeletedItem(){
        log.debug("===Verify items are deleted successfully==");
        int final_item = getAllListCount();
        System.out.println("final items: "+   final_item);
        Assert.assertEquals(all_items_count-1,final_item );

    }

}

