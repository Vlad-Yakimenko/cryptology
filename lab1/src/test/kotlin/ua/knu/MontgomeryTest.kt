package ua.knu

import org.junit.Assert.assertEquals
import org.junit.Test
import ua.knu.util.Randomizer
import java.math.BigInteger

class MontgomeryTest {

    private val lowerBound = BigInteger.ONE
    private val upperBound = BigInteger.valueOf(Short.MAX_VALUE.toLong())
    private val generator: Randomizer = Randomizer(System.currentTimeMillis())

    private val mod = BigInteger("170141183460469231731687303715884105727")
    private val montgomeryMultiplication: Montgomery = Montgomery(mod)

    @Test
    fun multiply() {
        val value1: BigInteger = generator.getBigInteger(lowerBound, upperBound)
        val value2: BigInteger = generator.getBigInteger(lowerBound, upperBound)
        val expectedResult = value1.multiply(value2).mod(mod)
        val value1Repr: BigInteger = montgomeryMultiplication.represent(value1)
        val value2Repr: BigInteger = montgomeryMultiplication.represent(value2)
        val resultRepr: BigInteger = montgomeryMultiplication.multiply(value1Repr, value2Repr)
        val result: BigInteger = montgomeryMultiplication.multiply(resultRepr, BigInteger.ONE)
        assertEquals(expectedResult, result)
    }

    @Test
    fun pow() {
        val value1: BigInteger = generator.getBigInteger(lowerBound, upperBound)
        val value2: BigInteger = generator.getBigInteger(lowerBound, upperBound)
        val binPowerResult = BinPow.compute(value1, value2, mod)
        val montgomeryResult: BigInteger = montgomeryMultiplication.pow(value1, value2)
        assertEquals(binPowerResult, montgomeryResult)
    }
}
