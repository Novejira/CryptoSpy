package com.nafi.cryptospy.data.source.network.service

import com.nafi.cryptospy.BuildConfig
import com.nafi.cryptospy.data.source.network.model.coin.CoinListResponse
import com.nafi.cryptospy.data.source.network.model.detail.DetailResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface CryptoSpyApiService {
    @Headers(
        "Accept: application/json",
        "x-cg-api-key: ${BuildConfig.API_KEY}",
    )
    @GET("markets")
    suspend fun getCoins(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("precision") precision: Int = 2,
        @Query("sparkline") sparkLine: Boolean = false,
        @Query("per_page") perPage: Int = 20,
    ): List<CoinListResponse>

    @GET("{id}")
    suspend fun getDetail(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = true,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
        @Query("sparkline") sparkline: Boolean = false,
    ): DetailResponse

    companion object {
        @JvmStatic
        operator fun invoke(): CryptoSpyApiService {
            val okHttpClient =
                OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            return retrofit.create(CryptoSpyApiService::class.java)
        }
    }
}
