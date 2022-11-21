package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.Person.PersonLoginModel
import br.arc_camp_tcc.soperito.service.repository.remote.api.PersonService
import br.arc_camp_tcc.soperito.service.repository.remote.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//? tratamento de ERRO e conversao de JSON
class PersonRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(PersonService::class.java)

    fun login(email: String, senha: String, listener: APIListener<PersonLoginModel>) {
        val call = remote.login(email, senha)
        call.enqueue(object : Callback<PersonLoginModel> {
            override fun onResponse(
                call: Call<PersonLoginModel>,
                response: Response<PersonLoginModel>
            ) {
                if (response.code() == DataBaseConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess((it)) }
                } else {
                    listener.onFailure(context.getString(R.string.erro_login))
                }
            }

            // falha de comunicaçao
            override fun onFailure(call: Call<PersonLoginModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.erro_conexao))
            }
        })

    }


}