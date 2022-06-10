import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.project.Rpn;
import org.project.RpnImpl;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RpnTest {


    private static Rpn rpn;

    @BeforeAll
    static public void setRpn() {
        rpn = new RpnImpl();
    }

    @Test
    public void IsNumericShouldBeTrue() {
        //given
        String number = String.valueOf(new Random().nextInt(10));
        //act
        rpn.isNumeric(number);
        //assert
        Assertions.assertTrue(rpn.isNumeric(number));
    }

    //Test With Hamcrest
    @Test
    public void IsNumericShouldBeFalse() {
        //given
        String value = "15g";
        //act
        rpn.isNumeric(value);
        //assert
        assertThat(rpn.isNumeric(value), is(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "/"})
    public void getPriorityShouldReturn2(String strings) {
        int expected = 2;
        //act
        int actual = rpn.getPriority(strings);
        //assert
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"+", "-"})
    public void getPriorityShouldReturn1(String strings) {
        int expected = 1;
        //act
        int actual = rpn.getPriority(strings);
        //assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Description("Для постфиксной нотации характерно что 1й элемент это число")
    public void toRpn1stValueShouldBeNumeric() {
        //give
        String[] input = ("3 + 2 * 6").split(" ");
        //act
        List<String> actualList = rpn.toRpn(input);
        //assert
        Assertions.assertTrue(rpn.isNumeric(actualList.get(0)));

    }

    //Test With Hamcrest
    @Test
    @Description("Возвращаемый список должен содержать все элеенты входящего")
    public void toRpnShouldReturnSameValuesAsHave() {
        //give
        String[] input = ("3 + 2 * 6").split(" ");
        //act
        List<String> actualList = rpn.toRpn(input);
        //assert
        assertThat(actualList, hasItems("3", "+", "2", "*", "6"));

    }

}
