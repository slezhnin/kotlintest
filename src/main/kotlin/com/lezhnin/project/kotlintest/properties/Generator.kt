package com.lezhnin.project.kotlintest.properties

import com.lezhnin.project.kotlintest.properties.generator.Generator1
import com.lezhnin.project.kotlintest.properties.generator.IGenerator1
import io.kotlintest.properties.Gen

class Generator(private val iterations: Int = 100) {
    fun <A> gen(genA: Gen<A>): Generator1<A> {
        return IGenerator1(iterations, genA)
    }
}
