import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WishListPageTest {
    public static WebDriver driver;
    public static WishListPage wishListPage;
    public static List<WebElement> productToWishList;
    public static List<String> productsInWishList = new ArrayList<>();

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wishListPage = new WishListPage(driver);
    }

    @Test
    @Order(1)
    void testGoToPage() {
        wishListPage.goToPage();
        assertEquals(wishListPage.ITEMS_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @Order(2)
    void testAddToWishList() {
        WebElement olItem = driver.findElement(new By.ByXPath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol"));
        productToWishList = olItem.findElements(By.tagName("li"));
            WebElement productElement = WishListPageTest.productToWishList.get(0);
        WebElement productLink = productElement.findElement(new By.ByClassName("product-item-link"));
        System.out.println(productLink.getText());
            wishListPage.addToWishList(productElement);

            String message = wishListPage.getPageMessage("addWishList");
            System.out.println("page message: " + message);

            boolean match = Pattern.compile("(.*)has been added to your Wish List(.*)", Pattern.CASE_INSENSITIVE).matcher(message).find();
            assertTrue(match);
    }

    @Test
    @Order(3)
    void testRemoveWishList(){
        WebElement olItem = driver.findElement(new By.ByClassName("product-items"));
        List<WebElement> productInWishList = olItem.findElements(By.tagName("li"));
        WebElement ProductWishListElement = productInWishList.get(0);
        WebElement productLink = ProductWishListElement.findElement(new By.ByClassName("product-item-link"));
        System.out.println(productLink.getText());

        wishListPage.removeWishList(ProductWishListElement);
        String message = wishListPage.getPageMessage("removeWishList");
        System.out.println("page message: " + message);

        boolean match = Pattern.compile("(.*)has been removed from your Wish List.", Pattern.CASE_INSENSITIVE).matcher(message).find();
        assertTrue(match);
    }

}