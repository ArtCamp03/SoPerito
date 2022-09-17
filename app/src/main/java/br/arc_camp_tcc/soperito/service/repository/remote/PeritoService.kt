package br.arc_camp_tcc.soperito.service.repository.remote

import br.arc_camp_tcc.soperito.service.model.PeritoModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PeritoService {
    @POST("save_perito/save_perito.php")
    @FormUrlEncoded
    fun savePerito(
        @Field("cod_usuario") codigUsuario: Int,
        @Field("user_perito") userPerito: Int,
        @Field("nome_perito") nome: String
    ): Call<Boolean>

    @POST("load_perito/load_perito.php")
    @FormUrlEncoded
    fun loadPerito(
        @Field("cod_usuario") codigUsuario: Int,
        @Field("cod_perito") codPerito: Int,
        @Field("user_perito") userPerito: Int?,
        @Field("nome_perito") nome: String?
    ): Call<PeritoModel>

    @POST("perito/perfil/save_perfil/save_perfil.php")
    @FormUrlEncoded
    fun savePerfil(
        @Field("cod_usuario") codigUsuario: Int,
        @Field("cod_perito") codPerito: Int,
        @Field("exp") exp: String,
        @Field("espec") espec: String
    ): Call<Boolean>

    @POST("perito/perfil/load_Perfil/load_Perfil.php")
    @FormUrlEncoded
    fun loadPerfil(
        @Field("cod_usuario") codigUsuario: Int,
        @Field("cod_perito") codPerito: Int,
        @Field("nome_perito") nome: String?,
        @Field("exp") exp: String?,
        @Field("espec") espec: String?
    ): Call<PeritoModel>

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

}