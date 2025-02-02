package com.mountech.binner.presentation.screen.request

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mountech.binner.data.database.Dao
import com.mountech.binner.data.model.dbo.BankDbo
import com.mountech.binner.data.model.dbo.BinInfoDbo
import com.mountech.binner.data.model.dbo.CountryDbo
import com.mountech.binner.data.model.dbo.NumberInfoDbo
import com.mountech.binner.data.model.dto.BinInfoDto
import com.mountech.binner.domain.GetBinInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RequestViewModel
@Inject constructor(
    private val getBinInfoUseCase: GetBinInfoUseCase,
    private val dao: Dao
) : ViewModel() {

    private val _binInfo = MutableStateFlow(BinInfoDto(number = null, country = null, bank = null))
    val binInfo get() = _binInfo.asStateFlow()

    suspend fun getBinInfo(code: String) {
        val info = viewModelScope.async(Dispatchers.IO) {
            getBinInfoUseCase.execute(code)
        }.await()

        info.let {
            _binInfo.value = info!!
            saveInfo(dtoToDboMapper(info, code))
        }
    }

    private fun saveInfo(binInfoDbo: BinInfoDbo) {
        viewModelScope.launch {
            dao.insert(binInfoDbo)
            Log.d("SAVE TO DB", "$binInfoDbo")
        }
    }

    private fun dtoToDboMapper(dto: BinInfoDto, bin: String): BinInfoDbo {
        return BinInfoDbo(
            id = Random.nextInt(),
            number = NumberInfoDbo(dto.number?.length, dto.number?.luhn),
            scheme = dto.scheme,
            type = dto.type,
            brand = dto.brand,
            country = CountryDbo(),
            bank = BankDbo(dto.bank?.name),
            bin = bin
        )
    }
}