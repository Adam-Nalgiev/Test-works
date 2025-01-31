package com.mountech.binner.domain

import com.mountech.binner.data.Repository
import com.mountech.binner.data.model.dto.BinInfoDto
import javax.inject.Inject

class GetBinInfoUseCase @Inject constructor(private val repository: Repository){
    suspend fun execute(code: String): BinInfoDto? {
        return repository.getBinInfo(code)
    }
}