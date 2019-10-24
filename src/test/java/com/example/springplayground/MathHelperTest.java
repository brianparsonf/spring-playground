package com.example.springplayground;

import com.example.springplayground.testutil.HappyPathTest;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MathHelperTest {

    private MathHelper mathHelper = new MathHelper();

    private double result;

    @HappyPathTest
    @DisplayName("calculate: add")
    void calculate_add_canAdd() {
        result = mathHelper.calculate("ADD", 3, 2);
        assertEquals(5., result);
        result = mathHelper.calculate("ADD", 0, 0);
        assertEquals(0., result);
        result = mathHelper.calculate("ADD", -8, 12);
        assertEquals(4., result);
    }

    @HappyPathTest
    @DisplayName("calculate: subtract")
    void calculate_subtract_canSubtract() {
        result = mathHelper.calculate("subtract", 38, 2);
        assertEquals(36., result);
    }

    @HappyPathTest
    @DisplayName("calculate: multiply")
    void calculate_multiply_canMultiply() {
        result = mathHelper.calculate("multiply", 9, 3);
        assertEquals(27., result);
    }

    @HappyPathTest
    @DisplayName("calculate: divide")
    void calculate_divide_canDivide() {
        result = mathHelper.calculate("divide", 9, 3);
        assertEquals(3., result);
        result = mathHelper.calculate("divide", 9, 2);
        assertEquals(4.5, result);
    }

    @HappyPathTest
    @DisplayName("volume: get volume")
    void volume_calculatesVolume() {
        result = mathHelper.volume(3,4,5);
        assertEquals(60, result);
    }

}