package com.lezhnin.project.kotlintest.properties

import io.kotlintest.matchers.string.beUpperCase
import io.kotlintest.properties.Gen
import io.kotlintest.should
import io.kotlintest.specs.WordSpec

class ConstantGenTest : WordSpec({
    "a generated constant uppercase value" should {
        "be in upper case" {
            Generator.gen(
                    object : ConstantGen<String>() {
                        override fun constants(): Iterable<String> = Gen.string().constants().map { it.toUpperCase() }
                    }
            ).forAll {
                it should beUpperCase()
            }
        }
    }
})
