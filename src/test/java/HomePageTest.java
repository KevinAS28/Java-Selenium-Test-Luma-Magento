import org.apache.log4j.chainsaw.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        for (int i = 0; i<4;i++){
            System.out.println("testAddToCarts: product "  + i);
            WebElement productElement = this.productToCarts.get(i);
            homePage.driver.findElement(new By.ByXPath("//div[@class='search results']")).click();
            homePage.addToCart(productElement);
            String message = homePage.getPageMessage();
            System.out.println("page message: " + message);
            boolean match = Pattern.compile("You added(.*)shopping cart(.*)", Pattern.CASE_INSENSITIVE).matcher(message).find();
            assertTrue(match);
//            WebElement pageHeaderElement = homePage.driver.findElement(new By.ByClassName("page-header"));
//            Actions builder = new Actions(homePage.driver);
//            builder.moveToElement(pageHeaderElement).perform();
//            try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);}
//            "search results"
        }

    }

    @Test
    @Order(4)
    void testGoToCarts(){
        homePage.goToCart();
    }

    @Test
    @Order(5)
    void testProceedToCheckOut(){
        homePage.proceedToCheckOut();
    }
}