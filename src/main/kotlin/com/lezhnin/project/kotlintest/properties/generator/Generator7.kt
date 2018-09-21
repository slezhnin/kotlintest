package com.lezhnin.project.kotlintest.properties.generator

import io.kotlintest.properties.Gen

interface Generator7<A, B, C, D, E, F, G> {
    fun forAll(fn: (A, B, C, D, E, F, G) -> Unit)
}

internal class IGenerator7<A, B, C, D, E, F, G>(
        private val iterations: Int,
        private val genA: Gen<A>,
        private val genB: Gen<B>,
        private val genC: Gen<C>,
        private val genD: Gen<D>,
        private val genE: Gen<E>,
        private val genF: Gen<F>,
        private val genG: Gen<G>
) : Generator7<A, B, C, D, E, F, G> {
    override fun forAll(fn: (A, B, C, D, E, F, G) -> Unit) {
        val genFn = { it: Pair<Pair<Pair<Pair<Pair<Pair<A, B>, C>, D>, E>, F>, G> ->
            fn(
                    it.first.first.first.first.first.first,
                    it.first.first.first.first.first.second,
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
                .zip(genG.constants())
                .forEach(genFn)
        genA.random()
                .zip(genB.random())
                .zip(genC.random())
                .zip(genD.random())
                .zip(genE.random())
                .zip(genF.random())
                .zip(genG.random())
                .take(iterations)
                .forEach(genFn)
    }
}