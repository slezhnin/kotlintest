package com.lezhnin.project.kotlintest.properties.generator

import io.kotlintest.properties.Gen

interface Generator3<A, B, C> {
    fun forAll(fn: (A, B, C) -> Unit)
    fun <D> gen(genD: Gen<D>): Generator4<A, B, C, D>
}

internal class IGenerator3<A, B, C>(
        private val iterations: Int,
        private val genA: Gen<A>,
        private val genB: Gen<B>,
        private val genC: Gen<C>
) : Generator3<A, B, C> {
    override fun forAll(fn: (A, B, C) -> Unit) {
        val genFn = { it: Pair<Pair<A, B>, C> ->
            fn(
                    it.first.first,
                    it.first.second,
                    it.second
            )
        }

        genA.constants()
                .zip(genB.constants())
                .zip(genC.constants())
                .forEach(genFn)
        genA.random()
                .zip(genB.random())
                .zip(genC.random())
                .take(iterations)
                .forEach(genFn)
    }

    override fun <D> gen(genD: Gen<D>): Generator4<A, B, C, D> {
        return IGenerator4(iterations, genA, genB, genC, genD)
    }
}