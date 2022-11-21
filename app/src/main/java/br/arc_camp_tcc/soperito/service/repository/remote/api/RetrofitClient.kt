package br.arc_camp_tcc.soperito.service.repository.remote.api

import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var INSTANCE : Retrofit
        private var email : String = ""
        private var senha : String = ""

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()

            // intersepta requisiçao modifica parametros e retorna as alteraçeos
            httpClient.addInterceptor (object : Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                        .newBuilder()
                        .addHeader(DataBaseConstants.HEADER.PERSON_KEY, email)
                        .addHeader(DataBaseConstants.HEADER.TOKEN_KEY, senha)
                        .build()
                    return  chain.proceed(request)
                }
            })

            if(!Companion::INSTANCE.isInitialized){
               synchronized(RetrofitClient::class) {
                   INSTANCE = Retrofit.Builder()
                       .baseUrl("http://192.168.0.4/so_perito/")
                       .client(httpClient.build())
                       .addConverterFactory(GsonConverterFactory.create())
                       .build()
               }
           }
            return INSTANCE
        }

         // retorna instancia de um serviço
        fun <T> getService(serviceClass: Class<T>): T{
            return getRetrofitInstance().create(serviceClass)
        }

        fun addHeaders(personkeyValue: String, tokenValue: String){
            email = tokenValue
            senha = personkeyValue
        }
    }
}
