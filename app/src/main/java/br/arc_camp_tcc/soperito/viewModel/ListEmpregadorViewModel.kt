package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import br.arc_camp_tcc.soperito.service.repository.ListEmpregadoresRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class ListEmpregadorViewModel(application: Application): AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val peritoListRepository = ListEmpregadoresRepository(application.applicationContext)

    private val _listEmpregador = MutableLiveData<List<ListEmpregadorModel>>()
    val listEmpregador: LiveData<List<ListEmpregadorModel>> = _listEmpregador

    private val _listFailure = MutableLiveData<String>()
    val listFailure: LiveData<String> = _listFailure

    fun listPerito() {
        peritoListRepository.listEmpregadores( object : APIListener<List<ListEmpregadorModel>> {
            override fun onSuccess(result: List<ListEmpregadorModel>) {
                _listEmpregador.value = result
            }

            override fun onFailure(message: String) {
                _listFailure.value = message
            }

        })
    }
}