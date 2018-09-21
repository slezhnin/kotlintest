package com.lezhnin.project.kotlintest.properties.generator

import io.kotlintest.properties.Gen

interface Generator2<A, B> {
    fun forAll(fn: (A, B) -> Unit)
    fun <C> gen(genC: Gen<C>): Generator3<A, B, C>
}

internal class IGenerator2<A, B>(
        private val iterations: Int,
        private val genA: Gen<A>,
        private val genB: Gen<B>
) : Generator2<A, B> {
    override fun forAll(fn: (A, B) -> Unit) {
        val genFn = { it: Pair<A, B> ->
            fn(
                    it.first,
                    it.second
            )
        }

        genA.constants()
                .zip(genB.constants())
                .forEach(genFn)
        genA.random()
                .zip(genB.random())
                .take(iterations)
                .forEach(genFn)
    }

    override fun <C> gen(genC: Gen<C>): Generator3<A, B, C> {
        return IGenerator3(iterations, genA, genB, genC)
    }
}