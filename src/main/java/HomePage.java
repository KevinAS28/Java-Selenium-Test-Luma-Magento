import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends CommonPage {

    public final String HOME_PAGE_URL = "https://magento.softwaretestingboard.com/";

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void goToPage(){
        driver.get(HOME_PAGE_URL);
        if (CommonPage.requireLoginEachPage){
            login(EMAIL, PASSWORD);
        }
        driver.get(HOME_PAGE_URL);
        System.out.println("home search");
    }

    public List<WebElement> searchItems(String keyword){
        WebElement searchElement = driver.findElement(new By.ById("search"));
        searchElement.sendKeys(keyword);
        searchElement.sendKeys(Keys.ENTER);
        WebElement productListContainer = driver.findElement(new By.ByXPath("//ol[@class='products list items product-items']"));
        List<WebElement> allProductElements = productListContainer.findElements(new By.ByXPath("//li[@class='item product product-item']"));
        return allProductElements;
    }

    public String addToCart(WebElement productElement0){
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement4 = wait4.until(ExpectedConditions.visibilityOf(productElement0));

        Actions builder = new Actions(driver);
        builder.moveToElement(productElement4).perform();

        WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement1 = wait0.until(ExpectedConditions.visibilityOf(productElement0));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement = wait2.until(ExpectedConditions.elementToBeClickable(productElement1));


//        try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);}

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addCartElement1 = wait1.until(ExpectedConditions.visibilityOf(productElement.findElement(By.tagName("button"))));
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addCartElement = wait3.until(ExpectedConditions.elementToBeClickable(addCartElement1));

        WebElement productLink = productElement.findElement(new By.ByClassName("product-item-link"));


        Actions actions = new Actions(driver);
        actions.moveToElement(addCartElement).click().build().perform();

//        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement addCartImageElement = wait1.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(addCartElement, new By.ByClassName("product-image-photo"))).get(0);
//        System.out.println("image element alt: " + addCartImageElement.getAttribute("alt"));
//        addCartElement.click();

        return productLink.getAttribute("innerHTML");
    }

    public void addToCartFromMenuNavbar(){
        driver.get("https://magento.softwaretestingboard.com/");
        Actions actions1 = new Actions(driver);
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menuGear = wait4.until(ExpectedConditions.visibilityOf(driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div[2]/nav/ul/li[4]"))));
        actions1.moveToElement(menuGear).perform();
        actions1.moveToElement(driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div[2]/nav/ul/li[4]/ul/li[1]"))).click().build().perform();

        WebElement waitOlItem = wait4.until(ExpectedConditions.visibilityOf(driver.findElement(new By.ByXPath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol"))));
        WebElement olItem = driver.findElement(new By.ByXPath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol"));
        List<WebElement> listLi = olItem.findElements(By.tagName("li"));

        actions1.moveToElement(listLi.get(0)).perform();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addCartElement1 = wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("tocart"))));
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addCartElement = wait3.until(ExpectedConditions.elementToBeClickable(addCartElement1));

        Actions actions = new Actions(driver);
        actions.moveToElement(addCartElement).click().build().perform();
    }

    public void addSizeAndColor(){
        driver.get("https://magento.softwaretestingboard.com/men/tops-men/jackets-men.html");
        List<String> listData = new ArrayList<>();
        WebElement olItem = driver.findElement(new By.ByXPath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol"));
        List<WebElement> listLi = olItem.findElements(By.tagName("li"));
        List<WebElement> listSize = listLi.get(0).findElements(By.className("swatch-option"));
        listSize.get(0).click();
        List<WebElement> listColor = listLi.get(0).findElements(By.cssSelector(".swatch-option.color"));
        listColor.get(1).click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addCartElement1 = wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("tocart"))));
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addCartElement = wait3.until(ExpectedConditions.elementToBeClickable(addCartElement1));

        Actions actions = new Actions(driver);
        actions.moveToElement(addCartElement).click().build().perform();
    }

    public void goToCart(){
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Cart = wait1.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//a[@class='action showcart']")));
//        try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}
        Actions action = new Actions(driver);
        action.click(Cart).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement cartHrefElement = wait.until(
                ExpectedConditions.elementToBeClickable(new By.ByXPath("//a[@class='action viewcart']")));
        driver.get(cartHrefElement.getAttribute("href"));
    }


    public void proceedToCheckOut(){
        WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkOutElement0 = wait0.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//button[contains(@data-role,'proceed-to-checkout')]")));
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkOutElement = wait1.until(ExpectedConditions.visibilityOf(checkOutElement0));
        checkOutElement.click();
        System.out.println("URL: " + driver.getCurrentUrl());
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
