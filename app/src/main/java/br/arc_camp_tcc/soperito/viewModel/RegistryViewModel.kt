package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.RegistryRepository

class RegistryViewModel(application: Application): AndroidViewModel(application) {

    private val registerRepository = RegistryRepository(application.applicationContext)

    private val _registryUser = MutableLiveData<ValidationModel>()
    val registryUser: LiveData<ValidationModel> = _registryUser

    fun registre(usuario: UsuarioModel){
        registerRepository.createUser(usuario, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                _registryUser.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _registryUser.value = ValidationModel(message)
            }

        })
    }

}