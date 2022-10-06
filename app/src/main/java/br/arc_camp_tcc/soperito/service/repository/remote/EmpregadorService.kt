package br.arc_camp_tcc.soperito.service.repository.remote

import br.arc_camp_tcc.soperito.service.model.EmpregadorModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EmpregadorService {

    @POST("empregador/cria_empregador/cria_emp.php")
    @FormUrlEncoded
    fun criaEmpregador(
        @Field("cod_usuario") codUser: Int,
        @Field("user_emp") userEmp: Int,
        @Field("email") email: String?,
        @Field("telefone") telefone: String?,
        @Field("nome_empregador") nome: String
    ): Call<Boolean>

    @POST("empregador/load_empregador/load_emp.php")
    @FormUrlEncoded
    fun loadEmpregador(
        @Field("cod_usuario") codUser: Int,
        @Field("cod_emp") codEmp: Int,
        @Field("user_emp") userEmp: Int?,
        @Field("nome_empregador") nome: String?,
        @Field("email") email: String?,
        @Field("telefone") telefone: String?,
        @Field("cod_vaga") codVaga: Int?
    ): Call<EmpregadorModel>

    @POST("empregador/edit_perfil_empregador/edit_perfil_emp.php")
    @FormUrlEncoded
    fun editPerfilEmp(
        @Field("cod_emp") codEmp: Int,
        @Field("nome_empregador") nome: String?,
        @Field("email") email: String?,
        @Field("telefone") telefone: String?,
    ): Call<Boolean>


    @POST("perito/contratante/info_contratante/load_info.php")
    @FormUrlEncoded
    fun loadInfoVaga(
        @Field("cod_emp") codEmp: Int,
        @Field("nome") nome: String?,
        @Field("email") email: String?,
        @Field("telefone") telefone: String?
    ): Call<UsuarioModel>
}