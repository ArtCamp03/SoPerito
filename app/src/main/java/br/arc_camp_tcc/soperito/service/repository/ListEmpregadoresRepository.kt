package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import br.arc_camp_tcc.soperito.service.repository.remote.ListEmpregadorservice
import br.arc_camp_tcc.soperito.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListEmpregadoresRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(ListEmpregadorservice::class.java)

    fun listEmpregadores(listener: APIListener<List<ListEmpregadorModel>>) {
        val call = remote.listEmpregadores()
        executeCall(call, listener)
    }

    fun buscEmpregadores(servico:String,listener: APIListener<List<ListEmpregadorModel>>) {
        val call = remote.buscVaga(null, null, null, servico, null
        , null, null, null)
        executeCall(call, listener)
    }

    fun saveVaga(getCodEmp:Int,getNomeEmp:String, vaga: ListEmpregadorModel
                         ,listener: APIListener<Boolean>) {
        val call = remote.saveVaga(
            getCodEmp,
            getNomeEmp,
            vaga.servico,
            vaga.tempExp,
            vaga.dispPagar,
            vaga.descricao,
            vaga.vData)
        call.enqueue(object : Callback<Boolean>{
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

    fun loadVaga(codVaga: Int,listener: APIListener<ListEmpregadorModel>) {
        val call = remote.loadVaga(codVaga,null,null, null, null
        , null, null, null)
        executeCall(call, listener)
    }

    fun candidato(codVaga:Int,codEmp:Int, codPer:Int,listener: APIListener<Boolean>) {
        val call = remote.addCandidato(codVaga,codEmp, codPer)
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

    fun contatoServico(codPerito:Int, listener: APIListener<List<ListEmpregadorModel>>){
        val call = remote.listContatoService(null,null, codPerito,
            null , null, null, null, null, null)
        call.enqueue(object : Callback<List<ListEmpregadorModel>> {
            override fun onResponse(call: Call<List<ListEmpregadorModel>>, response: Response<List<ListEmpregadorModel>>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }
            override fun onFailure(call: Call<List<ListEmpregadorModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.register_not_found))
            }
        })
    }

}