package com.mountech.binner.data

import com.mountech.binner.data.database.Dao
import com.mountech.binner.data.model.dto.BinInfoDto
import com.mountech.binner.data.network.Client
import com.mountech.binner.entity.BinInfo
import javax.inject.Inject

class Repository @Inject constructor(
    private val database: Dao,
    networkClient: Client
) {
    private val client = networkClient.requests

    suspend fun getBinInfo(code: String): BinInfoDto? {
        return client.requireInfo(code = code)
    }

    suspend fun getBinsHistory(page: Int, offset: Int): List<BinInfo> {
        return database.getAll(page, offset)
    }

}