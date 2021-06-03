package ua.knu

import java.math.BigInteger

internal class Montgomery(private val mod: BigInteger) {

    private val modReversed: BigInteger
    private val r: BigInteger
    private val shift: Int

    fun represent(value: BigInteger): BigInteger {
        return value.shiftLeft(shift).mod(mod)
    }

    fun multiply(value1Repr: BigInteger, value2Repr: BigInteger?): BigInteger {
        val t = value1Repr.multiply(value2Repr)
        var u = t.add(t.multiply(modReversed).and(r.subtract(BigInteger.ONE)).multiply(mod)).shiftRight(shift)
        if (u > mod) {
            u = u.subtract(mod)
        }
        return u
    }

    fun pow(value: BigInteger, p: BigInteger): BigInteger {
        var p = p
        var valueRepr = represent(value)
        var resultRepr = represent(BigInteger.ONE)
        while (p != BigInteger.ZERO) {
            if (p.and(BigInteger.ONE) == BigInteger.ONE) {
                resultRepr = multiply(resultRepr, valueRepr)
            }
            valueRepr = multiply(valueRepr, valueRepr)
            p = p.shiftRight(1)
        }
        return multiply(resultRepr, BigInteger.ONE)
    }

    init {
        shift = mod.bitLength()
        r = BigInteger.ONE.shiftLeft(shift)
        modReversed = ExtendedGCD.compute(mod, r).x!!.negate()
    }
}
