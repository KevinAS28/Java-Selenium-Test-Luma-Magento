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

    public List<String> generateAsciiCharsNumbers(boolean numbers, boolean lowercase, boolean uppercase){
        List<String> ascii = new ArrayList<>();
        char c;
        if (lowercase){
            for (c = 'a'; c <= 'z'; c++)
            {
                ascii.add(String.valueOf(c));
            }
        }

        if (uppercase){
            for (c = 'A'; c <= 'Z'; c++)
            {
                ascii.add(String.valueOf(c));
            }
        }

        if (numbers){
            for (int i = 0; i <= 9; i++)
            {
                ascii.add(String.valueOf(i));
            }

        }

        return ascii;
    }
    public String generateRandomString(int length, boolean numbers, boolean lowercase, boolean uppercase){
        String randomChars = "";
        java.util.Random random = new java.util.Random();
        List<String> asciiChars = generateAsciiCharsNumbers(true, true, true);
        for (int i = 0; i < length; i++){
            int random_num = random.nextInt(asciiChars.size());
            randomChars += asciiChars.get(random_num);
        }
        return randomChars;
    }

    public String generateRandomString(int length){
        return generateRandomString(length, true, true, true);
    }

    public void fillSubmit(){
        String firstNameId="firstname", lastNameId="lastname", emailId="email_address", passwordId="password", confirmPasswordId="password-confirmation";
        String firstNameValue = generateRandomString(8, false, true, false);
        String lastNameValue = generateRandomString(8, false, true, false);
        String emailAddrValue = "1!aA" + generateRandomString(8) + "@gmail.com";
        String passwordValue = "1!aA" + generateRandomString(8);
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
    }

}
