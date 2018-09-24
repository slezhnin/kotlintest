package com.lezhnin.project.kotlintest.properties

import com.lezhnin.project.kotlintest.properties.generator.Generator1
import com.lezhnin.project.kotlintest.properties.generator.IGenerator1
import io.kotlintest.properties.Gen

class Generator(private val iterations: Int = defaultIterations) {
    companion object {
        private const val defaultIterations = 100

        fun <A> gen(genA: Gen<A>, iterations: Int = defaultIterations): Generator1<A> {
            return Generator(iterations).gen(genA)
        }
    }

    fun <A> gen(genA: Gen<A>): Generator1<A> {
        return IGenerator1(iterations, genA)
    }
}
