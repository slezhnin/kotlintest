package com.lezhnin.project.kotlintest.properties

import io.kotlintest.matchers.string.beUpperCase
import io.kotlintest.properties.Gen
import io.kotlintest.should
import io.kotlintest.specs.WordSpec

class RandomGenTest : WordSpec({
    "a generated random uppercase value" should {
        "be in upper case" {
            Generator.gen(
                    object : RandomGen<String>() {
                        override fun random(): Sequence<String> = Gen.string().random().map { it.toUpperCase() }
                    }
            ).forAll {
                it should beUpperCase()
            }
        }
    }
})
