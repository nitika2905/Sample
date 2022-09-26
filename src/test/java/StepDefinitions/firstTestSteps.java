package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import setup.driverBase;


public class firstTestSteps extends driverBase {

    int initialAllCount = 0;
    int newAllCount = 0;
    HomePage homePage;

    @Before
    public void launch(){
        setupChromeDriver();
    }
    @Given("User is on TODO page")
    public void the_user_is_on_todo_page() {
        System.out.println("User is on TODO page");
        homePage= new HomePage(getDriver());
        homePage.verifyHeading();
    }

    @When("User Add item {string} in the text field")
    public void add_todo_item(String item) {

        initialAllCount = homePage.getAllListCount();
        homePage.addItem(item);
    }

    @Then("check Item should be added in the list")
    public void verify_updated_List() {
        System.out.println("count after adding 1 item: " + homePage.getAllListCount());
        Assert.assertTrue(homePage.getAllListCount() > initialAllCount);
    }

    @When("User add more items in the list")
    public void adding_more_items() {
        homePage.addItem("test");
        homePage.addItem("more items");
        homePage.addItem("some more");
    }

    @Then("check All Items should be added in the list")
    public void verify_updated_list_again() {
        newAllCount = homePage.getAllListCount();
        System.out.println("count after adding all items: " + newAllCount);
    }

    @When("User mark first item as done")
    public void first_Item_Done() {
        homePage.markFirstItemDone();
    }

    @Then("check no. of pending items left")
    public void getPendingItem() {
        int pendingItemCount = homePage.getPendingItems();
        System.out.println("Pending items: " + pendingItemCount);
        Assert.assertEquals(pendingItemCount, newAllCount - 1);
    }

    @When("User selects Active Tab")
    public void tapOnActiveTab() {
        homePage.activeTab();
    }

    @Then("check Active Tab shows only pending items")
    public void verifyActiveTab() {
        homePage.verifyActiveTabShowPendingItemsOnly();
    }

    @When("User select All tab")
    public void tapOnAllTab() {
        homePage.allTab();
    }

    @Then("check All Tab shows all items")
    public void verifyAllTab() {
        homePage.verifyAllTabShowsAllItems();
    }

    @When("User clear completed item from list")
    public void clearCompletedItems() {
        homePage.clearCompletedItem();
    }

    @Then("check list is updated after items are cleared")
    public void verifyList() {
        homePage.verifyItemsLeftAfterClearingCompletedItems();
    }

    @When("User delete an item")
    public void deleteItem() {
        homePage.deleteItem();
    }

    @Then("check deleted items are successfully deleted")
    public void verifyAfterdeletedItem() {
        homePage.verifyItemLeftAfterDeletedItem();
    }

    @After
    public void afterScenario(){
        closeDriver();
    }

}

