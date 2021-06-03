package ua.knu

import org.junit.Assert
import org.junit.Test
import ua.knu.util.Randomizer
import java.math.BigInteger
import java.util.*

internal class BinPowTest {

    private fun pow(value: Int, power: Int, mod: Int): Int {
        var result = 1
        for (i in 0 until power) {
            result = result * value % mod
        }
        return result
    }

    @Test
    fun computeInt() {
        val lowerBound = 1
        val upperBound = Short.MAX_VALUE.toInt()
        val value: Int = GENERATOR.getInteger(lowerBound, upperBound)
        val power: Int = GENERATOR.getInteger(lowerBound, upperBound)
        val mod: Int = GENERATOR.getInteger(lowerBound, upperBound)
        val expectedResult = pow(value, power, mod)
        val result = BinPow.compute(
            BigInteger.valueOf(value.toLong()),
            BigInteger.valueOf(power.toLong()),
            BigInteger.valueOf(mod.toLong())
        )
        Assert.assertEquals(BigInteger.valueOf(expectedResult.toLong()), result)
    }

    @Test
    fun computeBigInteger() {
        val value = BigInteger(length, random)
        val power = BigInteger(length, random)
        val mod = BigInteger(length, random)
        val result = BinPow.compute(value, power, mod)
        Assert.assertEquals(result.compareTo(mod).toLong(), -1)
    }

    @Test
    fun valueZero() {
        val value = BigInteger.ZERO
        val power = BigInteger(length, random)
        val mod = BigInteger(length, random)
        val result = BinPow.compute(value, power, mod)
        Assert.assertEquals(result, BigInteger.ZERO)
    }

    @Test
    fun powZero() {
        val value = BigInteger(length, random)
        val power = BigInteger.ZERO
        val mod = BigInteger(length, random)
        val result = BinPow.compute(value, power, mod)
        Assert.assertEquals(result, BigInteger.ONE)
    }

    @Test
    fun valueOne() {
        val value = BigInteger.ONE
        val power = BigInteger(length, random)
        val mod = BigInteger(length, random)
        val result = BinPow.compute(value, power, mod)
        Assert.assertEquals(result, BigInteger.ONE)
    }

    @Test
    fun powOne() {
        val value = BigInteger(length, random)
        val power = BigInteger.ONE
        val mod = BigInteger(length, random)
        val result = BinPow.compute(value, power, mod)
        Assert.assertEquals(result, value.mod(mod))
    }

    @Test
    fun nullMod() {
        val value = BigInteger(length, random)
        val power = BigInteger.ONE
        val result = BinPow.compute(value, power, null)
        Assert.assertEquals(result, value)
    }

    companion object {
        private val GENERATOR: Randomizer = Randomizer(System.currentTimeMillis())
        private val random = Random()
        private const val length = 1024
    }
}
