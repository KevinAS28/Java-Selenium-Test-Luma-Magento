import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends CommonPage{
    WebDriver driver;
    String REGISTER_PAGE_URL = "https://magento.softwaretestingboard.com/customer/account/create/";

    public RegisterPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void goToPage(){
        driver.get(REGISTER_PAGE_URL);
    }


    public String fillSubmit(){
        String firstNameId="firstname", lastNameId="lastname", emailId="email_address", passwordId="password", confirmPasswordId="password-confirmation";
        String firstNameValue = Util.generateRandomString(8, false, true, false);
        String lastNameValue = Util.generateRandomString(8, false, true, false);
        String emailAddrValue = "1!aA" + Util.generateRandomString(8) + "@gmail.com";
        String passwordValue = "1!aA" + Util.generateRandomString(8);
        System.out.println("Values: " + firstNameValue + " | " + lastNameValue + " | " + emailAddrValue + " | " + passwordValue);

        WebElement firstNameElement = driver.findElement(new By.ById(firstNameId));
        WebElement lastNameElement = driver.findElement(new By.ById(lastNameId));
        WebElement emailElement = driver.findElement(new By.ById(emailId));
        WebElement passwordElement = driver.findElement(new By.ById(passwordId));
        WebElement confirmPasswordElement = driver.findElement(new By.ById(confirmPasswordId));
        WebElement submitButton = driver.findElement(new By.ByXPath("//button[contains(@title,'Create an Account')]"));

        firstNameElement.sendKeys(firstNameValue);
        lastNameElement.sendKeys(lastNameValue);
        emailElement.sendKeys(emailAddrValue);
        passwordElement.sendKeys(passwordValue);
        confirmPasswordElement.sendKeys(passwordValue);
        submitButton.click();

        return emailAddrValue + "|" + passwordValue;
    }

}
