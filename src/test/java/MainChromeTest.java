import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainChromeTest {
    // https://groups.google.com/g/webdriver/c/m7ynStLbPM4
    public static WebDriver driver;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        MainChromeTest.driver = new ChromeDriver();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @AfterAll
    public static void finishAllTests(){

    }





}