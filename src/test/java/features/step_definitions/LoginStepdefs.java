package features.step_definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static features.RunnerTest.wd;

public class LoginStepdefs {
    @Given("^I on Home Page$")
    public void iOnHomePage() throws Throwable {
        try{
            wd.findElement(By.cssSelector("div.Message.welcome"));
        } catch (NoSuchElementException ex){
            wd.get("http://imgur.com/");
        }

    }

    @When("^I click Sign in$")
    public void iClickSignIn() throws Throwable {
        wd.findElement(By.cssSelector("a.Navbar-signin")).click();
    }

    @Then("^Sign in Page is opened$")
    public void signInPageIsOpened() throws Throwable {
        WebElement text = wd.findElement(By.cssSelector("div.signin-callout.text-center.text-shadow"));
         assert (text.getText().equalsIgnoreCase("Sign In with"));

    }

    @Given("^I on Sign in page$")
    public void iOnSignInPage() throws Throwable {
        wd.get("https://imgur.com/signin");
    }

    @When("^I enter \"([^\"]*)\" in username field$")
    public void iEnterInUsermaneField(String userName) throws Throwable {
        WebElement userField = wd.findElement(By.id("username"));
        userField.click();
        userField.clear();
        userField.sendKeys(userName);

    }

    @And("^I enter \"([^\"]*)\" in password field$")
    public void iEnterInPasswordField(String userPass) throws Throwable {
       WebElement passField = wd.findElement(By.id("password"));
       passField.click();
       passField.clear();
       passField.sendKeys(userPass);
    }

    @And("^Click Sign in button$")
    public void clickSignInButton() throws Throwable {
        wd.findElement(By.name("submit")).click();
    }

    @Then("^I see error message$")
    public void iSeeErrorMessage() throws Throwable {
        assert  wd.findElement(By.cssSelector("p.error")).isDisplayed();


    }

    @And("^I logged in$")
    public void iLoggedIn() throws Throwable {
        assert wd.findElement(By.cssSelector("span.UserAvatar")).isDisplayed();
    }
}
