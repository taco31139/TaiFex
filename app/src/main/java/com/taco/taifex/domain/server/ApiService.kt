package com.taco.taifex.domain.server

import com.taco.taifex.domain.data.BwibbuAllRs
import com.taco.taifex.domain.data.StockDayAvgRs
import com.taco.taifex.domain.data.StockDayRs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/exchangeReport/BWIBBU_ALL")
    suspend fun getBWIBBU(): Response<List<BwibbuAllRs>>

    @GET("/v1/exchangeReport/STOCK_DAY_AVG_ALL")
    suspend fun getSrockDayAvgAll(): Response<List<StockDayAvgRs>>

    @GET("/v1/exchangeReport/STOCK_DAY_ALL")
    suspend fun getSrockDayAll(): Response<List<StockDayRs>>
}