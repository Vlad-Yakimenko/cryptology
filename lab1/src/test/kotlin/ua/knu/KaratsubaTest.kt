package ua.knu

import org.junit.Assert.assertEquals
import org.junit.Test
import ua.knu.util.Randomizer
import java.math.BigInteger

class KaratsubaTest {
    private val generator: Randomizer = Randomizer(System.currentTimeMillis())
    private val lowerBound = BigInteger.ONE
    private val upperBound = BigInteger.valueOf(Short.MAX_VALUE.toLong())

    @Test
    fun compute() {
        val value1: BigInteger = generator.getBigInteger(lowerBound, upperBound)
        val value2: BigInteger = generator.getBigInteger(lowerBound, upperBound)
        val expectedResult = value1.multiply(value2)
        var result: BigInteger = Karatsuba.compute(value1, value2)
        assertEquals(expectedResult, result)
        result = Karatsuba.compute(value2, value1)
        assertEquals(expectedResult, result)
    }

    @Test
    fun computeZero() {
        val value1 = BigInteger.ZERO
        val value2: BigInteger = generator.getBigInteger(lowerBound, upperBound)
        var result: BigInteger = Karatsuba.compute(value1, value2)
        assertEquals(value1, result)
        assertEquals(BigInteger.ZERO, result)
        result = Karatsuba.compute(value2, value1)
        assertEquals(value1, result)
        assertEquals(BigInteger.ZERO, result)
    }

    @Test
    fun computeOne() {
        val value1 = BigInteger.ONE
        val value2: BigInteger = generator.getBigInteger(lowerBound, upperBound)
        var result: BigInteger = Karatsuba.compute(value1, value2)
        assertEquals(value2, result)
        result = Karatsuba.compute(value2, value1)
        assertEquals(value2, result)
    }
}