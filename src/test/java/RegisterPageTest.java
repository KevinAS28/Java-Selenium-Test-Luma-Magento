import org.apache.log4j.chainsaw.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegisterPageTest{
    public static WebDriver driver;
    public static RegisterPage registerPage;



    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        registerPage = new RegisterPage(driver);
    }

    @BeforeEach
    public void beforeEach(){
        try{Thread.sleep(2000);}catch(InterruptedException e){System.out.println(e);}
    }

    @Test
    @Order(1)
    void testGoToPage() {
        System.out.println("testGoToPage(): " + driver.getCurrentUrl());
        registerPage.goToPage();
//        assertTrue(registerPage.REGISTER_PAGE_URL.startsWith(driver.getCurrentUrl()));
        assertEquals(registerPage.REGISTER_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @Order(2)
    public void testFillSubmit(){
        System.out.println("testFillSubmit(): " + driver.getCurrentUrl());
        String email_password_string = registerPage.fillSubmit();
        String[] email_password = email_password_string.split("|");
        CommonPage.EMAIL = email_password[0];
        CommonPage.PASSWORD = email_password[1];
        assertEquals("https://magento.softwaretestingboard.com/customer/account/", driver.getCurrentUrl());
    }

}