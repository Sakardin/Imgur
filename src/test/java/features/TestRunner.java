package features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue= "features.step_definitions",
        format = {"pretty"},
        tags = "@wip"

)
public class TestRunner {
    public static WebDriver wd;
    public static WebDriverWait wait;


    @BeforeClass
    public static void start() {
        wd = new ChromeDriver();

//        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(wd, 10);
        wd.get("http://imgur.com/");
    }


    @AfterClass
    public static void stop(){
        wd.quit();
        wd = null;
    }



}
