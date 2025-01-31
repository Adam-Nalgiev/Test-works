package com.na.rmuniverse.domain

import com.na.rmuniverse.data.Repository
import com.na.rmuniverse.entity.Character
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute(page: Int): List<Character> {
        return repository.getCharactersList(page)
    }
}