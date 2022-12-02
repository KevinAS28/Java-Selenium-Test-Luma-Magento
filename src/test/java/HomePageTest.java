import org.apache.log4j.chainsaw.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HomePageTest{
    public static WebDriver driver;
    public static HomePage homePage;

    public static List<WebElement> productToCarts;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @Test
    @Order(1)
    void testGoToRegister() {
        homePage.goToPage();
        homePage.goToRegister();
        assertEquals(homePage.REGISTER_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @Order(2)
    void testSearchItems() {
        homePage.goToPage();
        this.productToCarts = homePage.searchItems("Bag");
        assertNotEquals(0, productToCarts.size());
    }

    @Test
    @Order(3)
    void testAddToCarts(){
        homePage.addToCart(this.productToCarts.get(0));
        System.out.println(homePage.getPageMessage());
    }
}