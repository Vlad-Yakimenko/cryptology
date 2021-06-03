package ua.knu

import org.junit.Assert
import org.junit.Test
import ua.knu.util.Randomizer
import java.math.BigInteger

internal class PrimalityTest {

    companion object {
        private val generator: Randomizer = Randomizer(System.currentTimeMillis())
        private val primes = listOf(
            7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
            43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
            101, 103, 107, 109, 113, 127, 131, 137, 139, 149,
            151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
            271, 331, 397, 547, 631, 919, 1657, 1801, 1951, 2269, 2437,
            2791, 3169, 3571, 4219, 4447, 5167, 5419, 6211, 7057, 7351,
            8269, 9241, 10267, 11719, 12097, 13267, 13669, 16651, 19441, 19927, 22447, 23497
        )
        private val composites: MutableList<BigInteger> = ArrayList()

        init {
            val lowerBound = BigInteger.ONE
            val upperBound = BigInteger.valueOf(Short.MAX_VALUE.toLong())
            for (prime in primes) {
                composites.add(
                    generator.getBigInteger(lowerBound, upperBound).multiply(BigInteger.valueOf(prime.toLong()))
                )
            }
        }
    }

    @Test
    fun primesFermatTest() {
        val primalityTest = Fermat(generator, 5)
        for (prime in primes) {
            Assert.assertTrue(primalityTest.testPrimality(BigInteger.valueOf(prime.toLong())))
        }
    }

    @Test
    fun compositesFermatTest() {
        val primalityTest = Fermat(generator, 5)
        for (composite in composites) {
            Assert.assertFalse(primalityTest.testPrimality(composite))
        }
    }

    @Test
    fun primesMillerRabinTest() {
        val primalityTest = MillerRabin(generator, 5)
        for (prime in primes) {
            Assert.assertTrue(primalityTest.testPrimality(BigInteger.valueOf(prime.toLong())))
        }
    }

    @Test
    fun compositesMillerRabinTest() {
        val primalityTest = MillerRabin(generator, 5)
        for (composite in composites) {
            Assert.assertFalse(primalityTest.testPrimality(composite))
        }
    }
}
