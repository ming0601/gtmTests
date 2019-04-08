package com.gtm.formation.testsIndustriels.exercises;

public class Calculator {
    public int add(String a, String b){
        try {
            return Integer.parseInt(a) + Integer.parseInt(b);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
