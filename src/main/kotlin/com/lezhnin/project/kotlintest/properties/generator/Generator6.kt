package com.lezhnin.project.kotlintest.properties.generator

import io.kotlintest.properties.Gen

interface Generator6<A, B, C, D, E, F> {
    fun forAll(fn: (A, B, C, D, E, F) -> Unit)
    fun <G> gen(genG: Gen<G>): Generator7<A, B, C, D, E, F, G>
}

internal class IGenerator6<A, B, C, D, E, F>(
        private val iterations: Int,
        private val genA: Gen<A>,
        private val genB: Gen<B>,
        private val genC: Gen<C>,
        private val genD: Gen<D>,
        private val genE: Gen<E>,
        private val genF: Gen<F>
) : Generator6<A, B, C, D, E, F> {
    override fun forAll(fn: (A, B, C, D, E, F) -> Unit) {
        val genFn = { it: Pair<Pair<Pair<Pair<Pair<A, B>, C>, D>, E>, F> ->
            fn(
                    it.first.first.first.first.first,
                    it.first.first.first.first.second,
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
                .zip(genF.constants())
                .forEach(genFn)
        genA.random()
                .zip(genB.random())
                .zip(genC.random())
                .zip(genD.random())
                .zip(genE.random())
                .zip(genF.random())
                .take(iterations)
                .forEach(genFn)
    }

    override fun <G> gen(genG: Gen<G>): Generator7<A, B, C, D, E, F, G> {
        return IGenerator7(iterations, genA, genB, genC, genD, genE, genF, genG)
    }
}