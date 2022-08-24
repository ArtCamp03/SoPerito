package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.VagasModel
import br.arc_camp_tcc.soperito.service.repository.remote.RetrofitClient
import br.arc_camp_tcc.soperito.service.repository.remote.VagasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VagasRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(VagasService::class.java)

    // faz download usando API
    fun vagasList(vagaId : Int,servico : String, descricao : String,
                    tempExp : Int, dispPagar : Float,
                    listener: APIListener<List<VagasModel>>){
        val call = remote.servicoList(vagaId, servico, descricao, tempExp, dispPagar )
        call.enqueue(object : Callback<List<VagasModel>>{
            override fun onResponse(
                call: Call<List<VagasModel>>,
                response: Response<List<VagasModel>>
            ) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }

            override fun onFailure(call: Call<List<VagasModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_inesperado))
            }

        })  // feedback

    }

}