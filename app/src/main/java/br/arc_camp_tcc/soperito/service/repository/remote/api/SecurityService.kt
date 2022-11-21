package br.arc_camp_tcc.soperito.service.repository.remote.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SecurityService {

    @POST("config/security/confirm_senha.php")
    @FormUrlEncoded
    fun confirmSenha(
        @Field("cod_usuario") codigUsuario: Int,
        @Field("senha") senha: String?
    ): Call<Boolean>

    @POST("config/security/alter_senha.php")
    @FormUrlEncoded
    fun alterSenha(
        @Field("cod_usuario") codigUsuario: Int,
        @Field("senha") senha: String?
    ): Call<Boolean>

}