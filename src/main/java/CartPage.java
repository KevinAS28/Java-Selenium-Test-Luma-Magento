import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends CommonPage{
    public final String CART_PAGE_URL = "https://magento.softwaretestingboard.com/checkout/cart/";

    public static List<WebElement> productElementsInChart = new ArrayList<>();
    public static List<String> wantedProductNames = new ArrayList<>(){{add("Wayfarer Messenger Bag"); add("Voyage Yoga Bag"); add("Joust Duffle Bag");}};
    public static List<String> unWantedProductNames = new ArrayList<>(){{add("Push It Messenger Bag");}};

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        if (CommonPage.requireLoginEachPage){
            login(EMAIL, PASSWORD);
        }
        driver.get(CART_PAGE_URL);
    }

    public void proceedToCheckOut(){
        WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkOutElement0 = wait0.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//button[contains(@data-role,'proceed-to-checkout')]")));
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkOutElement = wait1.until(ExpectedConditions.visibilityOf(checkOutElement0));
        checkOutElement.click();
        System.out.println("URL: " + driver.getCurrentUrl());

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(new By.ById("shipping")));
    }
    public static String getProductName(WebElement cartItemElement){
        WebElement productCoreElement = cartItemElement.findElement(new By.ByClassName("product-item-name")).findElement(new By.ByTagName("a"));
        return productCoreElement.getAttribute("innerHTML").strip().replaceAll("[^A-Z|^a-z|\\ ]", "");
    }

    public void removeProductElement(WebElement cartItemElement){
        WebElement removeButton = Util.waitElement(driver, cartItemElement.findElement(new By.ByClassName("action-delete")));//new By.ByXPath("//a[contains(@title,'Remove item')]")));
        removeButton.click();
    }
    public List<WebElement> getProductElementsInCart(){
        WebElement cartTableElement = Util.waitElement(driver, new By.ById("shopping-cart-table"));
        Util.waitElement(driver, cartTableElement);
        List<WebElement> cartItemElements = cartTableElement.findElements(new By.ByXPath("//tbody[@class='cart item']"));
        return cartItemElements;

    }

}
