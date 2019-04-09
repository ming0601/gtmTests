package com.gtm.formation.testsIndustriels.exercises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculatorPlus {
    @Autowired
    Calculator calculator;

    @Autowired
    public CalculatorPlus(Calculator calculator) {
        super();
        this.calculator = calculator;
    }

    public int add(String input1, String input2) {
        try {
            return calculator.add(input1, input2);
        } catch (NumberFormatException e) {
            return 0;
        } catch (RuntimeException exc) {
            throw exc;
        }
    }
}
