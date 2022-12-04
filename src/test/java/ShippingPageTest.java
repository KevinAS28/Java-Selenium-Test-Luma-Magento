import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShippingPageTest {
    public static WebDriver driver;
    public static ShippingPage shippingPage;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        shippingPage = new ShippingPage(driver);
    }

    @Test
    @Order(1)
    void testGoToPage() {
        shippingPage.goToPage();
        assertEquals(shippingPage.SHIPPING_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @Order(2)
    void testShippingAddress() {
        shippingPage.shippingAddress();
        Util.waitElement(driver, new By.ByXPath("//div[text()[contains(.,'Payment Method')]]"));
        assertEquals("https://magento.softwaretestingboard.com/checkout/#payment", driver.getCurrentUrl());
    }

//    @Test
//    @Order(3)
//    void testNextButton(){
//        shippingPage.nextButton();
//        Util.waitElement(driver, new By.ByClassName("payment-method-title"));
//        assertEquals("https://magento.softwaretestingboard.com/checkout/#payment", driver.getCurrentUrl());
//    }
}