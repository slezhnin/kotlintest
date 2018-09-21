package com.lezhnin.project.kotlintest.properties.generator

import io.kotlintest.properties.Gen

interface Generator5<A, B, C, D, E> {
    fun forAll(fn: (A, B, C, D, E) -> Unit)
    fun <F> gen(genF: Gen<F>): Generator6<A, B, C, D, E, F>
}

internal class IGenerator5<A, B, C, D, E>(
        private val iterations: Int,
        private val genA: Gen<A>,
        private val genB: Gen<B>,
        private val genC: Gen<C>,
        private val genD: Gen<D>,
        private val genE: Gen<E>
) : Generator5<A, B, C, D, E> {
    override fun forAll(fn: (A, B, C, D, E) -> Unit) {
        val genFn = { it: Pair<Pair<Pair<Pair<A, B>, C>, D>, E> ->
            fn(
                    it.first.first.first.first,
                    it.first.first.first.second,
                    it.first.first.second,
                    it.first.second,
                    it.second
            )
        }

        genA.constants()
                .zip(genB.constants())
                .zip(genC.constants())
                .zip(genD.constants())
                .zip(genE.constants())
                .forEach(genFn)
        genA.random()
                .zip(genB.random())
                .zip(genC.random())
                .zip(genD.random())
                .zip(genE.random())
                .take(iterations)
                .forEach(genFn)
    }

    override fun <F> gen(genF: Gen<F>): Generator6<A, B, C, D, E, F> {
        return IGenerator6(iterations, genA, genB, genC, genD, genE, genF)
    }
}