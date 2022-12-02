import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    String HOME_PAGE_URL = "https://magento.softwaretestingboard.com/";
    String REGISTER_PAGE_URL = "https://magento.softwaretestingboard.com/customer/account/create/";

    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get(HOME_PAGE_URL);
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
