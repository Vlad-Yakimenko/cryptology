package ua.knu.util

import java.math.BigInteger
import java.util.*

internal class Randomizer(seed: Long) {

    private val random: Random

    fun getBigInteger(lowerBound: BigInteger?, upperBound: BigInteger): BigInteger {
        var result: BigInteger
        var i = 0

        do {
            result = BigInteger(upperBound.bitLength(), random)
            i++
        } while (result <= lowerBound || result >= upperBound)

        return result
    }

    fun getInteger(lowerBound: Int, upperBound: Int): Int {
        return random.nextInt(upperBound) + lowerBound
    }

    init {
        random = Random(seed)
    }
}
