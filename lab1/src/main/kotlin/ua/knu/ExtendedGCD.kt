package ua.knu

import java.math.BigInteger

internal object ExtendedGCD {

    fun compute(value1: BigInteger, value2: BigInteger): GCDResult {
        val result = GCDResult()

        if (value1 == BigInteger.ZERO) {
            result.gcd = value2
            result.x = BigInteger.ZERO
            result.y = BigInteger.ONE

        } else {
            val previousResult = compute(value2.mod(value1), value1)
            result.gcd = previousResult.gcd
            result.x = previousResult.y!!.subtract(value2.divide(value1).multiply(previousResult.x))
            result.y = previousResult.x
        }

        return result
    }

    class GCDResult {
        var gcd: BigInteger? = null
        var x: BigInteger? = null
        var y: BigInteger? = null
    }
}
