import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MagentoLumaTest {
    public static WebDriver driver;

    RegisterPageTest registerPageTest = new RegisterPageTest();
    HomePageTest homePageTest = new HomePageTest();
    CartPageTest cartPageTest = new CartPageTest();
    ShippingPageTest shippingPageTest = new ShippingPageTest();

    WishListPageTest wishListPageTest = new WishListPageTest();

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    @Order(1)
    public void allTest(){
        registerPageTest.driver = driver;
        RegisterPageTest.registerPage = new RegisterPage(driver);
        System.out.println("register go to page");
        registerPageTest.testGoToPage();
        System.out.println("register fill submit");
        registerPageTest.testFillSubmit();
//        registerPageTest.registerPage.login();
        CommonPage.requireLoginEachPage = false;

        homePageTest.driver = driver;
        homePageTest.homePage = new HomePage(driver);
        System.out.println("home go to page");
        homePageTest.testGoToPage();
        System.out.println("home search");
        homePageTest.testSearchItems();
        System.out.println("home add");
        homePageTest.testAddToCarts();
        System.out.println("home add size and colour");
        homePageTest.testAddSizeAndColor();
        System.out.println("home go cart");
        homePageTest.testGoToCarts();

        wishListPageTest.wishListPage = new WishListPage(driver);
        wishListPageTest.driver = driver;
        System.out.println("Wishlist go to page");
        wishListPageTest.testGoToPage();
        System.out.println("Wishlist add");
        wishListPageTest.testAddToWishList();
        System.out.println("Wishlist remove");
        wishListPageTest.testRemoveWishList();

        cartPageTest.cartPage = new CartPage(driver);
        cartPageTest.driver = driver;
        System.out.println("cart go page");
        cartPageTest.testGoToPage();
        System.out.println("cart wanted products");
        cartPageTest.testWantedProductNames();
        System.out.println("cart unwanted products");
        cartPageTest.testRemoveUnwantedProductNames();
        try{Thread.sleep(5000);}catch(InterruptedException e1){System.out.println(e1);}
        System.out.println("cart checkout");
        cartPageTest.testProceedToCheckout();

        shippingPageTest.driver = driver;
        shippingPageTest.shippingPage = new ShippingPage(driver);
        System.out.println("shipping page");
        shippingPageTest.testGoToPage();
        System.out.println("shipping address");
        shippingPageTest.testShippingAddress();
        System.out.println("shipping address appropriate?");
        shippingPageTest.testAddressAppropriate();
        System.out.println("shipping place order button");
        shippingPageTest.testPlaceOrderButton();
        try{Thread.sleep(5000);}catch(InterruptedException e1){System.out.println(e1);}
    }
    
}
