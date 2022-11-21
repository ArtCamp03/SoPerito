package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.repository.remote.api.RetrofitClient
import br.arc_camp_tcc.soperito.service.repository.remote.api.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(UsuarioService::class.java)

    // chama dados de usuario da API
    fun loadUser(codUuario: Int, listener: APIListener<UsuarioModel>) {
        val call = remote.load(codUuario,null, null, null, null, null, null
        , null, null, null, null, null)
        call.enqueue(object : Callback<UsuarioModel> {
            override fun onResponse(call: Call<UsuarioModel>, response: Response<UsuarioModel>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.register_not_found))
                }
            }

            override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_inesperado))
            }

        })
    }
}