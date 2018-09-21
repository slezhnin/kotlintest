package com.lezhnin.project.kotlintest.properties

import io.kotlintest.properties.Gen

abstract class RandomGen<T> : Gen<T> {
    override fun constants(): Iterable<T> = listOf()
}
