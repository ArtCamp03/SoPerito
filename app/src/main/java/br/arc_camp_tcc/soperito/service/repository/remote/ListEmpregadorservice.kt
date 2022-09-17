package br.arc_camp_tcc.soperito.service.repository.remote

import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import retrofit2.Call
import retrofit2.http.*

interface ListEmpregadorservice {

    @GET("perito/")
    fun  listEmpregadores(): Call<List<ListEmpregadorModel>>

    @GET("perito/")
    fun  listEmpregadoresContato(): Call<List<ListEmpregadorModel>>

    @GET("perito/")
    fun  listEmpregadoresHistorico(): Call<List<ListEmpregadorModel>>

    @GET("perito/{id}")
    fun loadListPerito(@Path(value = "id", encoded = true)
                       id: Int
    ): Call<ListEmpregadorModel>

    @POST("save_perito/save_perito.php")
    @FormUrlEncoded
    fun createVaga(
        @Field("cod_curriculo") codCurriculo: Int,
        @Field("cod_perito") codPerito: Int,
        @Field("nome_perito") nome: String,
        @Field("servico") servico: String,
        @Field("temp") temp: String,
        @Field("obs") obs: String,
        @Field("valor") valor: String
    ): Call<Boolean>

    @POST("save_perito/save_perito.php")
    @FormUrlEncoded
    fun updateVaga(
        @Field("cod_curriculo") codCurriculo: Int,
        @Field("cod_perito") codPerito: Int,
        @Field("nome_perito") nome: String,
        @Field("servico") servico: String,
        @Field("temp") temp: String,
        @Field("obs") obs: String,
        @Field("valor") valor: String
    ): Call<Boolean>

    @DELETE("save_perito/save_perito.php")
    @FormUrlEncoded
    fun deleteVaga(
        @Field("cod_curriculo") codCurriculo: Int
    ): Call<Boolean>

}