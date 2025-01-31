package com.mountech.binner.domain

import com.mountech.binner.data.Repository
import javax.inject.Inject

class GetBinsHistoryUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute() {

    }
}