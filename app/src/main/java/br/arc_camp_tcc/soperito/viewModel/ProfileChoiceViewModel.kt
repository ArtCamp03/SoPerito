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

    private val _cntPerito = MutableLiveData<Boolean>()
    val cntPerito: LiveData<Boolean> = _cntPerito

    private val _cntEmpregador = MutableLiveData<Boolean>()
    val cntEmpregador: LiveData<Boolean> = _cntEmpregador

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

    fun verifyContaPerito(){
        val codUser = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_USER).toIntOrNull()
        val userPerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.USER_PERITO).toIntOrNull()

        var contaPerito : Boolean = false

        if(codUser != 0 && userPerito == 1){
            contaPerito = true
        }
        _cntPerito.value = contaPerito
    }

    fun verifyContaEmpregador() {
        val codUser = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_USER).toIntOrNull()
        val userEmpregador = securityPreferences.get(DataBaseConstants.USER.COLUMNS.USER_EMP).toIntOrNull()
        var contaEmpregador: Boolean = false

        if (codUser != 0 && userEmpregador == 1) {
            contaEmpregador = true
        }

        _cntEmpregador.value = contaEmpregador
    }

}