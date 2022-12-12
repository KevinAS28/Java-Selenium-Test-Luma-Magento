import org.apache.log4j.chainsaw.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
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
    public static List<String> productsInCart = new ArrayList<>();


    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @Test
    @Order(1)
    void testGoToPage(){
        homePage.goToPage();
        assertEquals(homePage.HOME_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @Order(2)
    void testSearchItems() {

        HomePageTest.productToCarts = homePage.searchItems("Bag");
        assertNotEquals(0, productToCarts.size());
    }

    @Test
    @Order(3)
    void testAddToCarts(){
        int i = 0;
        while (i < 4){
            WebElement productElement = HomePageTest.productToCarts.get(i);
            homePage.driver.findElement(new By.ByXPath("//div[@class='search results']")).click();
            String productName = homePage.addToCart(productElement);
            if (HomePageTest.productsInCart.contains(productName)){
                System.out.println("Product exist: " + productName);
                continue;
            }
            HomePageTest.productsInCart.add(productName);
            System.out.println("testAddToCarts: product "  + productName + " | " +  i + " | " + productElement.toString());
            String message = homePage.getPageMessage();
            System.out.println("page message: " + message);
            boolean match = Pattern.compile("You added(.*)shopping cart(.*)", Pattern.CASE_INSENSITIVE).matcher(message).find();
            assertTrue(match);
            i++;
//            WebElement pageHeaderElement = homePage.driver.findElement(new By.ByClassName("page-header"));
//            Actions builder = new Actions(homePage.driver);
//            builder.moveToElement(pageHeaderElement).perform();
//            try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);}
//            "search results"
        }
        System.out.println("Products in cart: " + productsInCart);
        CartPage.wantedProductNames = productsInCart.subList(0, 2);
        try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);}
    }

    @Test
    @Order(4)
    void testAddSizeAndColor(){
        homePage.addSizeAndColor();
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(new By.ByClassName("messages")));
        String message = homePage.getPageMessage();
        boolean match = Pattern.compile("You added(.*)shopping cart(.*)", Pattern.CASE_INSENSITIVE).matcher(message).find();
        assertTrue(match);
    }

    @Test
    @Order(5)
    void testGoToCarts(){
        homePage.goToCart();
        assertEquals("https://magento.softwaretestingboard.com/checkout/cart/", driver.getCurrentUrl());
    }

//    @Test
//    @Order(5)
//    void testProceedToCheckOut(){
//        homePage.proceedToCheckOut();
//    }

//    @Test
//    @Order(6)
//    void go(){
//
//    }


//    @Test
//    @Order(7)
//    void  testShippingMethod(){
//        homePage.shippingMethod();
//    }
}