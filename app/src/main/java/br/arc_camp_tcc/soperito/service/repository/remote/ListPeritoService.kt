package br.arc_camp_tcc.soperito.service.repository.remote

import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import retrofit2.Call
import retrofit2.http.*

interface ListPeritoService {

    @GET("empregador/perito_disponivel/perito_disp.php")
    fun  ListPeritoDisponivel(): Call<List<ListPeritoModel>>

    @GET("perito/")
    fun  ListPeritoCandidato(): Call<List<ListPeritoModel>>

    @GET("perito/")
    fun  ListPeritoHistorico(): Call<List<ListPeritoModel>>

    @POST("empregador/perito_disponivel/load_perito_disp.php/{cod_curriculo}")
    @FormUrlEncoded
    fun loadListPerito(
        @Field("cod_curriculo") codCurriculo: Int?,
        @Field("cod_perito") codPerito: Int?,
        @Field("nome_perito") nome: String?,
        @Field("servico") servico: String?,
        @Field("temp") temp: String?,
        @Field("obs") obs: String?,
        @Field("valor") valor: String?,
        @Field("data_curriculo") dataCurriculo: String?
    ): Call<ListPeritoModel>

    @POST("save_perito/save_perito.php")
    @FormUrlEncoded
    fun updateVaga(
        @Field("cod_curriculo") codCurriculo: Int,
        @Field("cod_perito") codPerito: Int,
        @Field("nome_perito") nome: String,
        @Field("servico") servico: String,
        @Field("temp") temp: String,
        @Field("obs") obs: String,
        @Field("valor") valor: String,
        @Field("data_curriculo") dataCurriculo: String
    ): Call<Boolean>

    @DELETE("save_perito/save_perito.php")
    @FormUrlEncoded
    fun deleteVaga(
        @Field("cod_curriculo") codCurriculo: Int
    ): Call<Boolean>

}