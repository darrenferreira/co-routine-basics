package com.example.co_routine_basics.basic.repo

import android.util.Log
import com.example.co_routine_basics.basic.service.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class BasicRepo internal constructor(
    private val dispatcher: CoroutineContext,
    private val pokemonService: PokemonService
) {

    constructor() : this(Dispatchers.IO, PokemonService())


    suspend fun getDataFromService(): List<String> {
        return withContext(dispatcher) {
            pokemonService.getAll().body()?.results.orEmpty().take(20).map { it.name }
        }
    }


}