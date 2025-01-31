package com.mountech.binner.data

import android.util.Log
import com.mountech.binner.data.model.dto.BinInfoDto
import com.mountech.binner.data.network.Client
import javax.inject.Inject

class Repository @Inject constructor(
    networkClient: Client
) {
    private val client = networkClient.requests

    suspend fun getBinInfo(code: String): BinInfoDto? {
        val re = client.requireInfo(code = code)
        Log.d("BIN INFO REPOS", "$re" )
        return re
    }

}