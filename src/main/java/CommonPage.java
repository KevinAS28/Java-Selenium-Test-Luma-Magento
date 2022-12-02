import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonPage {
    public String EMAIL = "1!aACawIYYHj@gmail.com", PASSWORD = "1!aAYPOQqcqe";
    public WebDriver driver;

    public CommonPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(){
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        String emailId="email", passwordId="pass";
        WebElement emailElement = driver.findElement(new By.ById(emailId));
        WebElement passwordElement = driver.findElement(new By.ById(passwordId));
        WebElement buttonElement = driver.findElement(new By.ById("send2"));
        emailElement.sendKeys(EMAIL);
        passwordElement.sendKeys(PASSWORD);
        buttonElement.click();
    }

}
