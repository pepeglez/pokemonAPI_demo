package com.example.pokemonapi

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 3)
    }

    @Test
    fun split_string() {
        val url = "https://pokeapi.co/api/v2/pokemon/3/"
        val list: List<String> = url.split("/")
        print(list[6])
        assertEquals(4, 2 + 3)
    }
}