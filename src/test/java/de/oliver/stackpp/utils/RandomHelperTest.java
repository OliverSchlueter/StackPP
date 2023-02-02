package de.oliver.stackpp.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomHelperTest {

    private final int MIN = 42;
    private final int MAX = 1337;

    @Test
    void randomInRange() {
        for (int i = 0; i < 1000; i++) {
            double val = RandomHelper.randomInRange(MIN, MAX);

            assert val > MIN && val < MAX;
        }
    }
}