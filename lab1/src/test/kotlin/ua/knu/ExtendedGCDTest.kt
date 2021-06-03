package ua.knu

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import ua.knu.util.Randomizer
import java.math.BigInteger

internal class ExtendedGCDTest {

    private fun gcd(value1: Int, value2: Int): Int {
        for (i in Math.min(value1, value2) downTo 1) {
            if (value1 % i == 0 && value2 % i == 0) {
                return i
            }
        }
        return 0
    }

    @Test
    fun computeInteger() {
        val value1: Int = generator.getInteger(lowerBound, upperBound)
        val value2: Int = generator.getInteger(lowerBound, upperBound)
        val expectedResult = gcd(value1, value2)
        val result: BigInteger? =
            ExtendedGCD.compute(BigInteger.valueOf(value1.toLong()), BigInteger.valueOf(value2.toLong())).gcd
        Assert.assertEquals(BigInteger.valueOf(expectedResult.toLong()), result)
    }

    @Test
    fun computeBigInteger() {
        val value1: BigInteger = generator.getBigInteger(
            BigInteger.valueOf(lowerBound.toLong()), BigInteger.valueOf(
                upperBound.toLong()
            )
        )
        val value2: BigInteger = generator.getBigInteger(
            BigInteger.valueOf(lowerBound.toLong()), BigInteger.valueOf(
                upperBound.toLong()
            )
        )
        val result: ExtendedGCD.GCDResult = ExtendedGCD.compute(value1, value2)
        assertEquals(result.gcd, result.x?.multiply(value1)?.add(result.y?.multiply(value2)))
    }

    companion object {
        private val generator: Randomizer = Randomizer(System.currentTimeMillis())
        private const val lowerBound = 1
        private const val upperBound = Short.MAX_VALUE.toInt()
    }
}