package com.lezhnin.project.kotlintest.properties

import io.kotlintest.properties.Gen
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class GeneratorTest : WordSpec({
    "generator" should {
        "run with the given number of iterations" {
            Generator(1).gen(Gen.int()).forAll { it shouldBe it }
        }
        "generate 1 random value" {
            Generator.gen(Gen.string())
                    .forAll {
                        it shouldBe it
                    }
        }
        "generate 2 random values" {
            Generator.gen(Gen.string())
                    .gen(Gen.int())
                    .forAll { a, b ->
                        a shouldBe a
                        b shouldBe b
                    }
        }
        "generate 3 random values" {
            Generator.gen(Gen.string())
                    .gen(Gen.int())
                    .gen(Gen.string())
                    .forAll { a, b, c ->
                        a shouldBe a
                        b shouldBe b
                        c shouldBe c
                    }
        }
        "generate 4 random values" {
            Generator.gen(Gen.string())
                    .gen(Gen.int())
                    .gen(Gen.string())
                    .gen(Gen.int())
                    .forAll { a, b, c, d ->
                        a shouldBe a
                        b shouldBe b
                        c shouldBe c
                        d shouldBe d
                    }
        }
        "generate 5 random values" {
            Generator.gen(Gen.string())
                    .gen(Gen.int())
                    .gen(Gen.string())
                    .gen(Gen.int())
                    .gen(Gen.string())
                    .forAll { a, b, c, d, e ->
                        a shouldBe a
                        b shouldBe b
                        c shouldBe c
                        d shouldBe d
                        e shouldBe e
                    }
        }
        "generate 6 random values" {
            Generator.gen(Gen.string())
                    .gen(Gen.int())
                    .gen(Gen.string())
                    .gen(Gen.int())
                    .gen(Gen.string())
                    .gen(Gen.int())
                    .forAll { a, b, c, d, e, f ->
                        a shouldBe a
                        b shouldBe b
                        c shouldBe c
                        d shouldBe d
                        e shouldBe e
                        f shouldBe f
                    }
        }
        "generate 7 random values" {
            Generator.gen(Gen.string())
                    .gen(Gen.int())
                    .gen(Gen.string())
                    .gen(Gen.int())
                    .gen(Gen.string())
                    .gen(Gen.int())
                    .gen(Gen.string())
                    .forAll { a, b, c, d, e, f, g ->
                        a shouldBe a
                        b shouldBe b
                        c shouldBe c
                        d shouldBe d
                        e shouldBe e
                        f shouldBe f
                        g shouldBe g
                    }
        }
    }
})
