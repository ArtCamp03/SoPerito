package br.arc_camp_tcc.soperito.service.repository.remote.api

import br.arc_camp_tcc.soperito.service.model.VagasModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface VagasService {
    // envia dados para API
    @POST("pagina/servico.php")
    @FormUrlEncoded
    fun servicoList(
        @Field("id_vaga") vagaId: Int,
        @Field("servico") servico: String,
        @Field("descricao") descricao: String,
        @Field("temp_exp") tempExp: Int,
        @Field("disp_pagar") dispPagar: Float
    ): Call<List<VagasModel>>

}