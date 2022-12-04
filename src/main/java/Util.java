import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<String> generateAsciiCharsNumbers(boolean numbers, boolean lowercase, boolean uppercase){
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
    public static String generateRandomString(int length, boolean numbers, boolean lowercase, boolean uppercase){
        String randomChars = "";
        java.util.Random random = new java.util.Random();
        List<String> asciiChars = generateAsciiCharsNumbers(numbers, lowercase, uppercase);
        for (int i = 0; i < length; i++){
            int random_num = random.nextInt(asciiChars.size());
            randomChars += asciiChars.get(random_num);
        }
        return randomChars;
    }

    public static String generateRandomString(int length){
        return generateRandomString(length, true, true, true);
    }

    public static WebElement waitElement(WebDriver driver, By elementBy){
        WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(5));
        return waitDriver.until(ExpectedConditions.elementToBeClickable(elementBy));
    }

    public static WebElement waitElement(WebDriver driver, WebElement element){
        WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(5));
        return waitDriver.until(ExpectedConditions.elementToBeClickable(element));
    }

}
