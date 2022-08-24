package br.arc_camp_tcc.soperito.service.repository.remote

import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UsuarioService {

    @POST("cadastro/cadastro.php")
    @FormUrlEncoded
    fun createUser(
        @Field("email") email: String,
        @Field("nome") nome: String,
        @Field("cpf") cpf: String,
        @Field("telefone") telefone: String,
        @Field("senha") senha: String,
    ): Call<Boolean>

    @POST("load/load.php")
    @FormUrlEncoded
    fun load(
        @Field("cod_usuario") codigUsuario: Int,
        @Field("cod_emp") codEmp: Int?,
        @Field("cod_perito") codPerito: Int?,
        @Field("user_perito") userPerito: Boolean?,
        @Field("user_emp") userEmp: Boolean?,
        @Field("email") email: String?,
        @Field("nome") nome: String?,
        @Field("cpf") cpf: String?,
        @Field("telefone") telefone: String?,
        @Field("senha") senha: String?
    ): Call<UsuarioModel>

}