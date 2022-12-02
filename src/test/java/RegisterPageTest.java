import org.apache.log4j.chainsaw.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class RegisterPageTest{
    public static WebDriver driver;
    public static RegisterPage registerPage;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        registerPage = new RegisterPage(driver);
    }

    @Test
    @Order(2)
    public void testFillSubmit(){
        registerPage.goToPage();
        String email_password_string = registerPage.fillSubmit();
        String[] email_password = email_password_string.split("|");
        HomePageTest.email = email_password[0];
        HomePageTest.password = email_password[1];
        assertEquals("https://magento.softwaretestingboard.com/customer/account/", driver.getCurrentUrl());
    }

}