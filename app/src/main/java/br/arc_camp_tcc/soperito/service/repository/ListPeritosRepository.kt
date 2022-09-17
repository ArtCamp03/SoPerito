package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import br.arc_camp_tcc.soperito.service.repository.remote.ListPeritoService
import br.arc_camp_tcc.soperito.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPeritosRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(ListPeritoService::class.java)

    fun listPeritos(listener: APIListener<List<ListPeritoModel>>) {
        val call = remote.ListPeritoDisponivel()
        call.enqueue(object : Callback<List<ListPeritoModel>> {
            override fun onResponse(call: Call<List<ListPeritoModel>>, response: Response<List<ListPeritoModel>>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.register_not_found))
                }
            }

            override fun onFailure(call: Call<List<ListPeritoModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_peritoss))
            }
        })
    }

    fun loadList(listener: APIListener<List<ListPeritoModel>>) {
        val call = remote.ListPeritoCandidato()
        call.enqueue(object : Callback<List<ListPeritoModel>> {
            override fun onResponse(
                call: Call<List<ListPeritoModel>>,
                response: Response<List<ListPeritoModel>>
            ) {
                listener.onFailure(context.getString(R.string.register_not_found))
            }

            override fun onFailure(call: Call<List<ListPeritoModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_peritoss))
            }
        })
    }

    fun loadCurriculo(codCurriculo: Int, listener: APIListener<ListPeritoModel>){
        val call = remote.loadListPerito(codCurriculo,null, null, null
        , null, null, null, null)
        call.enqueue(object : Callback<ListPeritoModel> {
            override fun onResponse(call: Call<ListPeritoModel>, response: Response<ListPeritoModel>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }
            override fun onFailure(call: Call<ListPeritoModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.register_not_found))
            }
        })
    }
}