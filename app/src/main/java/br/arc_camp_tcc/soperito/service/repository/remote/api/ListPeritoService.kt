package br.arc_camp_tcc.soperito.service.repository.remote.api

import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ListPeritoService {

    @GET("empregador/perito_disponivel/perito_disp.php")
    fun  ListPeritoDisponivel(): Call<List<ListPeritoModel>>

    @POST("empregador/busca_perito/busca_perito.php")
    @FormUrlEncoded
    fun  listPeritoBusca(
        @Field("cod_curriculo") codCurriculo: Int?,
        @Field("cod_perito") codPerito: Int?,
        @Field("nome_perito") nome: String?,
        @Field("servico") servico: String?,
        @Field("temp") temp: String?,
        @Field("obs") obs: String?,
        @Field("valor") valor: String?,
        @Field("data_curriculo") dataCurriculo: String?
    ): Call<List<ListPeritoModel>>

    @POST("empregador/perito_disponivel/load_perito_disp.php")
    @FormUrlEncoded
    fun loadListPerito(
        @Field("cod_curriculo") codCurriculo: Int,
        @Field("cod_perito") codPerito: Int?,
        @Field("nome_perito") nome: String?,
        @Field("servico") servico: String?,
        @Field("temp") temp: String?,
        @Field("obs") obs: String?,
        @Field("valor") valor: String?,
        @Field("data_curriculo") dataCurriculo: String?
    ): Call<ListPeritoModel>

    @POST("empregador/candidatos/candidatos.php")
    @FormUrlEncoded
    fun listPeritoCandidato(
        @Field("cod_curriculo") codCurriculo: Int?,
        @Field("cod_perito") codPerito: Int?,
        @Field("empregador") emrpegador: Int?,
        @Field("nome_perito") nome: String?,
        @Field("servico") servico: String?,
        @Field("temp") temp: String?,
        @Field("obs") obs: String?,
        @Field("valor") valor: String?,
        @Field("data_curriculo") dataCurriculo: String?
    ): Call<List<ListPeritoModel>>

    @POST("perito/curriculo/save_curriculo/save_curriculo.php")
    @FormUrlEncoded
    fun saveCurriculo(
        @Field("cod_perito") codPerito: Int,
        @Field("nome_perito") nome: String?,
        @Field("servico") servico: String?,
        @Field("temp") temp: String?,
        @Field("obs") obs: String?,
        @Field("valor") valor: String?,
        @Field("data_curriculo") dataCurriculo: String
    ): Call<Boolean>

    @POST("perito/contratante/add_contratante.php")
    @FormUrlEncoded
    fun addContratante(
        @Field("cod_curriculo") codVaga: Int,
        @Field("cod_emp") codEmp: Int,
        @Field("cod_perito") codPerito: Int
    ): Call<Boolean>

}