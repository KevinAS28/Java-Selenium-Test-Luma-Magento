import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainChromeTest {
    // https://groups.google.com/g/webdriver/c/m7ynStLbPM4
    public static WebDriver driver;

    public String EMAIL = "1!aACawIYYHj@gmail.com", PASSWORD = "1!aAYPOQqcqe";

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

//    @Test
//    public void loginTest(){
//        login();
//        assertEquals("https://magento.softwaretestingboard.com/customer/account/", driver.getCurrentUrl());
//    }


}