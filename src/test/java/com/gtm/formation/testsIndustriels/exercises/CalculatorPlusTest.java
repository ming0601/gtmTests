package com.gtm.formation.testsIndustriels.exercises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class CalculatorPlusTest {
    private Calculator mockCalculator = Mockito.mock(Calculator.class);
    private CalculatorPlus subject = new CalculatorPlus(mockCalculator);

    @DisplayName("Testing CalculatorPlus calling Calculator")
    @ParameterizedTest(name="Case {index}: inputs {1} and {2} should return {0}")
    @CsvSource({
            "OK,2,3,5",
            "nbFormatException,a,b,0",
            "otherException,-,-,0"})
    void testSumPlus (String cas, String input1, String input2, int result) {
        switch (cas) {
            case "OK": {
                Mockito.doReturn(5).when(mockCalculator).add(input1, input2);
                Assertions.assertEquals(result, subject.add(input1, input2));
            }
            break;
            case ("nbFormatException"): {
                Mockito.doThrow(NumberFormatException.class).when(mockCalculator).add(input1, input2);
                Assertions.assertEquals(result, subject.add(input1, input2));
            }
            break;
            case ("otherException"): {
                Mockito.doThrow(RuntimeException.class).when(mockCalculator).add(input1, input2);
                RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()-> subject.add(input1, input2));
                Assertions.assertTrue(exception.toString().contains("java.lang.RuntimeException"));
            }
            break;
        }
    }
}
