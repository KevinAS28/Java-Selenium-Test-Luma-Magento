import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void generateAsciiCharsNumbers() {
        System.out.println("generateAsciiCharsNumbers" + Util.generateAsciiCharsNumbers(true, false, false));
    }

    @Test
    void generateRandomString() {
        System.out.println("generateRandomString " + Util.generateRandomString(5, true, false, false));

    }

}