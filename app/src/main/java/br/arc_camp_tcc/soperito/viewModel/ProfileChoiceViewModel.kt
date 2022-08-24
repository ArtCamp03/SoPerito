package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.service.repository.UsuarioRepository

class ProfileChoiceViewModel(application: Application): AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val usuarioRepository = UsuarioRepository(application.applicationContext)

    private val _loadUsuario = MutableLiveData<ValidationModel>()
    val loadUsuario: LiveData<ValidationModel> = _loadUsuario

    private val codUsuario = securityPreferences.get(DataBaseConstants.SHARED.USER_KEY).toInt()

    fun loadDataUser() {
        usuarioRepository.loadUser(codUsuario,object : APIListener<UsuarioModel> {
            override fun onSuccess(result: UsuarioModel) {

                securityPreferences.store(DataBaseConstants.USER.COLUMNS.COD_USER, result.codigUsuario.toString())
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.USER_PERITO, result.userPerito.toString())
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.USER_EMP, result.userEmp.toString())
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.COD_PERITO, result.codPerito.toString())
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.COD_EMP, result.codEmp.toString())
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.NOME, result.nome)
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.CPF, result.cpf)
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.TELEFONE, result.telefone)
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.EMAIL, result.email)

                _loadUsuario.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _loadUsuario.value = ValidationModel(message)
            }

        })
    }

}