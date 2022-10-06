package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.EmpregadorModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.repository.remote.EmpregadorService
import br.arc_camp_tcc.soperito.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmrpegadorRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(EmpregadorService::class.java)

    fun criaEmpregador(codUsuario: Int,useEmpregador:Int,nomeEmp:String,getEmail:String,
                       getTelefone:String, listener: APIListener<Boolean>){
        val call = remote.criaEmpregador(codUsuario, useEmpregador,getEmail, getTelefone, nomeEmp)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                handleResponse(response, listener)
            }
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_conexao))
            }
        })
    }

    fun loadEmp(codUsuario: Int, codEmpregador: Int,listener: APIListener<EmpregadorModel>){
        val call = remote.loadEmpregador(codUsuario, codEmpregador,null,null, null, null, null)
        call.enqueue(object : Callback<EmpregadorModel> {
            override fun onResponse(
                call: Call<EmpregadorModel>,
                response: Response<EmpregadorModel>
            ) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conta_empregador))
                }
            }

            override fun onFailure(call: Call<EmpregadorModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_conexao))
            }

        })
    }

    fun editPerfilEmp(codEmpregador: Int, nome: String, telefone: String, email: String,listener: APIListener<Boolean>){
        val call = remote.editPerfilEmp(codEmpregador, nome, email, telefone)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(
                call: Call<Boolean>,
                response: Response<Boolean>
            ) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.update_invalid))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_conexao))
            }

        })
    }



    fun loadInfoVaga(codEmpregador: Int,listener: APIListener<UsuarioModel>){
        val call = remote.loadInfoVaga(codEmpregador,null,null, null)
        call.enqueue(object : Callback<UsuarioModel> {
            override fun onResponse(
                call: Call<UsuarioModel>,
                response: Response<UsuarioModel>
            ) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conta_empregador))
                }
            }

            override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_conexao))
            }

        })
    }

}