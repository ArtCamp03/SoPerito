package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import br.arc_camp_tcc.soperito.service.repository.remote.ListEmpregadorservice
import br.arc_camp_tcc.soperito.service.repository.remote.RetrofitClient

class ListEmpregadoresRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(ListEmpregadorservice::class.java)

    fun listEmpregadores(listener: APIListener<List<ListEmpregadorModel>>) {
        val call = remote.listEmpregadores()
        executeCall(call, listener)
    }

}