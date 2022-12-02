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

    public String addToCart(WebElement productElement0){
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement4 = wait4.until(ExpectedConditions.visibilityOf(productElement0));

        Actions builder = new Actions(driver);
        builder.moveToElement(productElement4).perform();

        WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement1 = wait0.until(ExpectedConditions.visibilityOf(productElement0));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement = wait2.until(ExpectedConditions.elementToBeClickable(productElement1));


        try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);}

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

    public void goToCart(){
        WebElement Cart = driver.findElement(new By.ByXPath("//a[@class='action showcart']"));
        try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}
        Actions action = new Actions(driver);
        action.click(Cart).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement cartHrefElement = wait.until(
                ExpectedConditions.elementToBeClickable(new By.ByXPath("//a[@class='action viewcart']")));
        driver.get(cartHrefElement.getAttribute("href"));
    }

    public void proceedToCheckOut(){
        WebElement checkOut = driver.findElement(new By.ById("top-cart-btn-checkout"));
        checkOut.click();
    }

    public void shippingAddress(){
        String company = "company", addressId1 = "street[0]", addressId2 = "street[1]", addressId3 = "street[2]", city = "city", state = "region_id", postalCode = "postcode", country = "country_id", phoneNumber = "telephone";
        String companyValue = Util.generateRandomString(10, false, true,false);
        String Addr1Value = "1!aA" + Util.generateRandomString(20);
        String Addr2Value = "1!aA" + Util.generateRandomString(20);
        String Addr3Value = "1!aA" + Util.generateRandomString(20);
        String cityValue = Util.generateRandomString(10, false, true,false);
        String stateValue = Util.generateRandomString(10, false, true,false);
        String postalCodeValue = Util.generateRandomString(5, false, true,false);
        String countryValue = Util.generateRandomString(10, false, true,false);
        String phoneNumberValue = Util.generateRandomString(12, true, false,false);
        System.out.println("Values: " + companyValue + " | " + Addr1Value + " | " + Addr2Value + " | " + Addr3Value + " | " + cityValue + " | " + stateValue + " | " + postalCodeValue + " | " + countryValue + " | " + phoneNumberValue);

        try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);}
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement shipAddWait = wait.until(ExpectedConditions.elementToBeClickable(new By.ByName("company")));
        System.out.println(shipAddWait.getText());

        WebElement companyElement = driver.findElement(new By.ByName(company));
        WebElement addressId1Element = driver.findElement(new By.ByName(addressId1));
        WebElement addressId2Element = driver.findElement(new By.ByName(addressId2));
        WebElement addressId3Element = driver.findElement(new By.ByName(addressId3));
        WebElement cityElement = driver.findElement(new By.ByName(city));
        WebElement stateElement = driver.findElement(new By.ByName(state));
        WebElement postalCodeElement = driver.findElement(new By.ByName(postalCode));
        WebElement countryElement = driver.findElement(new By.ByName(country));
        WebElement phoneNumberElement = driver.findElement(new By.ByName(phoneNumber));


        companyElement.sendKeys(companyValue);
        addressId1Element.sendKeys(Addr1Value);
        addressId2Element.sendKeys(Addr2Value);
        addressId3Element.sendKeys(Addr3Value);
        cityElement.sendKeys(cityValue);
        stateElement.sendKeys(stateValue);
        postalCodeElement.sendKeys(postalCodeValue);
        countryElement.sendKeys(countryValue);
        phoneNumberElement.sendKeys(phoneNumberValue);
    }

    public void shippingMethod(){
        String shippingMethod1 = "ko_unique_1", shippingMethod2 = "ko_unique_2";
        String shippingMethod1Value = "1";
        String shippingMethod2Value = "2";
        System.out.println("Values: " + shippingMethod1Value + " | " + shippingMethod2Value );

        WebElement shippingMethod1Element = driver.findElement(new By.ByName(shippingMethod1));
        WebElement shippingMethod2Element = driver.findElement(new By.ByName(shippingMethod2));
        WebElement nextButton = driver.findElement(new By.ByXPath("//button[@class = 'button action continue primary']"));

        shippingMethod1Element.sendKeys(shippingMethod1Value);
        shippingMethod2Element.sendKeys(shippingMethod2Value);
        nextButton.click();
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
