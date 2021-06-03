package ua.knu

import ua.knu.util.Randomizer
import java.math.BigInteger

internal class Fermat(private val generator: Randomizer, private val loopNumber: Int) {
    fun testPrimality(value: BigInteger): Boolean {
        val valueSubtracted = value.subtract(BigInteger.ONE)
        for (i in 0 until loopNumber) {
            val randomNumber: BigInteger = generator.getBigInteger(BigInteger.ONE, valueSubtracted)
            val testingNumber = BinPow.compute(randomNumber, valueSubtracted, value)
            if (testingNumber.compareTo(BigInteger.ONE) != 0) {
                return false
            }
        }
        return true
    }
}
