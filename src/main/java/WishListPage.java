import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WishListPage extends CommonPage {

    public final String ITEMS_PAGE_URL = "https://magento.softwaretestingboard.com/men/tops-men/jackets-men.html";

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        driver.get(ITEMS_PAGE_URL);
        if (CommonPage.requireLoginEachPage){
            login(EMAIL, PASSWORD);
        }
        driver.get(ITEMS_PAGE_URL);
    }

    public void addToWishList(WebElement productElement0) {
//        List<String> listData = new ArrayList<>();
//        WebElement olItem = driver.findElement(new By.ByXPath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol"));
//        List<WebElement> listLi = olItem.findElements(By.tagName("li"));
//        List<WebElement> listSize = listLi.get(0).findElements(By.className("swatch-option"));
//        listSize.get(0).click();
//        System.out.println(listSize.get(0).getText());

        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement4 = wait4.until(ExpectedConditions.visibilityOf(productElement0));

        Actions builder = new Actions(driver);
        builder.moveToElement(productElement4).perform();

        WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement1 = wait0.until(ExpectedConditions.visibilityOf(productElement0));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement = wait2.until(ExpectedConditions.elementToBeClickable(productElement1));


        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addWishListElement1 = wait1.until(ExpectedConditions.visibilityOf(productElement.findElement(By.className("towishlist"))));
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addWishListElement = wait3.until(ExpectedConditions.elementToBeClickable(addWishListElement1));

//        WebElement productLink = productElement.findElement(new By.ByClassName("product-item-link"));


//        Actions actions = new Actions(driver);
//        actions.moveToElement(addWishListElement).click().build().perform();
//        clickWishListElement(addWishListElement);

//        System.out.println(productLink.getText());

//        return productLink.getAttribute("innerHTML");
//        return "";
        Actions actions = new Actions(driver);
        actions.moveToElement(addWishListElement).click().build().perform();
//        return new WishListDetailPage(driver);
    }

    public void removeWishList(WebElement productElement0){
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement4 = wait4.until(ExpectedConditions.visibilityOf(productElement0));

        Actions builder = new Actions(driver);
        builder.moveToElement(productElement4).perform();

        WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement1 = wait0.until(ExpectedConditions.visibilityOf(productElement0));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement = wait2.until(ExpectedConditions.elementToBeClickable(productElement1));


        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement removeWishListElement1 = wait1.until(ExpectedConditions.visibilityOf(productElement.findElement(By.className("btn-remove"))));
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement removeWishListElement = wait3.until(ExpectedConditions.elementToBeClickable(removeWishListElement1));

        Actions actions = new Actions(driver);
        actions.moveToElement(removeWishListElement).click().build().perform();
    }


    public String getPageMessage(String type){
        WebElement pageMessageLink;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        if(type.equals("addWishList")){
             pageMessageLink = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//div[text()[contains(.,'added to your Wish List')]]")));
        } else{
             pageMessageLink = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//div[text()[contains(.,'removed from your Wish List')]]")));
        }

        return pageMessageLink.findElement(new By.ByXPath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getAttribute("innerHTML");
    }


}
