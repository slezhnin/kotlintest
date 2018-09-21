package com.lezhnin.project.kotlintest.properties.generator

import io.kotlintest.properties.Gen

interface Generator1<A> {
    fun forAll(fn: (A) -> Unit)
    fun <B> gen(genB: Gen<B>): Generator2<A, B>
}

internal class IGenerator1<A>(
        private val iterations: Int,
        private val genA: Gen<A>
) : Generator1<A> {
    override fun forAll(fn: (A) -> Unit) {
        genA.constants().forEach(fn)
        genA.random().take(iterations).forEach(fn)
    }

    override fun <B> gen(genB: Gen<B>): Generator2<A, B> {
        return IGenerator2(iterations, genA, genB)
    }
}
