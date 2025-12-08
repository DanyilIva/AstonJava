package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTests {

    @Test
    public void testFactorial() {
        assertAll(
                () -> assertEquals(120, Operations.factorial(5)),
                () -> assertEquals(1, Operations.factorial(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> Operations.factorial(-1))
        );
    }

    @Test
    public void testTrArea() {
        assertEquals(6.0, Operations.trArea(3,4,5), 000.1);
    }

    @Test
    public void testCalculations() {
        assertAll(
                () -> assertEquals(5, Operations.add(2,3)),
                () -> assertEquals(3, Operations.subtract(6,3)),
                () -> assertEquals(15, Operations.multiply(5,3)),
                () -> assertEquals(10.0, Operations.divide(30,3)),
                () -> assertThrows(ArithmeticException.class, () -> Operations.divide(30,0))
        );
    }

    @Test
    public void testComparsion() {
        assertAll(
                () -> assertTrue(Operations.greater(2,1)),
                () -> assertFalse(Operations.lesser(2,1)),
                () -> assertTrue(Operations.equal(1,1)),
                () -> assertFalse(Operations.equal(1,2))
        );
    }
}
