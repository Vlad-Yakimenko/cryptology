package ua.knu

import java.math.BigInteger

internal object BinPow {

    fun compute(value: BigInteger, exponent: BigInteger, mod: BigInteger?): BigInteger {
        var value = value
        var exponent = exponent
        var result = BigInteger.ONE

        while (exponent != BigInteger.ZERO) {
            if (exponent.and(BigInteger.ONE) == BigInteger.ONE) {
                result = modulus(result.multiply(value), mod)
            }

            value = modulus(value.multiply(value), mod)
            exponent = exponent.shiftRight(1)
        }

        return result
    }

    private fun modulus(value: BigInteger, mod: BigInteger?): BigInteger {
        return if (mod == null) value else value.mod(mod)
    }
}
