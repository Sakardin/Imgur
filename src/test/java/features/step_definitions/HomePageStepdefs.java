package features.step_definitions;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static features.TestRunner.wait;
import static features.TestRunner.wd;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;


public class HomePageStepdefs {

    private String tagName;
    private int tagNumber;
    private List<String> mostViralPost;


//    Scenario: Open Home Page

    @Given("^I open URL$")
    public void iOpenURL() {
        wd.get("http://imgur.com/");
    }

    @Then("^I see Home Page$")
    public void iSeeHomePage() {
        try{
                wd.findElement(By.cssSelector("div.Message.welcome"));
            } catch (NoSuchElementException ex){
                wd.get("http://imgur.com/");
            }
    }

//    Scenario: Tag page open

    @When("^I click on Explore tag  bar$")
    public void iClickOnExploreTagBar() {
        tagName = wd.findElement(By.cssSelector("div.TrendingTags-container > a:nth-child(1) > div > div.Tag-name")).getText();
        wd.findElement(By.cssSelector("div.TrendingTags-container > a:nth-child(1)")).click();
    }

    @Then("^Right tag page is open$")
    public void rightTagPageIsOpen() throws Throwable {
        Thread.sleep(3000);
        String pageName = wd.findElement(By.cssSelector("div.Cover-metadata > h1")).getText();
        Assert.assertTrue(tagName.equalsIgnoreCase(pageName));
    }
//    Scenario: More Tags are displayed

    @And("^I see tags icons$")
    public void iSeeTagsIcons() {
        tagNumber = wd.findElement(By.className("TrendingTags-container")).findElements(By.className("Tag ")).size();
    }

    @When("^I click \"([^\"]*)\"$")
    public void iClick(String moreTags) {
        wd.findElement(By.linkText(moreTags)).click();
    }

    @Then("^I see more tags icons$")
    public void iSeeMoreTagsIcons() {
        int moreTags = wd.findElement(By.className("TrendingTags-container")).findElements(By.className("Tag ")).size();
        Assert.assertNotEquals(tagNumber, moreTags);
        Assert.assertTrue(tagNumber < moreTags);
        System.out.println("Tag number before " + tagNumber);
        System.out.println("Tag number after " + moreTags);
    }

//    Scenario: Sort order is changed

    @And("^I see \"([^\"]*)\"$")
    public void iSee(String mostViral) {
        WebElement dropmenu = wd
//                .findElement(By.cssSelector("span.NewCover-change-sort-wrapper > div > div.Dropdown-title > span"))
                .findElement(By.xpath("//div[@class='Dropdown-title'][.='"+mostViral+"']"));

//        dropmenu.findElement(By.linkText(mostViral));
        List<WebElement> mostViralList = wd.findElements(By.cssSelector("div.Post-item-title > span"));
        mostViralPost = new ArrayList<>();
        for (WebElement mostViralP : mostViralList) {
            mostViralPost.add(mostViralP.getText());
        }


        dropmenu.click();
//        Assert.assertTrue(t.equalsIgnoreCase(mostViral));
    }

    @When("^I choose \"([^\"]*)\"$")
    public void iChoose(String userSub) {
        wd.findElement(By.xpath("//div[contains(text(),'" + userSub + "')]")).click();
    }

    @Then("^Order of post has changed$")
    public void orderOfPostHasChanged() throws Throwable {

        Thread.sleep(3000);
        List<WebElement> userSublList = wd.findElements(By.cssSelector("div.Post-item-title > span"));
        List<String> userSubPost = new ArrayList<>();
        for (WebElement userSublP : userSublList) {
            userSubPost.add(userSublP.getText());
        }
        Assert.assertNotEquals(userSubPost, mostViralPost);
    }

//    Scenario: Waterfall and uniform page layout

    @And("^Waterfall layout is active$")
    public void waterfallLayoutIsActive() {
        wd.findElement(By.cssSelector("div.Tooltip-dataTitle.waterfall")).click();
        String px = wd.findElement(By.cssSelector("div.FastGrid > div:nth-child(1) > div:nth-child(1)")).getCssValue("height");
        Assert.assertNotEquals(px, "240px");
    }

    @When("^I hover mouse over Unoform icon$")
    public void iHoverMouseOverUnoformIcon() {
        Actions hover = new Actions(wd);
        WebElement iconUniforn = wd.findElement(By.cssSelector("div.Tooltip-dataTitle.uniform"));
        hover.moveToElement(iconUniforn);
        String tooltip = iconUniforn.getAttribute("data-title");
        Assert.assertEquals(tooltip, "Uniform");
    }

    @And("^I click$")
    public void iClick() {
        wd.findElement(By.cssSelector("div.Tooltip-dataTitle.uniform")).click();
    }

    @Then("^I see layout has changed$")
    public void iSeeLayoutHasChanged() {
        String px = wd.findElement(By.cssSelector("div.FastGrid > div:nth-child(1) > div:nth-child(1)")).getCssValue("height");
        Assert.assertEquals(px, "240px");
    }

//    Scenario: I see Footer

