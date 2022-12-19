import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

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

    @Test
    @Order(3)
    void testAddressAppropriate(){
        WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitDriver.until(ExpectedConditions.invisibilityOfElementLocated(new By.ByClassName("loader")));
        Util.waitElement(driver, driver.findElement(new By.ByXPath("//*[@id=\"opc-sidebar\"]/div[2]/div/div[1]/div[2]")));
        Util.waitElement(driver, new By.ByXPath("//span[text()[contains(.,'Ship To:')]]"));
        String address = driver.findElement(new By.ByXPath("//*[@id=\"opc-sidebar\"]/div[2]/div/div[1]/div[2]")).getAttribute("innerHTML").replaceAll("<!--(.*?)-->","");
        boolean match = Pattern.compile("(.*)"+shippingPage.shipping_address_checkout+"(.*)", Pattern.CASE_INSENSITIVE).matcher(address).find();
        assertTrue(match);
    }

    @Test
    @Order(4)
    void testPlaceOrderButton(){
        shippingPage.getOrderItem();
        System.out.println(shippingPage.item_list_before_checkout);
        shippingPage.placeOrderButton();
        Util.waitElement(driver, new By.ByXPath("//*[@id=\"maincontent\"]/div[1]/h1/span"));
        assertEquals("Thank you for your purchase!", driver.findElement(new By.ByXPath("//*[@id=\"maincontent\"]/div[1]/h1/span")).getText());
    }

    @Test
    @Order(5)
    void testGetOrderNumber(){
        shippingPage.getOrderNumber();
        System.out.println(shippingPage.item_list_order_report);
        assertEquals(shippingPage.item_list_before_checkout, shippingPage.item_list_order_report);
    }

//    @Test
//    @Order(3)
//    void testNextButton(){
//        shippingPage.nextButton();
//        Util.waitElement(driver, new By.ByClassName("payment-method-title"));
//        assertEquals("https://magento.softwaretestingboard.com/checkout/#payment", driver.getCurrentUrl());
//    }
}