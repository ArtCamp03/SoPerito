package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.PeritoModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.repository.remote.PeritoService
import br.arc_camp_tcc.soperito.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeritoRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(PeritoService::class.java)

    fun criaPérito(
        codUsuario: Int,
        userPerito: Int,
        nomePerito: String,
        listener: APIListener<Boolean>
    ) {
        val call = remote.savePerito(codUsuario, userPerito, nomePerito)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conta_perito))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_conexao))
            }
        })
    }

    fun loadPerito(codUsuario: Int, codPerito: Int, listener: APIListener<PeritoModel>) {
        val call = remote.loadPerito(codUsuario, codPerito, null, null)
        call.enqueue(object : Callback<PeritoModel> {
            override fun onResponse(call: Call<PeritoModel>, response: Response<PeritoModel>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }

            override fun onFailure(call: Call<PeritoModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.perito_not_found))
            }
        })
    }

    fun addPerfil(
        codUser: Int,
        codPerito: Int,
        exp: String,
        esp: String,
        listener: APIListener<Boolean>
    ) {
        val call = remote.savePerfil(codUser, codPerito, exp, esp)
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

    fun loadPerfil(codUser: Int, codPerito: Int, listener: APIListener<PeritoModel>) {
        val call = remote.loadPerfil(codUser, codPerito, null, null, null)
        call.enqueue(object : Callback<PeritoModel> {
            override fun onResponse(call: Call<PeritoModel>, response: Response<PeritoModel>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }

            override fun onFailure(call: Call<PeritoModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.register_not_found))
            }
        })
    }


    fun loadInfoCandiato(codPerito: Int, listener: APIListener<UsuarioModel>) {
        val call = remote.loadInfo(codPerito, null, null, null)
        call.enqueue(object : Callback<UsuarioModel> {
            override fun onResponse(call: Call<UsuarioModel>, response: Response<UsuarioModel>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_conexao))
                }
            }

            override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.register_not_found))
            }
        })
    }
}