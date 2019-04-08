package com.gtm.formation.testsIndustriels.exercises;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.annotation.Order;
import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTest {
    Calculator subject = new Calculator();
    private int expectedResult = 0;
    private int expectedInt = 0;
    private String expectedString = "";

    @BeforeEach
    public void setup() {
        expectedResult = 54;
        expectedInt = 123;
        expectedString = "echec - doit etre true";
    }

    @AfterEach
    public void shutDown() {
        expectedResult = 0;
    }

    /**
     * Here we test a condition where right values are set and the method add returns a correct result
     * In the second test, we input wrong values (characters here), the method should throw NumberFormatException
     * @param result
     * @param input1
     * @param input2
     * TODO Parallel execution
     */
    @Tag("slow")
    @Order(2)
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    @DisabledOnOs({OS.LINUX, OS.SOLARIS, OS.OTHER})
    @ParameterizedTest(name = "Case {index}: return {0} for inputs {1} and {2}")
    @CsvSource({"ok,32,22", "exception,tEst,TEST"})
    void sumTest(String result, String input1, String input2) {
        switch (result) {
            case "ok": Assertions.assertEquals(expectedResult, subject.add(input1, input2));
            break;
            case "exception": {
                NumberFormatException exception = Assertions.assertThrows(NumberFormatException.class, ()-> {subject.add(input1, input2);});
                Assertions.assertTrue(exception.getMessage().contains("For input string:"));
            }
            break;
        }
    }

    /*
    objis.com/votre-premiere-classe-de-test-junit/
     */
    @Test
    @Order(1)
    void testAssertTrue(){
        Assertions.assertTrue(true, expectedString);
        Assertions.assertFalse(false, expectedString);
        Assertions.assertEquals(expectedString, "echec - doit etre true");
        Assertions.assertEquals(1.0F, 1.0F, "failure, float numbers are not the same");
        Assertions.assertNotNull(expectedString, "echec - doit etre true");
        assertThat(expectedString).containsWhitespaces();
        assertThat(expectedString).contains("e", "c", "t");
        Assertions.assertNotSame(new Object(), new Object());
        Assertions.assertSame(expectedInt, 123);
        Assertions.assertEquals(expectedInt,123);
    }

    @Disabled
    @Test
    void testFailedAsserts() {
        Assertions.assertTrue(0>1, expectedString); // should not pass
        Assertions.assertNull(expectedString, "echec - doit etre true"); // should not pass
    }
}
