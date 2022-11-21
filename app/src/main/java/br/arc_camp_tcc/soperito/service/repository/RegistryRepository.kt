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

class RegistryRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(UsuarioService::class.java)

    fun createUser(usuario: UsuarioModel, listener: APIListener<Boolean>) {
        val call = remote.createUser(usuario.email, usuario.nome, usuario.cpf,
            usuario.telefone,usuario.cidade, usuario.estado, usuario.senha
            )
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_login))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_salvamento))
            }


        })
    }
}