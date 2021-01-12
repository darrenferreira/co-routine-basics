package com.example.co_routine_basics.basic.repo

import com.example.co_routine_basics.basic.service.Pokemon
import com.example.co_routine_basics.basic.service.PokemonResponse
import com.example.co_routine_basics.basic.service.PokemonService
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import retrofit2.Response

@ExperimentalCoroutinesApi
class BasicRepoTest {

    private lateinit var basicRepo: BasicRepo

    @Mock
    private lateinit var mockPokemonService: PokemonService

    @Before
    fun setUp() {
        initMocks(this)
        basicRepo = BasicRepo(Dispatchers.Unconfined, mockPokemonService)
    }

    @Test
    fun testThatRepoFetchesData() = runBlocking() {
        whenever(mockPokemonService.getAll()).thenReturn(Response.success(getMockPokemonData()))

        val actualResult = basicRepo.getDataFromService()

        assertEquals(actualResult, listOf("0", "1", "2", "3"))

    }

    private fun getMockPokemonData(): PokemonResponse {
        val pokemon = mutableListOf<Pokemon>()
        repeat(4){
            pokemon += createFakePokemon(it.toString())
        }
        return PokemonResponse(4,pokemon)
    }

    private fun createFakePokemon(name: String): Pokemon {
        return Pokemon(name, "")
    }
}