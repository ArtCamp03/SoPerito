package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import br.arc_camp_tcc.soperito.service.repository.ListPeritosRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class ListPeritoViewModel(application: Application): AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val peritoListRepository = ListPeritosRepository(application.applicationContext)

    private val _listPerito = MutableLiveData<List<ListPeritoModel>>()
    val listPerito: LiveData<List<ListPeritoModel>> = _listPerito

    private val _listFailure = MutableLiveData<String>()
    val listFailure: LiveData<String> = _listFailure

    fun listPerito() {
        peritoListRepository.listPeritos( object : APIListener<List<ListPeritoModel>> {
            override fun onSuccess(result: List<ListPeritoModel>) {
                _listPerito.value = result
            }
            override fun onFailure(message: String) {
                _listFailure.value = message
            }
        })

    }

}