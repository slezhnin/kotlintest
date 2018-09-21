package com.lezhnin.project.kotlintest.properties

import io.kotlintest.properties.Gen

abstract class ConstantGen<T> : Gen<T> {
    override fun random(): Sequence<T> = listOf<T>().asSequence()
}
