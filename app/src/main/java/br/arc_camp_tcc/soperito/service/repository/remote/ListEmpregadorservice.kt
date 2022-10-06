package br.arc_camp_tcc.soperito.service.repository.remote

import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ListEmpregadorservice {

    @GET("perito/servicos_disponivel/vagas_disp.php")
    fun  listEmpregadores(): Call<List<ListEmpregadorModel>>

    @POST("perito/busca_empregador/busca_emp.php")
    @FormUrlEncoded
    fun buscVaga(
        @Field("cod_vaga") codVaga: Int?,
        @Field("cod_emp") codEmp: Int?,
        @Field("nome_emp") nomeEmp: String?,
        @Field("servico") servico: String?,
        @Field("temp_exp") tempExp: String?,
        @Field("disp_pagar") dispPagar: String?,
        @Field("descricao") descricao: String?,
        @Field("v_data") vData: String?
    ): Call<List<ListEmpregadorModel>>

    @POST("empregador/vaga/save_vaga/save_vaga.php")
    @FormUrlEncoded
    fun saveVaga(
        @Field("cod_emp") codEmp: Int?,
        @Field("nome_emp") nomeEmp: String?,
        @Field("servico") servico: String?,
        @Field("temp_exp") tempExp: String?,
        @Field("disp_pagar") dispPagar: String?,
        @Field("descricao") descricao: String?,
        @Field("v_data") vData: String?
    ): Call<Boolean>

    @POST("perito/servicos_disponivel/load_vagas.php")
    @FormUrlEncoded
    fun loadVaga(
        @Field("cod_vaga") codVaga: Int?,
        @Field("cod_emp") codEmp: Int?,
        @Field("nome_emp") nomeEmp: String?,
        @Field("servico") servico: String?,
        @Field("temp_exp") tempExp: String?,
        @Field("disp_pagar") dispPagar: String?,
        @Field("descricao") descricao: String?,
        @Field("v_data") vData: String?
    ): Call<ListEmpregadorModel>

    @POST("empregador/candidatos/addCandidato.php")
    @FormUrlEncoded
    fun addCandidato(
        @Field("cod_vaga") codVaga: Int,
        @Field("cod_emp") codEmp: Int,
        @Field("cod_perito") codPerito: Int
    ): Call<Boolean>

    @POST("perito/contratante/contratante.php")
    @FormUrlEncoded
    fun listContatoService(
        @Field("cod_vaga") codVaga: Int?,
        @Field("cod_emp") codEmp: Int?,
        @Field("contratado") contratante: Int?,
        @Field("nome_emp") nomeEmp: String?,
        @Field("servico") servico: String?,
        @Field("temp_exp") tempExp: String?,
        @Field("disp_pagar") dispPagar: String?,
        @Field("descricao") descricao: String?,
        @Field("v_data") vData: String?
    ): Call<List<ListEmpregadorModel>>
}