import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartPageTest {
    public static WebDriver driver;
    public static CartPage cartPage;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        cartPage = new CartPage(driver);
    }

    @Test
    @Order(1)
    void testGoToPage() {
        cartPage.goToPage();
        assertEquals(cartPage.CART_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @Order(2)
    void testWantedProductNames(){
        List<WebElement> productElementsInCart = cartPage.getProductElementsInCart();
        CartPage.productElementsInChart = productElementsInCart;

        List<String> productNamesInCart = new ArrayList<>();
        for (WebElement productElement: productElementsInCart){
            productNamesInCart.add(CartPage.getProductName(productElement));

        }
        System.out.println("products in cart: " + productNamesInCart);
        System.out.println("wanted product names: " + cartPage.wantedProductNames);

        for (String wantedProductName : cartPage.wantedProductNames){
            System.out.println(String.format("product %s in the cart?", wantedProductName));
            assertTrue(productNamesInCart.contains(wantedProductName.strip().replaceAll("[^A-Z|^a-z|\\ ]", "")));
        }
    }

    @Test
    @Order(3)
    public void testRemoveUnwantedProductNames(){
        for (WebElement productElement: CartPage.productElementsInChart){
            String productName;
            try{
                productName = CartPage.getProductName(productElement);
            } catch (Exception e){
                continue;
            }

            if (CartPage.unWantedProductNames.contains(productName)){
                System.out.println("remove product: " + productName);
                cartPage.removeProductElement(productElement);
            }
        }
    }

    @Test
    @Order(4)
    void testProceedToCheckout(){
        cartPage.proceedToCheckOut();
        assertTrue(driver.getCurrentUrl().startsWith("https://magento.softwaretestingboard.com/checkout"));
    }


}