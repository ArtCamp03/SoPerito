package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.service.repository.SecurityRepository

class SecurityViewModel(application: Application) : AndroidViewModel(application){

    private val securityRepository = SecurityRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val codUsuario = securityPreferences.get(DataBaseConstants.SHARED.USER_KEY).toInt()

    private val _verifySenha = MutableLiveData<ValidationModel>()
    val verifySenha: LiveData<ValidationModel> = _verifySenha

    private val _alteraSenha = MutableLiveData<ValidationModel>()
    val alteraSenha: LiveData<ValidationModel> = _alteraSenha

    fun confirmSenha(senha: String){
        securityRepository.confirmSenha(senha, codUsuario, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                _verifySenha.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _verifySenha.value = ValidationModel(message)
            }

        })
    }

    fun alterSenha(Nsenha: String){
        securityRepository.alterSenha(Nsenha, codUsuario, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {

                logout()

                _alteraSenha.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _alteraSenha.value = ValidationModel(message)
            }

        })
    }

    fun logout(){
        securityPreferences.remove(DataBaseConstants.SHARED.PERSON_KEY)
        securityPreferences.remove(DataBaseConstants.SHARED.TOKEN_KEY)
    }
}