package ua.knu

import java.math.BigInteger

internal object Karatsuba {
    fun compute(value1: BigInteger, value2: BigInteger): BigInteger {
        val length = Math.max(value1.bitLength(), value2.bitLength())
        if (length < 2) {
            return value1.multiply(value2)
        }
        val shift = length / 2
        val x = BigInteger.ONE.shiftLeft(shift)
        val a = value1.shiftRight(shift)
        val b = value1.and(x.subtract(BigInteger.ONE))
        val c = value2.shiftRight(shift)
        val d = value2.and(x.subtract(BigInteger.ONE))
        val ac = compute(a, c)
        val bd = compute(b, d)
        val xCoefficient = compute(a.add(b), c.add(d)).subtract(ac).subtract(bd)
        return ac.shiftLeft(shift * 2).add(xCoefficient.shiftLeft(shift)).add(bd)
    }
}
