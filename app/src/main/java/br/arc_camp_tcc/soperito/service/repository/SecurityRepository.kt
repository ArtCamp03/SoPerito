package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.repository.remote.RetrofitClient
import br.arc_camp_tcc.soperito.service.repository.remote.SecurityService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecurityRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(SecurityService::class.java)

    fun confirmSenha(senha: String,codUuario: Int, listener: APIListener<Boolean>) {
        val call = remote.confirmSenha(codUuario,senha)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.register_not_found))
                }
            }
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_inesperado))
            }
        })
    }

    fun alterSenha(Nsenha: String, codUuario: Int, listener: APIListener<Boolean>) {
        val call = remote.alterSenha(codUuario,Nsenha)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.register_not_found))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_inesperado))
            }

        })
    }


}