package br.arc_camp_tcc.soperito.retrofitclient

import retrofit2.Retrofit

class RetrofitClient private constructor(){
    companion object{

        private lateinit var retrofit: Retrofit
        private val baseurl = ""

        private fun getRetrofitInstance() : Retrofit {

            if(!::retrofit.isInitialized){
                retrofit = Retrofit.Builder()
                    .baseUrl()
                    .build()
            }

            return retrofit
        }
    }
}