package ua.knu

import ua.knu.util.Randomizer
import java.math.BigInteger

internal class MillerRabin(private val generator: Randomizer, private val loopNumber: Int) {

    private fun isOdd(value: BigInteger): Boolean {
        return value.and(BigInteger.ONE) == BigInteger.ONE
    }

    fun testPrimality(value: BigInteger): Boolean {
        if (!isOdd(value)) {
            return false
        }
        val valueSubtracted = value.subtract(BigInteger.ONE)
        var d = valueSubtracted
        var s = 0
        while (!isOdd(d)) {
            s += 1
            d = d.shiftRight(1)
        }
        for (i in 0 until loopNumber) {
            val a: BigInteger = generator.getBigInteger(BigInteger.ONE, valueSubtracted)
            var x = BinPow.compute(a, d, value)
            if (x != BigInteger.ONE && x != valueSubtracted) {
                var isAlive = false
                var j = 0
                while (j < s - 1 && !isAlive) {
                    x = x.multiply(x).mod(value)
                    if (x == BigInteger.ONE) {
                        return false
                    }
                    if (x == valueSubtracted) {
                        isAlive = true
                    }
                    j++
                }
                if (!isAlive) {
                    return false
                }
            }
        }
        return true
    }
}
