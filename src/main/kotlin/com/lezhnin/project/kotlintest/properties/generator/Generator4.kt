package com.lezhnin.project.kotlintest.properties.generator

import io.kotlintest.properties.Gen

interface Generator4<A, B, C, D> {
    fun forAll(fn: (A, B, C, D) -> Unit)
    fun <E> gen(genE: Gen<E>): Generator5<A, B, C, D, E>
}

class IGenerator4<A, B, C, D>(
        private val iterations: Int,
        private val genA: Gen<A>,
        private val genB: Gen<B>,
        private val genC: Gen<C>,
        private val genD: Gen<D>
) : Generator4<A, B, C, D> {
    override fun forAll(fn: (A, B, C, D) -> Unit) {
        val genFn = { it: Pair<Pair<Pair<A, B>, C>, D> ->
            fn(
                    it.first.first.first,
                    it.first.first.second,
                    it.first.second,
                    it.second
            )
        }

        genA.constants()
                .zip(genB.constants())
                .zip(genC.constants())
                .zip(genD.constants())
                .forEach(genFn)
        genA.random()
                .zip(genB.random())
                .zip(genC.random())
                .zip(genD.random())
                .take(iterations)
                .forEach(genFn)
    }

    override fun <E> gen(genE: Gen<E>): Generator5<A, B, C, D, E> {
        return IGenerator5(iterations, genA, genB, genC, genD, genE)
    }
}