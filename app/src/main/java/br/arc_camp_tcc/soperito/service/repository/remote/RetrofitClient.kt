package br.arc_camp_tcc.soperito.service.repository.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var INSTANCE : Retrofit

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(object : Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                        .newBuilder()
                        .build()
                    return chain.proceed(request)
                }
            })

           if(!::INSTANCE.isInitialized){
               synchronized(RetrofitClient::class) {
                   INSTANCE = Retrofit.Builder()
                       .baseUrl("https://So-Perito-API.arturcampos.repl.co/")
                       .client(httpClient.build())
                       .addConverterFactory(GsonConverterFactory.create())
                       .build()
               }
           }
            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T{
            return getRetrofitInstance().create(serviceClass)
        }
    }
}
