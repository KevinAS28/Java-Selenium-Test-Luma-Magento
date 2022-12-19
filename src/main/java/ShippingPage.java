import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class ShippingPage extends CommonPage{
    public final String SHIPPING_PAGE_URL = "https://magento.softwaretestingboard.com/checkout/#shipping";

    public static String shipping_address_checkout;
    public static HashMap<String, String> item_list_before_checkout = new HashMap<String, String>();
    public static HashMap<String, String> item_list_order_report = new HashMap<String, String>();
    public static String order_number;

    public ShippingPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        if (CommonPage.requireLoginEachPage){
            login(EMAIL, PASSWORD);
        }
        driver.get(SHIPPING_PAGE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ById("shipping")));
    }



    public void shippingAddress(){
        String company = "company", addressId1 = "street[0]", addressId2 = "street[1]", addressId3 = "street[2]", city = "city", state0 = "region_id", state1 = "region", postalCode = "postcode", country = "country_id", phoneNumber = "telephone";
        String companyValue = Util.generateRandomString(10, false, true,false);
        String Addr1Value = "1!aA Address1 " + Util.generateRandomString(20);
        String Addr2Value = "1!aA Address2 " + Util.generateRandomString(20);
        String Addr3Value = "1!aA Address3 " + Util.generateRandomString(20);
        String cityValue =  "City " + Util.generateRandomString(10, false, true,false);
        String stateValue = "State " +Util.generateRandomString(10, false, true,false);
        String postalCodeValue = Util.generateRandomString(5, true, false,false);
        String countryValue = "Country " + Util.generateRandomString(10, false, true,false);
        String phoneNumberValue = Util.generateRandomString(12, true, false,false);
        System.out.println("Values: " + companyValue + " | " + Addr1Value + " | " + Addr2Value + " | " + Addr3Value + " | " + cityValue + " | " + stateValue + " | " + postalCodeValue + " | " + countryValue + " | " + phoneNumberValue);
        shipping_address_checkout = Addr1Value = ", "+ Addr2Value + ", " + Addr3Value + ", " + cityValue + ", " + postalCodeValue;


        WebElement companyElement = Util.waitElement(driver, new By.ByName(company));
        companyElement.sendKeys(companyValue);

        WebElement addressId1Element = Util.waitElement(driver, new By.ByName(addressId1));
        addressId1Element.sendKeys(Addr1Value);

        WebElement addressId2Element = Util.waitElement(driver, new By.ByName(addressId2));
        addressId2Element.sendKeys(Addr2Value);

        WebElement addressId3Element = Util.waitElement(driver, new By.ByName(addressId3));
        addressId3Element.sendKeys(Addr3Value);

        WebElement cityElement = Util.waitElement(driver, new By.ByName(city));
        cityElement.sendKeys(cityValue);

        WebElement stateElement = Util.waitElement(driver, new By.ByName(state0));
        stateElement.click();
        Select stateSelectElement = new Select(stateElement);
        stateSelectElement.selectByIndex(4);
//        stateElement.click();
//        stateSelectElement.getOptions().get(4).click();
//        System.out.println("state");
//        try{
//
//        } catch (Exception e){
//            WebElement stateElement = Util.waitElement(driver, new By.ByName(state1));
//            stateElement.click();
//            stateElement.sendKeys(stateValue);
//            System.out.println("state");
//
//        }

//        try{Thread.sleep(5000);}catch(InterruptedException e1){System.out.println(e1);}
        WebElement postalCodeElement = Util.waitElement(driver, new By.ByName(postalCode));
        postalCodeElement.sendKeys(postalCodeValue);

        WebElement countryElement = Util.waitElement(driver, new By.ByName(country));
//        countryElement.sendKeys(countryValue);
        Select countrySelectElement = new Select(countryElement);
        countrySelectElement.selectByIndex(4);

        WebElement phoneNumberElement = Util.waitElement(driver, new By.ByName(phoneNumber));
        phoneNumberElement.sendKeys(phoneNumberValue);
        nextButton();
    }

    public void getOrderItem(){
        WebElement ol = driver.findElement(new By.ByXPath("//*[@id=\"opc-sidebar\"]/div[1]/div/div[2]/div/ol"));
        List<WebElement> li = ol.findElements(new By.ByTagName("li"));
        WebElement dropdownList = driver.findElement(new By.ByXPath("//*[@id=\"opc-sidebar\"]/div[1]/div/div[1]"));
        dropdownList.click();
        Integer i = 1;
        for (WebElement row : li) {
            WebElement wait1 = Util.waitElement(driver, new By.ByXPath("//*[@id=\"opc-sidebar\"]/div[1]/div/div[2]/div/ol/li["+i+"]/div/div/div/div[1]/strong"));
            WebElement itemName = row.findElement(new By.ByXPath("//*[@id=\"opc-sidebar\"]/div[1]/div/div[2]/div/ol/li["+i+"]/div/div/div/div[1]/strong"));
            WebElement itemPrice = row.findElement(new By.ByXPath("//*[@id=\"opc-sidebar\"]/div[1]/div/div[2]/div/ol/li["+i+"]/div/div/div/div[2]"));
            String productName = itemName.getText();
            String productPrice = itemPrice.getText();
            item_list_before_checkout.put(productName, productPrice);
            i++;
        }
    }

    public void nextButton(){
        try{Thread.sleep(5000);}catch(InterruptedException e1){System.out.println(e1);}
        WebElement nextButtonElement1 = Util.waitElement(driver, new By.ById("shipping-method-buttons-container"));
        nextButtonElement1.click();
        try{
            WebElement nextButtonElement0 = Util.waitElement(driver, new By.ByXPath(String.format("//button[@class='button action continue primary']")));
            nextButtonElement0.click();
        } catch (Exception e){}
    }

    public void placeOrderButton(){
        try{
            WebElement nextButtonElement0 = Util.waitElement(driver, new By.ByXPath(String.format("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button")));
            nextButtonElement0.click();

            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait2.until(ExpectedConditions.visibilityOfElementLocated(new By.ByClassName("checkout-success")));
        } catch (Exception e){}
    }

    public void getOrderNumber(){
        try{
            WebElement orderNumberElement = Util.waitElement(driver, new By.ByXPath(String.format("//*[@id=\"maincontent\"]/div[3]/div/div[2]/p[1]/a/strong")));
            WebElement orderLinkElement = Util.waitElement(driver, new By.ByXPath(String.format("//*[@id=\"maincontent\"]/div[3]/div/div[2]/p[1]/a")));
            String orderNumber = orderNumberElement.getText();
            String orderLink = orderLinkElement.getAttribute("href");
            order_number = orderNumber;
            driver.get(orderLink);
            WebElement table = driver.findElement(new By.ByXPath("//*[@id=\"my-orders-table\"]"));
            List<WebElement> allProductElements = table.findElements(new By.ByTagName("tbody"));
//            get element each row
            for (WebElement row : allProductElements) {
                WebElement tr = row.findElement(new By.ByTagName("tr"));
                List<WebElement> td = tr.findElements(new By.ByTagName("td"));
                WebElement productNameElement = td.get(0).findElement(new By.ByTagName("strong"));
                WebElement productPriceElement = td.get(4).findElement(new By.ByClassName("price"));
                String productName = productNameElement.getText();
                String productPrice = productPriceElement.getText();
                System.out.println("----"+productName+"----"+productPrice);
                item_list_order_report.put(productName, productPrice);
            }

            System.out.println("NUMBER OF ROWS IN THIS TABLE = "+allProductElements.size());
        } catch (Exception e){}
    }

}
