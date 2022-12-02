import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends CommonPage {


    String HOME_PAGE_URL = "https://magento.softwaretestingboard.com/";
    String REGISTER_PAGE_URL = "https://magento.softwaretestingboard.com/customer/account/create/";

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void goToPage(){
        driver.get(HOME_PAGE_URL);
    }

    public List<WebElement> searchItems(String keyword){
        WebElement searchElement = driver.findElement(new By.ById("search"));
        searchElement.sendKeys(keyword);
        searchElement.sendKeys(Keys.ENTER);
        WebElement productListContainer = driver.findElement(new By.ByXPath("//ol[@class='products list items product-items']"));
        List<WebElement> allProductElements = productListContainer.findElements(new By.ByXPath("//li[@class='item product product-item']"));
        return allProductElements;
    }

    public void addToCart(WebElement productElement){

        Actions builder = new Actions(driver);
        builder.moveToElement(productElement).perform();

        try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);}
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addCartElement = wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//button[@class='action tocart primary']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(addCartElement).click().build().perform();
//        addCartElement.click();
    }


    public String getPageMessage(){
//        WebElement pageMessage = driver.findElement(new By.ByXPath("//div[@class='page messages' and ./**/]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement pageMessageLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//a[text()[contains(.,'shopping cart')]]")));

        return pageMessageLink.findElement(By.xpath("./..")).getAttribute("innerHTML");
    }

    public void goToRegister(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement hrefElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[text()[contains(.,'Create an Account')]]")));
//        WebElement hrefElement = driver.findElement(new By.ByXPath("//*[text()[contains(.,'Create an Account')]]"));
        String registerUrl = hrefElement.getAttribute("href");
        driver.get(registerUrl);
    }

}
