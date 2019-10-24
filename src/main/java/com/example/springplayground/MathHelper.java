package com.example.springplayground;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MathHelper {

    public enum Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE;
    }

    public double calculate(String strOperator, double x, double y) {
        Operator operator = Operator.valueOf(strOperator.toUpperCase());
        Double val = null;
        switch (operator) {
            case ADD:
                val =  x + y;
                break;
            case SUBTRACT:
                val = x - y;
                break;
            case MULTIPLY:
                val = x * y;
                break;
            case DIVIDE:
                val = x / y;
                break;
            default:
                val = x + y;
                break;
        }
        return val.doubleValue();
    }

    public double sum(List<Double> numbers) {
        double sum = 0.;
        if (numbers != null) {
            for(Double n: numbers) {
                if (n != null) {
                    sum += n.doubleValue();
                }
            }
        }
        return sum;
    }
}