    @Then("^I see Footer$")
    public void iSeeFooter() {
        WebElement footer = wd.findElement(By.xpath("//div[@class='Footer Footer-slim']"));
        assert footer.isDisplayed();
    }

//    Scenario: Searching

    @When("^I search \"([^\"]*)\"$")
    public void iSearch(String searchText) throws Throwable {
        WebElement searchBar = wd.findElement(By.cssSelector("div.Searchbar > form > input"));
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(searchText);
        Thread.sleep(3000);
    }

//      TAGS

    @Then("^I see only tags$")
    public void iSeeOnlyTags() {
        List<WebElement> suggestionItems = wd.findElements(By.cssSelector("a.Suggestion-item"));
        List<String> hrefs = new ArrayList<>();
        for (WebElement item : suggestionItems) {
            hrefs.add(item.getAttribute("href"));
        }
        for (String href : hrefs) {
            assert href.contains("/t/");
        }
    }

//      USERS

    @Then("^I see only users$")
    public void iSeeOnlyUsers() {
        List<WebElement> suggestionItems = wd.findElements(By.cssSelector("a.Suggestion-item"));
        List<String> hrefs = new ArrayList<>();
        for (WebElement item : suggestionItems) {
            hrefs.add(item.getAttribute("href"));
        }
        for (String href : hrefs) {
            assert href.contains("user");
        }
    }

//      TAGS and USERS

    @Then("^I see posts, tags and user$")
    public void iSeeTagsAndUser() {
        assert wd.findElement(By.cssSelector("div.Suggestion-block.posts")).isDisplayed();
        assert wd.findElement(By.cssSelector("div.Suggestion-block.tags")).isDisplayed();
        assert wd.findElement(By.cssSelector("div.Suggestion-block.users")).isDisplayed();
    }

//    Scenario: Top Comments page

    @When("^I click on Comments button$")
    public void iClickOnCommentsButton() {
        wd.findElement(By.xpath("//a[@title='Top Comments']")).click();
    }

    @Then("^I see Best Comments page$")
    public void iSeeBestCommentsPage() throws InterruptedException {
        Thread.sleep(3000);
        String bestComments = wd.findElement(By.cssSelector("h1")).getText();
        assert bestComments.equalsIgnoreCase("Best Comments");
    }

//    Scenario: Number on post thumbnail is same as number of images in the post
    private int postNumberOnHP;
    @And("^I see Post with number$")
    public void iSeePostWithNumber() throws Throwable {
//        wd.findElement(By.xpath("//a[@class='Post-item vetovote']/following-sibling::div[@class='Post-item-image-count']"));
        WebElement postNumber = wd.findElement(By.xpath("//div[@class='Post-item-image-count']"));
        postNumberOnHP = Integer.parseInt(postNumber.getText());
        System.out.println(postNumberOnHP);
//        wd.findElement(By.xpath(postNumber)).click();
        postNumber.click();

    }

    @When("^I open post$")
    public void iOpenPost() throws Throwable {
        Thread.sleep(3000);
       WebElement moreImages = wd.findElement(By.xpath("//a[@class='post-loadall btn btn-action']"));
       Actions action = new Actions(wd);
       action.moveToElement(moreImages);
       action.perform();
       moreImages.click();
    }

    @Then("^I see same number of images$")
    public void iSeeSameNumberOfImages() throws Throwable {
        Thread.sleep(3000);
    }

//    Scenario: Take me up - batton is pul HP up

    @When("^I scroll page$")
    public void iScrollPage() throws Throwable {
        JavascriptExecutor jse = (JavascriptExecutor)wd;
        jse.executeScript("window.scrollBy(0,1000)", "");
        Thread.sleep(3000);
    }

    @And("^I click on take me up$")
    public void iClickOnTakeMeUp() throws Throwable {
        wd.findElement(By.cssSelector("div.ButtonBackToTop.active")).click();
        Thread.sleep(3000);
    }

    @Then("^I on top of HP$")
    public void iOnTopOfHP() throws Throwable {
        wd.findElement(By.cssSelector("div.Message.welcome"));
    }

//    Scenario: Header minimaze when I scroll
    @Then("^Header is minimaze$")
    public void headerIsMinimaze() throws Throwable {
        Assert.assertNotNull(wd.findElement(By.cssSelector("div.Message.welcome")));


    }

    @And("^I see big header$")
    public void iSeeBigHeader() throws Throwable {
        wd.findElement(By.cssSelector("div.Message.welcome")).isDisplayed();
    }

//    Scenario:  Autocomplete suggests the search request
    @Then("^I see \"([^\"]*)\" in search suggeasteed string$")
    public void iSeeInSearchSuggeasteedString(String arg0) throws Throwable {
        List<WebElement> suggested = wd.findElements(By.cssSelector("a.class.Suggestion-item"));
        List<String> suggestedText =new ArrayList<>();
        for (WebElement item : suggested) {
//            System.out.println(item.getAttribute("title"));
            suggestedText.add(item.getAttribute("title"));
        }
        for (String item :suggestedText){
//            System.out.println(item);
            Assert.assertThat(item, CoreMatchers.containsString(arg0));
        }
    }



}
