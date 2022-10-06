package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.EmpregadorModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.EmrpegadorRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class EmpregadorViewModel(application: Application): AndroidViewModel(application)  {

   // private val repository = bancoRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val emrpegadorRepository = EmrpegadorRepository (application.applicationContext)

    // verifica se usuario ja possui conta Perito
    private val getContUser = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_USER).toInt()
    private val getContEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_EMP).toInt()
    private val getCodEmp = securityPreferences.get(DataBaseConstants.BUNDLE.COD_EMP).toInt()
    private val getNomeEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.NOME)
    private val getTelefoneEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.TELEFONE)
    private val getEmailEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.EMAIL)
    private val userEmp = 1

    private val _criaEmp = MutableLiveData<ValidationModel>()
    val criaEmp : LiveData<ValidationModel> = _criaEmp

    private val _loadEmp = MutableLiveData<ValidationModel>()
    val loadEmp : LiveData<ValidationModel> = _loadEmp

    private val _editPerfil = MutableLiveData<ValidationModel>()
    val editPerfil : LiveData<ValidationModel> = _editPerfil

    private val _nomeEmp = MutableLiveData<String>()
    val nomeEmp : LiveData<String> = _nomeEmp

    private val _telefone = MutableLiveData<String>()
    val telefone : LiveData<String> = _telefone

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _status = MutableLiveData<String>()
    val status : LiveData<String> = _status

    private val _loadInfoSuccess = MutableLiveData<UsuarioModel>()
    val loadInfoSuccess : LiveData<UsuarioModel> = _loadInfoSuccess

    private val _loadInfoFail = MutableLiveData<String>()
    val loadInfoFail : LiveData<String> = _loadInfoFail


    fun criaEmrpegador(){
        emrpegadorRepository.criaEmpregador(getContUser, userEmp,getNomeEmp,getEmailEmp, getTelefoneEmp, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                _criaEmp.value = ValidationModel()
            }
            override fun onFailure(message: String) {
                _criaEmp.value = ValidationModel(message)
            }
        })
    }

    fun loadEmrpegador(){
        emrpegadorRepository.loadEmp(getContUser, getContEmp, object : APIListener<EmpregadorModel> {
            override fun onSuccess(result: EmpregadorModel) {
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.NOME, result.nome).toString()
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.TELEFONE, result.telefone).toString()
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.EMAIL, result.email).toString()
                _loadEmp.value = ValidationModel()
            }
            override fun onFailure(message: String) {
                _loadEmp.value = ValidationModel(message)
            }
        })
    }

    fun loadPerfilEmp(){
        _nomeEmp.value = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.NOME)
        _telefone.value = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.TELEFONE)
        _email.value = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.EMAIL)
    }

    fun editPerfil(nome:String, telefone:String, email:String){
        emrpegadorRepository.editPerfilEmp(getContEmp, nome, telefone,email, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.NOME, nome)
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.TELEFONE,telefone)
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.EMAIL,email)
                _editPerfil.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _editPerfil.value = ValidationModel(message)
            }

        })

    }

    fun loadInfoVaga() {
        emrpegadorRepository.loadInfoVaga(getCodEmp, object : APIListener<UsuarioModel> {
            override fun onSuccess(result: UsuarioModel) {
                _loadInfoSuccess.value = result
            }

            override fun onFailure(message: String) {
                _loadInfoFail.value = message
            }
        })
    }

}