package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import br.arc_camp_tcc.soperito.service.repository.remote.api.ListPeritoService
import br.arc_camp_tcc.soperito.service.repository.remote.api.RetrofitClient
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

    fun saveCurriculo(
        codPerito: Int,
        nome: String,
        curriculo: ListPeritoModel,
        listener: APIListener<Boolean>
    ) {
        val call = remote.saveCurriculo(codPerito, nome,
            curriculo.servico,
            curriculo.temp ,
            curriculo.obs,
            curriculo.valor,
            curriculo.dataCurriculo)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_salvamento))
            }
        })
    }

    fun buscPerito(servico:String, listener: APIListener<List<ListPeritoModel>>){
        val call = remote.listPeritoBusca(null,null, null, servico
            , null, null, null, null)
        call.enqueue(object : Callback<List<ListPeritoModel>> {
            override fun onResponse(call: Call<List<ListPeritoModel>>, response: Response<List<ListPeritoModel>>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }
            override fun onFailure(call: Call<List<ListPeritoModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.register_not_found))
            }
        })
    }

    fun peritoCandidato(codEmp:Int, listener: APIListener<List<ListPeritoModel>>){
        val call = remote.listPeritoCandidato(null,null, codEmp,
           null , null, null, null, null, null)
        call.enqueue(object : Callback<List<ListPeritoModel>> {
            override fun onResponse(call: Call<List<ListPeritoModel>>, response: Response<List<ListPeritoModel>>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }
            override fun onFailure(call: Call<List<ListPeritoModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.register_not_found))
            }
        })
    }

    fun contratante(codCurriculo:Int,codEmp:Int, codPerito:Int,listener: APIListener<Boolean>) {
        val call = remote.addContratante(codCurriculo,codEmp, codPerito)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_salvamento))
            }

        })
    }



}