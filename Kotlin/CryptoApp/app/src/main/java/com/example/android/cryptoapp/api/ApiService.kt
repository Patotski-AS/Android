package com.example.android.cryptoapp.api

import com.example.android.cryptoapp.pojo.CoinInfoListOfData
import com.example.android.cryptoapp.pojo.CoinPriceInfoRawData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

//9ebab3b9f040f4a42b5124f5180f16f2da3e4eae1e88f25acf679205e72ee484

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PaRAM_API_KEY) api_key:String = "",
        @Query(QUERY_PaRAM_API_LIMIT) limit:Int = 10,
        @Query(QUERY_PaRAM_API_TO_SYMBOL) tSym:String = CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PaRAM_API_KEY) api_key:String = "",
        @Query(QUERY_PaRAM_API_FROM_SYMBOLS) fSyms:String ,
        @Query(QUERY_PaRAM_API_TO_SYMBOLS) tSyms:String = CURRENCY
    ):Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PaRAM_API_KEY = "api_key"
        private const val QUERY_PaRAM_API_LIMIT = "limit"
        private const val QUERY_PaRAM_API_TO_SYMBOL = "tsym"
        private const val QUERY_PaRAM_API_TO_SYMBOLS = "tsyms"
        private const val QUERY_PaRAM_API_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }


}