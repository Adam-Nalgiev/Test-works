package com.mountech.binner.domain

import com.mountech.binner.data.Repository
import com.mountech.binner.entity.BinInfo
import javax.inject.Inject

class GetBinsHistoryUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute(page: Int, offset: Int): List<BinInfo> {
        return repository.getBinsHistory(page, offset)
    }
}