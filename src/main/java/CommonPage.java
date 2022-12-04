import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonPage {
    public static String EMAIL = "1!aAXaOch1W5@gmail.com", PASSWORD = "1!aA4ONfjJYU";
    public WebDriver driver;
    public static boolean requireLoginEachPage = true;

    public CommonPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String email, String password){
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        String emailId="email", passwordId="pass";
        WebElement emailElement = driver.findElement(new By.ById(emailId));
        WebElement passwordElement = driver.findElement(new By.ById(passwordId));
        WebElement buttonElement = driver.findElement(new By.ById("send2"));
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);
        buttonElement.click();
    }

    public void login(){
        login(EMAIL, PASSWORD);
    }

}
