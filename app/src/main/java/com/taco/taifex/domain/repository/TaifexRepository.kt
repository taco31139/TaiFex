package com.taco.taifex.domain.repository

import com.taco.taifex.domain.server.ApiService
import com.taco.taifex.model.BwibbuAllModel
import com.taco.taifex.domain.server.Result
import com.taco.taifex.model.StockDayAvgModel
import com.taco.taifex.model.StockDayModel
import com.taco.taifex.model.TaifexModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip

class TaifexRepository(
    private val apiService: ApiService
) {
    /**
     * 取得三個 API 的資料
     *
     * @return Flow<Result<TaifexModel>>
     */
    fun fetchTaifexData() : Flow<Result<TaifexModel>> {
        var bwibbuFlow = flow {
            emit(runCatching { apiService.getBWIBBU() })
        }

        var stockDayAvgFlow = flow {
            emit(runCatching { apiService.getSrockDayAvgAll() })
        }

        var stockDayFlow = flow {
            emit(runCatching { apiService.getSrockDayAll() })
        }

        return bwibbuFlow.zip(stockDayAvgFlow) { bwibbuResult, stockDayAvgResult ->
            bwibbuResult to stockDayAvgResult
        }.zip(stockDayFlow) { (bwibbuResult, stockDayAvgResult), stockDayResult ->
            bwibbuResult.fold(
                onSuccess = { bwibbuResponse ->
                    stockDayAvgResult.fold(
                        onSuccess = { stockDayAvgResponse ->
                            stockDayResult.fold(
                                onSuccess = { stockDayResponse ->
                                    Result.Success(TaifexModel(bwibbuResponse.body()!!, stockDayResponse.body()!!, stockDayAvgResponse.body()!!))
                                },
                                onFailure = { error -> Result.Error(error) }
                            )
                        },
                        onFailure = { error -> Result.Error(error) }
                    )
                },
                onFailure = { error -> Result.Error(error) }
            )
        }
    }
}