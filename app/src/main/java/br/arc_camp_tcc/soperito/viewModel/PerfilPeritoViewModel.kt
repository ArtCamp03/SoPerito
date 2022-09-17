package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.PeritoModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.PeritoRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class PerfilPeritoViewModel(application: Application): AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val peritoRepository = PeritoRepository(application.applicationContext)

    private val codUser = securityPreferences.get(DataBaseConstants.PERITO.COLUMNS.COD_USER).toInt()
    private val codPerito = securityPreferences.get(DataBaseConstants.PERITO.COLUMNS.COD_PERITO).toInt()

    private val _nome = MutableLiveData<String>()
    val nome: LiveData<String> = _nome

    private val _esp = MutableLiveData<String>()
    val esp: LiveData<String> = _esp

    private val _exp = MutableLiveData<String>()
    val exp: LiveData<String> = _exp



    private val _setPerfil = MutableLiveData<ValidationModel>()
    val setPerfil: LiveData<ValidationModel> = _setPerfil

    private val _loadPerfil = MutableLiveData<ValidationModel>()
    val loadPerfil: LiveData<ValidationModel> = _loadPerfil

    fun setPerfil(exp : String,esp : String){
        peritoRepository.addPerfil(codUser, codPerito,exp, esp, object : APIListener<Boolean>{
            override fun onSuccess(result: Boolean) {
                _setPerfil.value = ValidationModel()
            }
            override fun onFailure(message: String) {
                _setPerfil.value = ValidationModel(message)
            }
        })
    }

    fun loadPerfil(){
        peritoRepository.loadPerfil(codUser, codPerito , object : APIListener<PeritoModel>{
            override fun onSuccess(result: PeritoModel) {

                /*
                securityPreferences.store(DataBaseConstants.PERITO.COLUMNS.ESP, result.espec).toString()
                securityPreferences.store(DataBaseConstants.PERITO.COLUMNS.EXP, result.exp).toString()
                */
                _nome.value = result.nome
                _esp.value = result.espec
                _exp.value = result.exp

                _loadPerfil.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _loadPerfil.value = ValidationModel(message)
            }

        })

    }

}